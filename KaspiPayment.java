public class KaspiPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Pay by Kaspi: $" + amount);
    }
}
