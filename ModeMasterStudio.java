import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ModeMasterStudio {
    private static ClothingStore store = ClothingStore.getInstance();
    private static ClothingFactory factory = new ClothingFactoryImpl();
    private static List<Order> orders = new ArrayList<>();
    private static int orderIdCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ClothingStore store = ClothingStore.getInstance();
        ClothingFactory factory = new ClothingFactoryImpl();
        DisplayAdapter displayAdapter = new ClothingDisplayAdapter(factory);

        System.out.println("Welcome to the clothing store!");
        System.out.println("How would you like to receive notifications?");
        System.out.println("1. SMS");
        System.out.println("2. Email");

        int notificationChoice;
        do {
            System.out.print("");
            notificationChoice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (notificationChoice) {
                case 1:
                    System.out.println("You chose to receive notifications via SMS.");
                    break;
                case 2:
                    System.out.println("You chose to receive notifications via Email.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        } while (notificationChoice != 1 && notificationChoice != 2);


        int choice;
        do {
            System.out.println("\nChoose an action:");
            System.out.println("1. Order clothing");
            System.out.println("2. View order");
            System.out.println("3. Modify order");
            System.out.println("4. Buy order");
            System.out.println("0. Exit");

            choice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (choice) {
                case 1:
                    orderClothing(scanner);
                    break;
                case 2:
                    viewOrder(scanner);
                    break;
                case 3:
                    modifyOrder(scanner);
                    break;
                case 4:
                    buyOrder(scanner);
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Incorrect choice. Please try again.");
                    break;
            }
        } while (choice != 0);
    }

    private static void orderClothing(Scanner scanner) {
        System.out.println("\n--- Clothing Order ---");
        Clothing customClothing = createCustomClothing(scanner);

        int orderId = generateOrderId();
        Order order = new Order(orderId);
        order.addClothing(customClothing);

        orders.add(order);

        System.out.println("User created a custom order with ID " + orderId);
    }

    private static Clothing createCustomClothing(Scanner scanner) {
        System.out.println("Choose clothing type:");
        System.out.println("1. Jeans");
        System.out.println("2. Cap");
        System.out.println("3. Shirt");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer

        String type;
        switch (choice) {
            case 1:
                type = "jeans";
                break;
            case 2:
                type = "cap";
                break;
            case 3:
                type = "shirt";
                break;
            default:
                System.out.println("Incorrect clothing type choice. Using default type (jeans).");
                type = "jeans";
                break;
        }

        System.out.println("Enter color:");
        String color = scanner.nextLine();

        System.out.println("Enter size:");
        String size = scanner.nextLine();

        System.out.println("Enter brand:");
        String brand = scanner.nextLine();

        System.out.println("Enter price:");
        double price = scanner.nextDouble();

        return new ClothingFactoryImpl().createClothing(type, color, size, brand, price);
    }

    private static int generateOrderId() {
        return orderIdCounter++;
    }

    private static void viewOrder(Scanner scanner) {
        System.out.println("\n--- View Order ---");
        System.out.println("Enter the order ID to view:");
        int orderId = scanner.nextInt();

        Order orderToDisplay = findOrderById(orderId);
        if (orderToDisplay != null) {
            displayOrder(orderToDisplay);
        } else {
            System.out.println("Order with ID " + orderId + " not found.");
        }
    }

    private static void buyOrder(Scanner scanner) {
        System.out.println("\n--- Order Payment ---");
        System.out.println("Enter the order ID you want to pay for:");
        int orderId = scanner.nextInt();

        Order orderToBuy = findOrderById(orderId);
        if (orderToBuy != null) {
            System.out.println("Choose payment method:");
            System.out.println("1. Credit Card");
            System.out.println("2. Kaspi");

            int paymentChoice = scanner.nextInt();

            PaymentStrategy paymentStrategy = null;
            switch (paymentChoice) {
                case 1:
                    paymentStrategy = new CreditCardPayment();
                    break;
                case 2:
                    paymentStrategy = new KaspiPayment();
                    break;
                default:
                    System.out.println("Incorrect payment method choice.");
                    return;
            }

            orderToBuy.setPaymentStrategy(paymentStrategy);
            boolean paymentResult = orderToBuy.makePayment();

            if (paymentResult) {
                orderToBuy.notifyPaymentStatus(true);
                System.out.println("Order payment successful.");
                orders.remove(orderToBuy); // Remove the order after successful payment
            } else {
                orderToBuy.notifyPaymentStatus(false);
                System.out.println("Error in order payment.");
            }
        } else {
            System.out.println("Order with ID " + orderId + " not found.");
        }
    }

    private static void modifyOrder(Scanner scanner) {
        System.out.println("\n--- Modify Order ---");
        System.out.println("Enter the order ID you want to modify:");
        int orderId = scanner.nextInt();

        Order orderToModify = findOrderById(orderId);
        if (orderToModify != null) {
            System.out.println("Choose clothing in the order to modify (enter index):");
            displayOrder(orderToModify);
            int clothingIndex = scanner.nextInt();

            if (clothingIndex >= 0 && clothingIndex < orderToModify.getClothes().size()) {
                Clothing clothingToModify = orderToModify.getClothes().get(clothingIndex);

                System.out.println("Choose what you want to modify:");
                System.out.println("1. Modify color");
                System.out.println("2. Modify size");
                System.out.println("3. Modify brand");

                int modificationChoice = scanner.nextInt();

                ClothingDecorator decorator = null;
                switch (modificationChoice) {
                    case 1:
                        System.out.println("Enter new color:");
                        String newColor = scanner.next();
                        decorator = new ColorDecorator(newColor);
                        break;
                    case 2:
                        System.out.println("Enter new size:");
                        String newSize = scanner.next();
                        decorator = new SizeDecorator(newSize);
                        break;
                    case 3:
                        System.out.println("Enter new brand:");
                        String newBrand = scanner.next();
                        decorator = new BrandDecorator(newBrand);
                        break;
                    default:
                        System.out.println("Incorrect choice.");
                        return;
                }

                orderToModify.modifyClothing(clothingIndex, decorator);
                System.out.println("Clothing successfully modified in the order.");
            } else {
                System.out.println("Invalid clothing index in the order.");
            }
        } else {
            System.out.println("Order with ID " + orderId + " not found.");
        }
    }

    private static Order findOrderById(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }

    private static void displayOrder(Order order) {
        if (order.getClothes().isEmpty()) {
            System.out.println("Order is empty.");
        } else {
            for (int i = 0; i < order.getClothes().size(); i++) {
                Clothing clothing = order.getClothes().get(i);
                System.out.println(i + ". " +
                        "Type: " + clothing.getClass().getSimpleName() +
                        ", Brand: " + clothing.getBrand() +
                        ", Color: " + clothing.getColor() +
                        ", Size: " + clothing.getSize() +
                        ", Price: $" + clothing.getPrice());
            }
        }
    }
}
