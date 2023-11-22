public abstract class Customer implements Observer {
    private String name;
    private NotificationStrategy notificationStrategy;

    public Customer(String name, NotificationStrategy notificationStrategy) {
        this.name = name;
        this.notificationStrategy = notificationStrategy;
    }

    @Override
    public void update(String message) {
        System.out.println(name + ", you have a new notification: " + message);
        notificationStrategy.sendNotification("You have a new notification: " + message);
    }

    public abstract void addClothingToOrder(Clothing clothing);

    public abstract void viewOrder();

    public abstract void modifyOrderSize(String clothingId, String newSize);

    public abstract String calculateOrderTotal();

    public abstract void setPaymentStrategy(PaymentStrategy paymentStrategy);

    public abstract boolean makePayment(String calculateOrderTotal);

    public abstract void notifyOrderStatus(String s);

    public abstract String chooseClothingType();

    public abstract void modifyOrderColor(String newColor);

    public abstract void modifyOrderSize(String newSize);
}
