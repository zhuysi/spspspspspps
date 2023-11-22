public class ClothingDisplayAdapter implements DisplayAdapter {
    private ClothingFactory factory;

    public ClothingDisplayAdapter(ClothingFactory factory) {
        this.factory = factory;
    }

    @Override
    public void displayClothing(Clothing clothing) {
        // The logic of displaying clothes in the store.
        System.out.println("Display in the store: " + clothing.getBrand() + " " + clothing.getColor());
    }
}
