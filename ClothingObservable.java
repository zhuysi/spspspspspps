import java.util.ArrayList;
import java.util.List;

public class ClothingObservable {
    private static List<Observer> observers = new ArrayList<>();
    private static NotificationStrategy notificationStrategy;

    public static void addObserver(Observer observer) {
        observers.add(observer);
    }

    public static void setNotificationStrategy(NotificationStrategy notificationStrategy) {
        ClothingObservable.notificationStrategy = notificationStrategy;
    }

    public static void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }

        if (notificationStrategy != null) {
            notificationStrategy.sendNotification(message);
        }
    }
}
