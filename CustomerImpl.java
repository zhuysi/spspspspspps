import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class CustomerImpl extends Customer {
    private List<Clothing> order = new ArrayList<>();
    private PaymentStrategy paymentStrategy;

    public CustomerImpl(String name, String surname, NotificationStrategy notificationStrategy) {
        super(name, notificationStrategy);
    }

    @Override
    public void addClothingToOrder(Clothing clothing) {
        order.add(clothing);
        notifyOrderStatus("Added new clothes to the order");
    }

    @Override
    public void viewOrder() {
        System.out.println("Your order:");
        for (Clothing clothing : order) {
            System.out.println("Тип: " + clothing.getClass().getSimpleName() +
                    ", Brand: " + clothing.getBrand() +
                    ", Color: " + clothing.getColor() +
                    ", Size: " + clothing.getSize() +
                    ", Price: $" + clothing.getPrice());
        }
    }

    @Override
    public void modifyOrderSize(String clothingId, String newSize) {
        for (Clothing clothing : order) {
            if (clothing.getClass().getSimpleName().equalsIgnoreCase(clothingId)) {
                clothing.setSize(newSize);
                notifyOrderStatus("Clothing size changed");
                return;
            }
        }
        System.out.println("Clothing with an ID " + clothingId + " not found in your order.");
    }

    @Override
    public String calculateOrderTotal() {
        double total = 0;
        for (Clothing clothing : order) {
            total += clothing.getPrice();
        }
        return String.valueOf(total);
    }

    @Override
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    @Override
    public boolean makePayment(String calculateOrderTotal) {
        if (paymentStrategy != null) {
            double total = Double.parseDouble(calculateOrderTotal);
            paymentStrategy.pay(total);
            notifyOrderStatus("Payment of the order was successful");
            return true;
        }
        return false;
    }

    @Override
    public void notifyOrderStatus(String s) {
        ClothingObservable.notifyObservers(s);
    }

    @Override
    public String chooseClothingType() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the type of clothing (jeans/cap/shirt):");
        return scanner.nextLine();
    }

    @Override
    public void modifyOrderColor(String newColor) {

    }

    @Override
    public void notifyPaymentStatus(boolean paymentStatus) {
        if (paymentStatus) {
            System.out.println("Notification: Payment of the order was successful.");
        } else {
            System.out.println("Notification: Error when paying for the order.");
        }
    }
}
