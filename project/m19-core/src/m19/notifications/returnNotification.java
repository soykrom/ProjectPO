package m19.notifications;

public class ReturnNotification extends Notification {
    @Override
    public String getNotification(){
        return "ENTREGA: " + super.getNotification();
    }
}