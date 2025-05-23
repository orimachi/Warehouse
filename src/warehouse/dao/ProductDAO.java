package warehouse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import warehouse.bean.ECalcUnit;
import warehouse.bean.ECategory;
import warehouse.bean.EPositions;
import warehouse.entity.Product;
import warehouse.utils.JDBC;
import warehouse.utils.SQLBuilder;

public class ProductDAO extends BaseDAO<Product, UUID> {

    private static final Logger logger = Logger.getLogger(ProductDAO.class.getName());

    @Override
    public void insert(Product entity) {
        try {
            String sql = SQLBuilder.buildSQLInsert("Product", "Name", "CalcUnit", "Description", "IdSupplier", "Category");
            logger.info(sql);
            JDBC.update(sql,
                    entity.getName(),
                    entity.getCalcUnit().name(),
                    entity.getDescription(),
                    entity.getIdSupplier(),
                    entity.getCategory().name());
            logger.info("Insert success");
        } catch (Exception e) {
            throw new RuntimeException("Cant insert missing information:" + e.getMessage());
        }
    }

    @Override
    public void update(Product entity) {
        String sql = SQLBuilder.buildSQLUpdate("Product", "ID", "Name", "CalcUnit", "Description", "IdSupplier", "Category");
        logger.info(sql);
        try {
            JDBC.update(sql,
                    entity.getName(),
                    entity.getCalcUnit().name(),
                    entity.getDescription(),
                    entity.getIdSupplier(),
                    entity.getCategory().name(),
                    entity.getId().toString());
            logger.info("Update success");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Cant update missing information:" + e.getMessage());
        }

    }

    @Override
    public void delete(UUID id) {
        try {
            String sql = SQLBuilder.buildSQLDelete("Product", "ID");
            logger.info(sql);
            JDBC.update(sql, id);
        } catch (Exception e) {
            throw new NullPointerException("Delete fail:" + e.getMessage());
        }
    }

    @Override
    public Product selectById(UUID id) {
        String sql = SQLBuilder.buildSQLSelect("Product", "ID");
        logger.info(sql);
        List<Product> list = selectBySql(sql, id);
        return list.isEmpty() ? null : list.getFirst();
    }

    @Override
    public Product selectByName(String name) {
        String sql = SQLBuilder.buildSQLSelect("Product", "Name");
        logger.info(sql);
        List<Product> list = selectBySql(sql, name);
        return list.isEmpty() ? null : list.getFirst();
    }

    @Override
    public List<Product> selectAll() {
        String sql = SQLBuilder.buildSQLSelectALL("Product");
        logger.info(sql);
        return selectBySql(sql);
    }

    @Override
    protected List<Product> selectBySql(String sql, Object... args) {
        List<Product> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBC.query(sql, args);
                while (rs.next()) {
                    Product entity = new Product();
                    entity.setId(UUID.fromString(rs.getString("ID")));
                    entity.setName(rs.getString("Name"));
                    entity.setCalcUnit(ECalcUnit.valueOf(rs.getString("CalcUnit")));
                    entity.setDescription(rs.getString("Description"));
                    entity.setIdSupplier(UUID.fromString(rs.getString("IDSupplier")));
                    entity.setCategory(ECategory.valueOf(rs.getString("Category")));
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

    public List<Product> getProductByKey(String keyword) {
        String sql = SQLBuilder.buildSQLSelectLike("Product", "Name", keyword, EPositions.START);
        logger.info(sql);
        return selectBySql(sql);
    }

    public String getNameByUUID(UUID id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public UUID getUUIDByName(String name) {
        String sql = SQLBuilder.getUUIDByName("Product", "Name");
        logger.info(sql);
        Object result = JDBC.value(sql, name);
        return result == null ? null : UUID.fromString(result.toString());
    }
}
