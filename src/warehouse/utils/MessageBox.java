package warehouse.utils;

import java.awt.Component;
import javax.swing.JFrame;
import warehouse.component.notification.MessageDialog;
import warehouse.component.notification.Notification;

public class MessageBox {

    public static void warning(Component parent, String message) {
        Notification notification = new Notification(parent, Notification.Type.WARNING, Notification.Location.BOTTOM_RIGHT, message);
        notification.showNotification();
    }

    public static void infomation(Component parent, String message) {
        Notification notification = new Notification(parent, Notification.Type.INFO, Notification.Location.BOTTOM_RIGHT, message);
        notification.showNotification();
    }

    public static void error(Component parent, String message) {
        Notification notification = new Notification(parent, Notification.Type.ERROR, Notification.Location.BOTTOM_RIGHT, message);
        notification.showNotification();
    }

    public static void success(Component parent, String message) {
        Notification notification = new Notification(parent, Notification.Type.SUCCESS, Notification.Location.BOTTOM_RIGHT, message);
        notification.showNotification();
    }

    public static boolean confirm(JFrame parent, String title, String message) {
        MessageDialog messageDialog = new MessageDialog(parent);
        messageDialog.showMessage(title, message);
        return messageDialog.getMessageType() == MessageDialog.MessageType.OK ? true : false;
    }
}
