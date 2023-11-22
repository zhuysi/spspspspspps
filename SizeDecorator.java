public class SizeDecorator implements ClothingDecorator {
    private String newSize;

    public SizeDecorator(String newSize) {
        this.newSize = newSize;
    }

    @Override
    public Clothing decorate(Clothing baseClothing) {
        return new SizedClothing(baseClothing, newSize);
    }
}
