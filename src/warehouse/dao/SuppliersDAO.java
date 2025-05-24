package warehouse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import warehouse.entity.Suppliers;
import warehouse.utils.JDBC;
import warehouse.utils.SQLBuilder;

public class SuppliersDAO extends BaseDAO<Suppliers, UUID> {
  
    private static final Logger logger = Logger.getLogger(SuppliersDAO.class.getName());
    
    @Override
    public void insert(Suppliers entity) {
        try {
            String sql = SQLBuilder.buildSQLInsert("Suppliers", "Name", "Address", "PhoneNumber");
            logger.info(sql);
            JDBC.update(sql,
                    entity.getName(),
                    entity.getAddress(),
                    entity.getPhoneNumber());
            logger.info("Insert success");
        } catch (Exception e) {
            throw new RuntimeException("Cant insert missing information:" + e.getMessage());
        }
    }

    @Override
    public void update(Suppliers entity) {
         try {
            String sql = SQLBuilder.buildSQLUpdate("Suppliers", "ID","Name" ,"Address", "PhoneNumber");
            logger.info(sql);
            JDBC.update(sql,
                    entity.getName(),
                    entity.getAddress(),
                    entity.getPhoneNumber(),
                    entity.getId());
            logger.info("Update success");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(UUID id) {
       try {
            String sql = SQLBuilder.buildSQLDelete("Suppliers", "IDSuppliers");
            logger.info(sql);
            JDBC.update(sql, id);
            logger.info("Delete success");
        } catch (Exception e) {
            throw new RuntimeException("Cant insert missing information:" + e.getMessage());
        }
    }

    @Override
    public Suppliers selectById(UUID id) {
        String sql = SQLBuilder.buildSQLSelect("Suppliers", "ID");
        logger.info(sql);
        List<Suppliers> list = selectBySql(sql, id);
        return list.isEmpty() ? null : list.getFirst();
    }
    
    @Override
    public Suppliers selectByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    
    @Override
    public List<Suppliers> selectAll() {
        String sql = SQLBuilder.buildSQLSelectALL("Suppliers");
        logger.info(sql);
        return selectBySql(sql);
    }
    

    @Override
    protected List<Suppliers> selectBySql(String sql, Object... args) {
        List<Suppliers> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBC.query(sql, args);
                while (rs.next()) {
                    Suppliers entity = new Suppliers();
                    entity.setId(UUID.fromString(rs.getString("ID")));
                    entity.setName(rs.getString("Name"));
                    entity.setPhoneNumber(rs.getString("PhoneNumber"));
                    entity.setAddress(rs.getString("Address"));
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
    
    public UUID getUUIDByName(String keyWord) {
        String sql = SQLBuilder.getUUIDByName("Suppliers", "Name");
        logger.info(sql);
        Object result = JDBC.value(sql,keyWord);
        logger.info(result.toString());
        return result == null ? null : UUID.fromString(result.toString());
    }
    
    public String getNameByUUID(UUID id) {
        String sql = SQLBuilder.getNameByUUID("Suppliers", "ID");
        logger.info(sql);
        Object result = JDBC.value(sql,id);
        return result == null ? null : result.toString();
    }
}
