package warehouse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import warehouse.bean.EStatus;
import warehouse.entity.StockIn;
import warehouse.utils.JDBC;
import warehouse.utils.SQLBuilder;

public class StockInDAO extends BaseDAO<StockIn, UUID>{

    private static final Logger logger = Logger.getLogger(StockInDAO.class.getName());
    
    @Override
    public void insert(StockIn entity) {
         try {
            String sql = SQLBuilder.buildSQLInsert("StockIn", "IDProduct", "Quantity", "IDSupplier","IDWareHouse","Username","UpdatedDate", "Status");
            logger.info(sql);
            JDBC.update(sql,
                    entity.getIdProduct(),
                    entity.getQuantity(),
                    entity.getIdSupplier(),
                    entity.getIdWarehouse(),
                    entity.getUsername(),
                    entity.getUpdatedDate(),
                    entity.getStatus().name()
            );
            logger.info("Insert success");
        } catch (Exception e) {
            throw new RuntimeException("Cant insert missing information:" + e.getMessage());
        }
    }

    @Override
    public void update(StockIn entity) {
         try {
            String sql = SQLBuilder.buildSQLUpdate("StockIn", "ID", "IDProduct" ,"Quantity","IDSupplier","IDWareHouse","Username","UpdatedDate", "Status");
            logger.info(sql);
            JDBC.update(sql,
                    entity.getIdProduct(),
                    entity.getQuantity(),
                    entity.getIdSupplier(),
                    entity.getIdWarehouse(),
                    entity.getUsername(),
                    entity.getUpdatedDate(),
                    entity.getStatus().name(),
                    entity.getId().toString()
            );
            logger.info("Update success");
        } catch (Exception e) {
            throw new RuntimeException("Cant update missing information:" + e.getMessage());
        }
    }

    @Override
    public void delete(UUID id) {
         try {
            String sql = SQLBuilder.buildSQLDelete("StockIn", "ID");
            JDBC.update(sql, id);
        } catch (Exception e) {
            throw new NullPointerException("Delete fail:" + e.getMessage());
        }
    }

    @Override
    public StockIn selectById(UUID id) {
        try {
            String sql = SQLBuilder.buildSQLSelect("StockIn", "Id");
            List<StockIn> result = selectBySql(sql, id);
            return result.isEmpty() ? null : result.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error check output");
        }
    }

    @Override
    public StockIn selectByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<StockIn> selectAll() {
        String sql = SQLBuilder.buildSQLSelectALL("StockIn");
        return selectBySql(sql);
    }

    public List<StockIn> selectALLByStatusProcessing(EStatus status){
        String sql = SQLBuilder.buildSQLSelect("StockIn", "Status");
        logger.info(sql);
        List<StockIn> list = selectBySql(sql,status.name());
        return list == null || list.isEmpty() ? new ArrayList<>() : list;
    }
    
    @Override
    protected List<StockIn> selectBySql(String sql, Object... args) {
        List<StockIn> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBC.query(sql, args);
                while (rs.next()) {
                    StockIn entity = new StockIn();
                    entity.setId(UUID.fromString(rs.getString("ID")));
                    entity.setIdSupplier(UUID.fromString(rs.getString("IDSupplier")));
                    entity.setIdWarehouse(UUID.fromString(rs.getString("IDWarehouse")));
                    entity.setQuantity(Integer.parseInt(rs.getString("Quantity")));
                    entity.setIdProduct(UUID.fromString(rs.getString("IDProduct")));
                    entity.setStatus(EStatus.valueOf(rs.getString("Status")));
                    entity.setUsername(rs.getString("Username"));
                    entity.setUpdatedDate(rs.getDate("UpdatedDate"));
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
}
