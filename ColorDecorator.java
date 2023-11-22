public class ColorDecorator implements ClothingDecorator {
    private String newColor;

    public ColorDecorator(String newColor) {
        this.newColor = newColor;
    }

    @Override
    public Clothing decorate(Clothing baseClothing) {
        return new ColoredClothing(baseClothing, newColor);
    }
}
