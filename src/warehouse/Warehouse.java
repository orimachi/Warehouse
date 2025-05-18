/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package warehouse;

import warehouse.utils.Auth;
import warehouse.view.LoginJDialog;
import warehouse.view.MainJFrame;

/**
 *
 * @author ADMIN
 */
public class Warehouse {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LoginJDialog login = new LoginJDialog(null, true);
        login.setVisible(true);
        if(Auth.isLogin()){
            new MainJFrame().setVisible(true);
        } else {
            System.exit(0);
        }
    }
    
}
