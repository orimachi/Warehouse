package warehouse.utils;

import warehouse.dao.AccountDAO;
import warehouse.entity.Account;


public class Auth {
   AccountDAO accountDAO = new AccountDAO();
    
    public static Account user = null;
    
    public static void clear(){
        Auth.user = null;
    }
    
    public static boolean isLogin(){
        return user == null ? true : false;
    }
    
    public static boolean isManager(){
        return true;
    }
    
    public Account getInformation(String username){
        if (isManager() == true) {
            Account infoCustomer = (Account) accountDAO.selectById(username);
            return infoCustomer;
        } 
            return null; 
    }
}
