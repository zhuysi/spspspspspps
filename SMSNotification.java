public class SMSNotification implements NotificationStrategy {
    @Override
    public void sendNotification(String message) {
        // Логика отправки SMS уведомления.
        System.out.println("Sending SMS: " + message);
    }
}
