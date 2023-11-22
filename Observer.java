// 6. Observer
public interface Observer {
    void update(String message);

    void setNotificationStrategy(NotificationStrategy notificationStrategy);

    void modifyOrderType(String newClothingType);

    void notifyPaymentStatus(boolean paymentStatus);
}
