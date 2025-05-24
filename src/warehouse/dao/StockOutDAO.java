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
            String sql = SQLBuilder.buildSQLInsert("Stock", "Quantity", "CalcUnit", "IDSupplier","IDWareHouse","IDAccount","AddDate");
            logger.info(sql);
            JDBC.update(sql,
                    entity.getQuantity(),
                    entity.getId()
            );
            logger.info("Insert success");
        } catch (Exception e) {
            throw new RuntimeException("Cant insert missing information:" + e.getMessage());
        }
    }

    @Override
    public void update(StockOut entity) {
       try {
            String sql = SQLBuilder.buildSQLUpdate("StockOut", "ID", "IDProduct" ,"Quantity", "Username","UpdatedDate","IDWarehouse", "Status");
            logger.info(sql);
            JDBC.update(sql,
                    entity.getIdProduct(),
                    entity.getQuantity(),
                    entity.getUsername(),
                    entity.getUpdatedDate(),
                    entity.getIdWarehouse().toString(),
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
            String sql = SQLBuilder.buildSQLDelete("StockOut", "ID");
            logger.info(sql);
            JDBC.update(sql, id);
        } catch (Exception e) {
            throw new NullPointerException("Delete fail:" + e.getMessage());
        }
    }

    @Override
    public StockOut selectById(UUID id) {
       try {
            String sql = SQLBuilder.buildSQLSelect("StockOut", "Id");
            List<StockOut> result = selectBySql(sql, id);
            return result.isEmpty() ? null : result.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error check output");
        }
    }

    @Override
    public StockOut selectByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<StockOut> selectAll() {
        String sql = SQLBuilder.buildSQLSelectALL("StockOut");
        return selectBySql(sql);
    }

     public List<StockOut> selectALLByStatusProcessing(EStatus status){
        String sql = SQLBuilder.buildSQLSelect("StockOut", "Status");
        logger.info(sql);
        List<StockOut> list = selectBySql(sql,status.name());
        return list == null || list.isEmpty() ? new ArrayList<>() : list;
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
                    entity.setId(UUID.fromString(rs.getString("ID")));
                    entity.setQuantity(Integer.parseInt(rs.getString("Quantity")));
                    entity.setIdProduct(UUID.fromString(rs.getString("IDProduct")));
                    entity.setUsername(rs.getString("Username"));
                    entity.setStatus(EStatus.valueOf(rs.getString("Status")));
                    entity.setUpdatedDate(rs.getDate("UpdatedDate"));
                    entity.setIdWarehouse(UUID.fromString(rs.getString("IDWarehouse")));
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
