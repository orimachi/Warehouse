package warehouse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import warehouse.bean.ECalcUnit;
import warehouse.bean.ECategory;
import warehouse.entity.Product;
import warehouse.utils.JDBC;
import warehouse.utils.SQLBuilder;

public class ProductDAO extends BaseDAO<Product, String> {

    private static final Logger logger = Logger.getLogger(ProductDAO.class.getName());

    @Override
    public void insert(Product entity) {
        try {
            String sql = SQLBuilder.buildSQLInsert("Product", "Name", "CalcUnit", "Description", "IdSupplier", "Category");
            logger.info(sql);
            JDBC.update(sql,
                    entity.getName(),
                    entity.getCalcUnit(),
                    entity.getDescription(),
                    entity.getIdSupplier(),
                    entity.getCategory());
            logger.info("Insert success");
        } catch (Exception e) {
            throw new RuntimeException("Cant insert missing information:" + e.getMessage());
        }
    }

    @Override
    public void update(Product entity) {
        String sql = SQLBuilder.buildSQLUpdate("Product", "Name", "CalcUnit", "Description", "IdSupplier", "Category", "IDProduct");
        logger.info(sql);
        try {
            if (entity.getId().isEmpty() || entity.getId().isBlank()) {
                throw new IllegalArgumentException("ID missing cant update");
            } else {
                JDBC.update(sql,
                    entity.getName(),
                    entity.getCalcUnit(),
                    entity.getDescription(),
                    entity.getIdSupplier(),
                    entity.getCategory(),
                    entity.getId());
                logger.info("Update success");
            }
        } catch (Exception e) {
            throw new RuntimeException("Cant update missing information:" + e.getMessage());
        }

    }

    @Override
    public void delete(String id) {
        try {
            String sql = "DELETE FROM Product WHERE ID=?";
            Product product = selectById(id);
            if (product == null) {
                logger.severe("Product doesnt not exist cant delete product with id" + id);
            } else {
                JDBC.update(sql, id);
                logger.info("Delete success with product with product id:" + id);
            }
        } catch (Exception e) {
            throw new NullPointerException("Delete fail:" + e.getMessage());
        }
    }

    @Override
    public Product selectById(String id) {
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
                    entity.setId(rs.getString("ID"));
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

    public List<Product> selectByNameSupplier(String nameSupplier) {
        String sql = SQLBuilder.buildSQLSelect("Product", "NameSuppliers");
        logger.info(sql);
        return selectBySql(sql, nameSupplier);
    }

}
