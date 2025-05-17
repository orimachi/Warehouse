package warehouse.utils;

import warehouse.bean.ERole;
import warehouse.dao.AccountDAO;
import warehouse.entity.Account;

public class Auth {

    AccountDAO accountDAO = new AccountDAO();

    public static Account user = null;

    public static void clear() {
        Auth.user = null;
    }

    public static boolean isLogin() {
        return user != null ;
    }

    public static boolean isManager() {
        return user.getRole() != ERole.STAFF;
    }

    public Account getInformation(String username) {
        return accountDAO.selectById(username);
    }
}
