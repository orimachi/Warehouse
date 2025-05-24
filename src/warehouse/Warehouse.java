package warehouse;

import warehouse.utils.Auth;
import warehouse.view.LoginJDialog;
import warehouse.view.MainJFrame;

public class Warehouse {

    public static void main(String[] args) {
        LoginJDialog login = new LoginJDialog(null, true);
        login.setVisible(true);
        if (Auth.isLogin()) {
            new MainJFrame().setVisible(true);
        } else {
            System.exit(0);
        }
    }
}
