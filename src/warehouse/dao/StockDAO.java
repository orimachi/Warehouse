package warehouse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import warehouse.bean.ECalcUnit;
import warehouse.entity.Stock;
import warehouse.utils.JDBC;
import warehouse.utils.SQLBuilder;

public class StockDAO extends BaseDAO<Stock, UUID>{

    private static final Logger logger = Logger.getLogger(StockDAO.class.getName());
    
    @Override
    public void insert(Stock entity) {
         try {
            String sql = SQLBuilder.buildSQLInsert("Stock", "IDProduct", "Quantity", "CalcUnit", "IDWarehouse","LastUpdate","IDAccount");
            logger.info(sql);
            JDBC.update(sql,
                    entity.getIdProduct(),
                    entity.getQuantity(),
                    entity.getIdWareHouse()
            );
            logger.info("Insert success");
        } catch (Exception e) {
            throw new RuntimeException("Cant insert missing information:" + e.getMessage());
        }
    }

    @Override
    public void update(Stock entity) {
        try {
            String sql = SQLBuilder.buildSQLUpdate("Stock", "IDProduct", "Quantity", "CalcUnit", "IDWarehouse","LastUpdate","IDAccount","IDStock");
            logger.info(sql);
            JDBC.update(sql,
                    entity.getIdProduct(),
                    entity.getQuantity(),
                    entity.getIdWareHouse(),
                    entity.getId());
            logger.info("Update success");
        } catch (Exception e) {
            throw new RuntimeException("Cant insert missing information:" + e.getMessage());
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            String sql = "DELETE FROM Stock WHERE ID=?";
            Stock product = selectById(id);
            if (product == null) {
                logger.severe("Stock doesnt not exist cant delete product with id" + id);
            } else {
                JDBC.update(sql, id);
                logger.info("Delete success with product with product id:" + id);
            }
        } catch (Exception e) {
            throw new NullPointerException("Delete fail:" + e.getMessage());
        }
    }

    @Override
    public Stock selectById(UUID id) {
        String sql = SQLBuilder.buildSQLSelect("Stock", "ID");
        logger.info(sql);
        if (id != null) {
            List<Stock> list = selectBySql(sql, id);
            return list.isEmpty() ? null : list.getFirst();
        } else {
            logger.info("Missing information:" + id);
            return null;
        }
    }

    @Override
    public Stock selectByName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Stock> selectAll() {
        String sql = SQLBuilder.buildSQLSelectALL("Stock");
        return selectBySql(sql);
    }

    @Override
    protected List<Stock> selectBySql(String sql, Object... args) {
       List<Stock> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBC.query(sql, args);
                while (rs.next()) {
                    Stock entity = new Stock();
                    entity.setId(UUID.fromString(rs.getString("ID")));
                    entity.setIdProduct(UUID.fromString(rs.getString("IDProduct")));
                    entity.setQuantity(Integer.parseInt(rs.getString("Quantity")));
                    entity.setIdWareHouse(UUID.fromString(rs.getString("IDWarehouse")));
                    list.add(entity);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
    
     public String getProductByUUID(UUID id) {
        String sql = SQLBuilder.getNameByUUID("Product", "ID");
//        logger.info(sql);
        Object result = JDBC.value(sql,id);
        return result == null ? null : result.toString();
    }
     
     public String getWarehouseByUUID(UUID id) {
        String sql = SQLBuilder.getNameByUUID("Warehouse", "ID");
//        logger.info(sql);
        Object result = JDBC.value(sql,id);
        return result == null ? null : result.toString();
    }
     
    public List<Stock> selectByWarehouse(UUID idWarehouse) {
        List<Stock> list = new ArrayList<>();
        String sql = SQLBuilder.buildSQLSelect("Stock", "idWareHouse");
//        logger.info(sql);
        try {
            ResultSet rs = JDBC.query(sql, idWarehouse);
            while (rs.next()) {
                Stock stock = new Stock();
                stock.setId(UUID.fromString(rs.getString("id")));
                stock.setIdProduct(UUID.fromString(rs.getString("idProduct")));
                stock.setIdWareHouse(UUID.fromString(rs.getString("idWareHouse")));
                stock.setQuantity(rs.getInt("quantity"));
                list.add(stock);
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
    
 
    public List<Stock> searchStockByProductName(String keyword) {
        List<Stock> list = new ArrayList<>();

        String joinClause = """
            JOIN Product p ON s.IDProduct = p.ID
            JOIN Warehouse w ON s.IDWarehouse = w.ID
        """;

        String sql = SQLBuilder.buildSQLSelectJoinLike("Stock s", joinClause, "p.Name");

        try {
            ResultSet rs = JDBC.query(sql, "%" + keyword + "%");
            while (rs.next()) {
                Stock entity = new Stock();
                entity.setId(UUID.fromString(rs.getString("ID")));
                entity.setIdProduct(UUID.fromString(rs.getString("IDProduct")));
                entity.setQuantity(rs.getInt("Quantity"));
                entity.setIdWareHouse(UUID.fromString(rs.getString("IDWarehouse")));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
    
    public List<Stock> searchStockByProductNameAndWarehouse(String keyword, UUID warehouseId) {
        String sql = """
            SELECT s.*
            FROM Stock s
            JOIN Product p ON s.IDProduct = p.ID
            WHERE p.Name LIKE ? AND s.IDWarehouse = ?
        """;
        return selectBySql(sql, "%" + keyword + "%", warehouseId);
    }
    
    public UUID getProductUUIDByName(String productName) {
        try {
            String sql = SQLBuilder.getUUIDByName("Product", "Name");
            logger.info(sql);
            ResultSet rs = JDBC.query(sql, productName);
            if (rs.next()) {
                return UUID.fromString(rs.getString("Id"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Cannot get product UUID by name: " + e.getMessage());
        }
    }
    



}
