package warehouse.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import warehouse.entity.Account;
import warehouse.utils.JDBC;

public class AccountDao extends BaseDAO<Account, String>{
    private static final Logger logger = Logger.getLogger(AccountDao.class.getName());
    
    @Override
    public void insert(Account entity) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void update(Account entity) {
        try {
            String sql = "UPDATE Account SET Password=? WHERE Username=?";
            JDBC.update(sql,entity.getPassword(),entity.getUsername());
        } catch (Exception e) {
            throw new NullPointerException("Update fail:" + e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
       try {
            String sql = "DELETE Account WHERE Username=?";
            JDBC.update(sql,id);
        } catch (Exception e) {
            throw new NullPointerException("Delete fail:" + e.getMessage());
        }
    }

    @Override
    public Account selectById(String id) {
         try {
            String sql = "SELECT * FROM Account WHERE Username=?";
            List<Account> list = this.selectBySql(sql, id);
            return list.isEmpty() ? list.get(0) : null;
        } catch (Exception e) {
            throw new NullPointerException("Cant find account with username:" + id);
        }
    }

    @Override
    public List<Account> selectAll() {
        String sql = "SELECT * FROM Account";
        return selectBySql(sql);
    }

    @Override
    protected List<Account> selectBySql(String sql, Object... args) {
        List<Account> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBC.query(sql, args);
                while(rs.next()){
                    Account entity=new Account();
                    entity.setUsername(rs.getString("username"));
                    entity.setPassword(rs.getString("password"));
                    list.add(entity);
                }
            } 
            finally{
                if(rs != null) {
                    rs.getStatement().getConnection().close();
                }
            }
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
    
    public boolean checkLogin(String username, String password) {
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            logger.info("Missing username or password");
            return false;
        } else {
            String sql = "SELECT * FROM Account WHERE Username=? AND Password=?";
            List<Account> list = this.selectBySql(sql, username, password);
            return !list.isEmpty();
        }
    }
}
