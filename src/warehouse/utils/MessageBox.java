package warehouse.utils;

import java.awt.Window;
import javax.swing.JFrame;
import warehouse.component.notification.MessageDialog;
import warehouse.component.notification.Notification;

public class MessageBox {

    public static void alert(Window parent, String message) {
        Notification notification = new Notification(parent, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, message);
        notification.showNotification();
    }

    public static void infomation(Window parent, String message) {
        Notification notification = new Notification(parent, Notification.Type.INFO, Notification.Location.TOP_RIGHT, message);
        notification.showNotification();
    }

    public static void error(Window parent, String message) {
        Notification notification = new Notification(parent, Notification.Type.ERROR, Notification.Location.TOP_RIGHT, message);
        notification.showNotification();
    }

    public static void success(Window parent, String message) {
        Notification notification = new Notification(parent, Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, message);
        notification.showNotification();
    }

    public static boolean confirm(JFrame parent, String title, String message) {
        MessageDialog messageDialog = new MessageDialog(parent);
        messageDialog.showMessage(title, message);
        return messageDialog.getMessageType() == MessageDialog.MessageType.OK ? true : false;
    }
}
