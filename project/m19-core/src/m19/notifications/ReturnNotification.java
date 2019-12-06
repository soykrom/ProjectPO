package m19.notifications;

public class ReturnNotification extends Notification {
    public ReturnNotification(String notification) {
        super(notification);
    }

    @Override
    public String getNotification() {
        return "ENTREGA: " + super.getNotification();
    }
}