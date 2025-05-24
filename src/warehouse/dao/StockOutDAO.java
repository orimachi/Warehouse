package warehouse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import warehouse.bean.EStatus;
import warehouse.entity.StockOut;
import warehouse.utils.JDBC;
import warehouse.utils.SQLBuilder;


public class StockOutDAO extends BaseDAO<StockOut, UUID>{
    private static final Logger logger = Logger.getLogger(StockOutDAO.class.getName());

    @Override
    public void insert(StockOut entity) {
        try {
            String sql = SQLBuilder.buildSQLInsert("StockOut", "id", "quantity", "idProduct", "idWarehouse", "username", "updatedDate", "status");
            logger.info(sql);
            JDBC.update(sql,
                    entity.getId().toString(),
                    entity.getQuantity(),
                    entity.getIdProduct().toString(),
                    entity.getIdWarehouse().toString(),
                    entity.getUsername(),
                    entity.getUpdatedDate(),
                    entity.getStatus().name()
            );
            logger.info("Insert success");
        } catch (Exception e) {
            throw new RuntimeException("Cannot insert StockOut: " + e.getMessage());
        }
    }

    @Override
    public void update(StockOut entity) {
        try {
            String sql = SQLBuilder.buildSQLUpdate("StockOut", "id", "quantity", "idProduct", "idWarehouse", "username", "updatedDate", "status");
            logger.info(sql);
            JDBC.update(sql,
                    entity.getQuantity(),
                    entity.getIdProduct().toString(),
                    entity.getIdWarehouse().toString(),
                    entity.getUsername(),
                    entity.getUpdatedDate(),
                    entity.getStatus().name(),
                    entity.getId().toString()
            );
            logger.info("Update success");
        } catch (Exception e) {
            throw new RuntimeException("Cannot update StockOut: " + e.getMessage());
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            String sql = SQLBuilder.buildSQLDelete("StockOut", "id");
            logger.info(sql);
            JDBC.update(sql, id.toString());
            logger.info("Delete success");
        } catch (Exception e) {
            throw new RuntimeException("Cannot delete StockOut: " + e.getMessage());
        }
    }

    @Override
    public StockOut selectById(UUID id) {
        try {
            String sql = SQLBuilder.buildSQLSelect("StockOut", "id");
            logger.info(sql);
            List<StockOut> result = selectBySql(sql, id.toString());
            return result.isEmpty() ? null : result.get(0);
        } catch (Exception e) {
            throw new RuntimeException("Cannot select StockOut by ID: " + e.getMessage());
        }
    }

    @Override
    public StockOut selectByName(String name) {
        throw new UnsupportedOperationException("Selecting StockOut by name is not supported.");
    }

    @Override
    public List<StockOut> selectAll() {
        String sql = SQLBuilder.buildSQLSelectALL("StockOut");
        logger.info(sql);
        return selectBySql(sql);
    }

    public List<StockOut> selectByWarehouse(UUID warehouseUUID) {
        String sql = SQLBuilder.buildSQLSelect("StockOut", "idWarehouse");
        logger.info(sql);
        List<StockOut> list = selectBySql(sql, warehouseUUID.toString());
        return list == null || list.isEmpty() ? new ArrayList<>() : list;
    }

    public List<StockOut> selectByStatus(String status) {
        String sql = SQLBuilder.buildSQLSelect("StockOut", "status");
        logger.info(sql);
        List<StockOut> list = selectBySql(sql, status);
        return list == null || list.isEmpty() ? new ArrayList<>() : list;
    }

    public List<StockOut> searchStockOutByProductName(String keyword) {
        String sql = SQLBuilder.buildSQLSelectJoinLike(
                "StockOut",
                "INNER JOIN Product ON StockOut.idProduct = Product.id",
                "Product.Name"
        );
        logger.info(sql);
        List<StockOut> list = selectBySql(sql, "%" + keyword + "%");
        return list == null || list.isEmpty() ? new ArrayList<>() : list;
    }

    public List<StockOut> searchStockOutByProductNameAndWarehouse(String keyword, UUID warehouseUUID) {
        List<String> columns = List.of("Product.Name", "StockOut.idWarehouse");
        List<String> operators = List.of("LIKE", "=");
        List<String> keywords = List.of("%" + keyword + "%", warehouseUUID.toString());
        String sql = SQLBuilder.buildSQLSelectsLikeAnd(
                "StockOut INNER JOIN Product ON StockOut.idProduct = Product.id",
                columns,
                operators,
                keywords
        );
        logger.info(sql);
        List<StockOut> list = selectBySql(sql);
        return list == null || list.isEmpty() ? new ArrayList<>() : list;
    }

    public List<StockOut> searchStockOutByProductNameAndWarehouseAndStatus(String keyword, UUID warehouseUUID, String status) {
        List<String> columns = List.of("Product.Name", "StockOut.idWarehouse", "StockOut.status");
        List<String> operators = List.of("LIKE", "=", "=");
        List<String> keywords = List.of(
                keyword != null && !keyword.trim().isEmpty() ? "%" + keyword + "%" : "%",
                warehouseUUID != null ? warehouseUUID.toString() : null,
                status != null && !status.equals("All statuses") ? status : null
        );
        String sql = SQLBuilder.buildSQLSelectsLikeAnd(
                "StockOut INNER JOIN Product ON StockOut.idProduct = Product.id",
                columns,
                operators,
                keywords
        );
        logger.info(sql);
        List<StockOut> list = selectBySql(sql);
        return list == null || list.isEmpty() ? new ArrayList<>() : list;
    }

    public String getProductByUUID(UUID idProduct) {
        try {
            String sql = SQLBuilder.getNameByUUID("Product", "id");
            logger.info(sql);
            ResultSet rs = JDBC.query(sql, idProduct.toString());
            if (rs.next()) {
                return rs.getString("Name");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Cannot get product name: " + e.getMessage());
        }
    }

    public String getWarehouseByUUID(UUID idWarehouse) {
        try {
            String sql = SQLBuilder.getNameByUUID("Warehouse", "id");
            logger.info(sql);
            ResultSet rs = JDBC.query(sql, idWarehouse.toString());
            if (rs.next()) {
                return rs.getString("Name");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Cannot get warehouse name: " + e.getMessage());
        }
    }

    @Override
    protected List<StockOut> selectBySql(String sql, Object... args) {
        List<StockOut> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBC.query(sql, args);
                while (rs.next()) {
                    StockOut entity = new StockOut();
                    entity.setId(UUID.fromString(rs.getString("id")));
                    entity.setQuantity(rs.getInt("quantity"));
                    entity.setIdProduct(UUID.fromString(rs.getString("idProduct")));
                    entity.setIdWarehouse(UUID.fromString(rs.getString("idWarehouse")));
                    entity.setUsername(rs.getString("username"));
                    entity.setUpdatedDate(rs.getDate("updatedDate"));
                    entity.setStatus(EStatus.valueOf(rs.getString("status")));
                    list.add(entity);
                }
            } finally {
                if (rs != null) {
                    rs.getStatement().getConnection().close();
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error executing SQL query: " + ex.getMessage());
        }
        return list;
    }
    
    
}
