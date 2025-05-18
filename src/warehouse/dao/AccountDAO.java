package warehouse.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import warehouse.bean.ERole;
import warehouse.entity.Account;
import warehouse.utils.JDBC;
import warehouse.utils.SQLBuilder;

public class AccountDAO extends BaseDAO<Account, String>{
    private static final Logger logger = Logger.getLogger(AccountDAO.class.getName());
    
    @Override
    public void insert(Account entity) {
        try {
            String sql = SQLBuilder.buildSQLInsert("Account","Username","Password" ,"Role");
            logger.info(sql);
            JDBC.update(sql,entity.getUsername(),entity.getPassword().hashCode(),entity.getRole());
        } catch (Exception e) {
            throw new NullPointerException("Update fail:" + e.getMessage());
        }
    }

    @Override
    public void update(Account entity) {
        try {
            String sql = SQLBuilder.buildSQLUpdate("Account","Password" ,"Role" ,"Username");
            logger.info(sql);
            JDBC.update(sql,entity.getPassword().hashCode(),entity.getRole(),entity.getUsername());
        } catch (Exception e) {
            throw new NullPointerException("Update fail:" + e.getMessage());
        }
    }

    @Override
    public void delete(String username) {
      throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Account selectById(String username) {
         try {
            String sql = SQLBuilder.buildSQLSelect("Account", "Username");
            List<Account> list = this.selectBySql(sql, username);
            return list.isEmpty() ? null : list.getFirst();
        } catch (Exception e) {
            throw new RuntimeException("Cant find account with username:" + username);
        }
    }
    
    @Override
    public Account selectByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Account> selectAll() {
        String sql = SQLBuilder.buildSQLSelectALL("Account");
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
                    entity.setRole(ERole.valueOf(rs.getString("role")));
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
    
    public boolean login(String username, String password) {
        String loginPassword = String.valueOf(password.hashCode());
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
