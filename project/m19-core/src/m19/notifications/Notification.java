package m19.notifications;

import java.io.Serializable;

public class Notification implements Serializable {
    private String _notification;

    public Notification(String notification) {
        _notification = notification;
    }

    public String getNotification() {
        return _notification;
    }
}