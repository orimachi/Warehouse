package warehouse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import warehouse.bean.ECalcUnit;
import warehouse.entity.Stock;
import warehouse.utils.ConvertDate;
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
                    entity.getCalcUnit(),
                    entity.getIdWareHouse());
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
                    entity.getCalcUnit(),
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
        throw new UnsupportedOperationException("Not supported yet."); 
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
                    entity.setCalcUnit(ECalcUnit.valueOf(rs.getString("CalcUnit")));
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
    
}
