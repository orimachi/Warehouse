package warehouse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import warehouse.entity.Warehouse;
import warehouse.utils.JDBC;
import warehouse.utils.SQLBuilder;

public class WarehouseDAO extends BaseDAO<Warehouse, UUID> {

    private static final Logger logger = Logger.getLogger(WarehouseDAO.class.getName());

    @Override
    public void insert(Warehouse entity) {
        try {
            String sql = SQLBuilder.buildSQLInsert("Warehouse", "Name", "Address", "PhoneNumber");
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
    public void update(Warehouse entity) {
        try {
            String sql = SQLBuilder.buildSQLUpdate("Warehouse", "Name", "Address", "PhoneNumber", "ID");
            logger.info(sql);
            if (entity != null) {
                JDBC.update(sql,
                        entity.getName(),
                        entity.getAddress(),
                        entity.getPhoneNumber(),
                        entity.getId());
                logger.info("Update success");
            }
        } catch (Exception e) {
            throw new RuntimeException("Cant update missing information:" + e.getMessage());
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            String sql = SQLBuilder.buildSQLDelete("Warehouse", "ID");
            logger.info(sql);
            Warehouse warehouse = selectById(id);
            if (id != null && warehouse != null) {
                JDBC.update(sql, id);
                logger.info("Update success");
            } else {
                logger.info("Missing id or warehouse doesn't exist");
            }
        } catch (Exception e) {
            throw new RuntimeException("Cant delete:" + e.getMessage());
        }
    }

    @Override
    public Warehouse selectById(UUID id) {
        String sql = SQLBuilder.buildSQLSelect("Warehouse", "ID");
        logger.info(sql);
        if (id != null) {
            List<Warehouse> list = selectBySql(sql, id);
            return list.isEmpty() ? null : list.getFirst();
        } else {
            logger.info("Missing information:" + id);
            return null;
        }
    }

    @Override
    public Warehouse selectByName(String name) {
        String sql = SQLBuilder.buildSQLSelect("Warehouse", "Name");
        logger.info(sql);
        if (name.isEmpty()) {
            logger.info("Missing information:" + name);
            return null;
        } else {
            List<Warehouse> list = selectBySql(sql, name);
            return list.isEmpty() ? null : list.getFirst();
        }
    }

    @Override
    public List<Warehouse> selectAll() {
        String sql = SQLBuilder.buildSQLSelectALL("Warehouse");
        logger.info(sql);
        return selectBySql(sql);
    }

    @Override
    protected List<Warehouse> selectBySql(String sql, Object... args) {
        List<Warehouse> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBC.query(sql, args);
                while (rs.next()) {
                    Warehouse entity = new Warehouse();
                    entity.setId(UUID.fromString(rs.getString("ID")));
                    entity.setName(rs.getString("Name"));
                    entity.setAddress(rs.getString("Address"));
                    entity.setPhoneNumber(rs.getString("PhoneNumber"));
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

    public UUID getUUIDByName(String name) {
        String sql = SQLBuilder.getUUIDByName("Warehouse", "Name");
        logger.info(sql);
        Object result = JDBC.value(sql, name);
        return result == null ? null : UUID.fromString(result.toString());
    }

    public String getNameByUUID(UUID id) {
        String sql = SQLBuilder.getNameByUUID("Warehouse", "ID");
        logger.info(sql);
        Object result = JDBC.value(sql, id);
        return result == null ? null : result.toString();
    }

    public List<Warehouse> selectByNameKeyWord(String name) {
        String sql = SQLBuilder.getEntityByName("Product", "Name");
        logger.info(sql);
        List<Warehouse> list = selectBySql(sql, "%" + name + "%");
        return list.isEmpty() ? null : list;
    }
}
