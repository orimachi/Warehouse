package warehouse.dao;

import com.whmanagement.entity.Product;
import com.whmanagement.utils.JDBC;
import com.whmanagement.utils.SQLBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ProductDAO extends BaseDAO<Product, String> {

    private static final Logger logger = Logger.getLogger(ProductDAO.class.getName());

    @Override
    public void insert(Product entity) {
        try {
            String sql = SQLBuilder.buildSQLInsert("Product", "ID", "Name", "Currency", "Price", "InputPrice", "Description", "NameSuppliers");
            logger.info(sql);
            JDBC.update(sql,
                    entity.getId(),
                    entity.getName(),
                    entity.getCurrency(),
                    entity.getPrice(),
                    entity.getInputPrice(),
                    entity.getDescription(),
                    entity.getNameSuppliers());
            logger.info("Insert success");
        } catch (Exception e) {
            logger.severe("Cant insert missing information:" + e.getMessage());
        }
    }

    @Override
    public void update(Product entity) {
        String sql = SQLBuilder.buildSQLUpdate("Product", "Name", "Currency", "Price", "InputPrice", "Description", "NameSuppliers", "ID");
        logger.info(sql);
        try {
            if (entity.getId().isEmpty() || entity.getId().isBlank()) {
                throw new IllegalArgumentException("ID missing cant update");
            } else {
                JDBC.update(sql,
                        entity.getName(),
                        entity.getCurrency(),
                        entity.getPrice(),
                        entity.getInputPrice(),
                        entity.getDescription(),
                        entity.getNameSuppliers(),
                        entity.getId());
                logger.info("Update success");
            }
        } catch (Exception e) {
            logger.severe("Error when update:" + e.getMessage());
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
        String sql = "SELECT * FROM Product WHERE ID=?";
        List<Product> list = selectBySql(sql, id);
        return list.isEmpty() ? list.getFirst() : null;
    }

    @Override
    public List<Product> selectAll() {
        String sql = "SELECT * FROM Product";
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
                    entity.setId(rs.getString("MaHH"));
                    entity.setName(rs.getString("TenHH"));
                    entity.setCurrency(rs.getString("DVT"));
                    entity.setPrice(rs.getDouble("DonGia"));
                    entity.setInputPrice(rs.getDouble("GiaNhap"));
                    entity.setDescription(rs.getString("MoTa"));
                    entity.setNameSuppliers(rs.getString("NameSuppliers"));
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
