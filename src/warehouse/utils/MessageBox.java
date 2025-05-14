package warehouse.utils;

import java.awt.Component;
import javax.swing.JOptionPane;

public class MessageBox {
     public static void alert(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, 
                "WareHouse Managerment:", JOptionPane.ERROR_MESSAGE);
    }
     
     public static void information(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, 
                "WareHouse Managerment:", JOptionPane.INFORMATION_MESSAGE);
    }
     
      public static boolean confirm(Component parent, String message) {
        int result = JOptionPane.showConfirmDialog(parent, message, 
                "WareHouse Managerment", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }
}
