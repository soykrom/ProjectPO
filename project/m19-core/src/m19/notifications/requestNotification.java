package m19.notifications;

public class RequestNotification extends Notification {
    @Override
    public String getNotification(){
        return "REQUISIÇÃO: " + super.getNotification();
    }
}