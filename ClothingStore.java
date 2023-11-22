// 1. Singleton (Single)
public class ClothingStore {
    private static ClothingStore instance;

    private ClothingStore() {
    }

    public static ClothingStore getInstance() {
        if (instance == null) {
            instance = new ClothingStore();
        }
        return instance;
    }
}
