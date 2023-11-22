import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Observer> observers = new ArrayList<>();
    private int orderId;
    private List<Clothing> clothes;
    private PaymentStrategy paymentStrategy;

    public Order(int orderId) {
        this.orderId = orderId;
        this.clothes = new ArrayList<>();
    }

    public void addClothing(Clothing clothing) {
        clothes.add(clothing);
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public List<Clothing> getClothes() {
        return clothes;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public boolean makePayment() {
        if (paymentStrategy != null) {
            // Предполагается, что calculateOrderTotal возвращает сумму заказа
            double totalAmount = calculateOrderTotal();
            paymentStrategy.pay(totalAmount);
            return true;
        }
        return false;
    }

    public void modifyClothing(int clothingIndex, ClothingDecorator decorator) {
        if (clothingIndex >= 0 && clothingIndex < clothes.size()) {
            Clothing clothingToModify = clothes.get(clothingIndex);
            Clothing modifiedClothing = decorator.decorate(clothingToModify);
            clothes.set(clothingIndex, modifiedClothing);
            System.out.println("The clothes in the order have been changed.");
        } else {
            System.out.println("Incorrect clothing index in the order.");
        }
    }


    private double calculateOrderTotal() {
        double total = 0;
        for (Clothing clothing : clothes) {
            total += clothing.getPrice();
        }
        return total;
    }

    public void notifyPaymentStatus(boolean paymentStatus) {
        for (Observer observer : observers) {
            observer.notifyPaymentStatus(paymentStatus);
        }
    }
}
