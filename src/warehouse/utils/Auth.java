package warehouse.utils;

import warehouse.dao.CustomerDAO;
import warehouse.entity.Customer;


public class Auth {
    CustomerDAO customerDAO = new CustomerDAO();
    
    public static Customer user = null;
    
    public static void clear(){
        Auth.user = null;
    }
    
    public static boolean isLogin(){
        return user == null ? true : false;
    }
    
    public static boolean isManager(){
        return true;
    }
    
    public Customer getInformation(String username){
        if (isManager() == true) {
            Customer infoCustomer = customerDAO.selectById(username);
            return infoCustomer;
        } 
            return null; 
    }
}
