package warehouse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import warehouse.bean.ECalcUnit;
import warehouse.entity.StockIn;
import warehouse.utils.ConvertDate;
import warehouse.utils.JDBC;
import warehouse.utils.SQLBuilder;

public class StockInDAO extends BaseDAO<StockIn, UUID>{

      private static final Logger logger = Logger.getLogger(StockInDAO.class.getName());
    
    @Override
    public void insert(StockIn entity) {
         try {
            String sql = SQLBuilder.buildSQLInsert("Stock", "Quantity", "CalcUnit", "IDSupplier","IDWareHouse","IDAccount","AddDate");
            logger.info(sql);
            JDBC.update(sql,
                    entity.getQuantity(),
                    entity.getCalcUnit(),
                    entity.getIdSupplier(),
//                    entity.getIdWarehouse(),
                    entity.getIdAccount(),
                    entity.getAddDate()
            );
            logger.info("Insert success");
        } catch (Exception e) {
            throw new RuntimeException("Cant insert missing information:" + e.getMessage());
        }
    }

    @Override
    public void update(StockIn entity) {
         try {
            String sql = SQLBuilder.buildSQLUpdate("Stock", "Quantity", "CalcUnit", "IDSupplier","IDWareHouse","IDAccount","AddDate","ID");
            logger.info(sql);
            JDBC.update(sql,
                    entity.getQuantity(),
                    entity.getCalcUnit(),
                    entity.getIdSupplier(),
//                    entity.getIdWarehouse(),
                    entity.getIdAccount(),
                    entity.getAddDate(),
                    entity.getId()
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
            StockIn entity = selectById(id);
            if (entity == null) {
                logger.severe("StockIn doesnt not exist cant delete product with id" + id);
            } else {
                JDBC.update(sql, id);
                logger.info("Delete success with product with product id:" + id);
            }
        } catch (Exception e) {
            throw new NullPointerException("Delete fail:" + e.getMessage());
        }
    }

    @Override
    public StockIn selectById(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); 
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
                    entity.setQuantity(Integer.parseInt(rs.getString("Quantity")));
                    entity.setIdSupplier(UUID.fromString(rs.getString("IDProduct")));
                    entity.setCalcUnit(ECalcUnit.valueOf(rs.getString("CalcUnit")));
//                    entity.setIdWarehouse(UUID.fromString(rs.getString("IDWarehouse")));
                    entity.setIdAccount(UUID.fromString(rs.getString("IDAccount")));
                    entity.setAddDate(ConvertDate.toDate(rs.getString("LastUpdate"),"YYYY-MM-DD"));
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

    @Override
    public UUID getUUIDByName(String table, String keyWord) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
