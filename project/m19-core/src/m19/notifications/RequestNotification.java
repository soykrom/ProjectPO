package m19.notifications;

public class RequestNotification extends Notification {
    public RequestNotification(String notification) {
        super(notification);
    }

    @Override
    public String getNotification() {
        return "REQUISICAO: " + super.getNotification();
    }
}