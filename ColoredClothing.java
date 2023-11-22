public class ColoredClothing implements Clothing {
    private Clothing baseClothing;
    private String newColor;

    public ColoredClothing(Clothing baseClothing, String newColor) {
        this.baseClothing = baseClothing;
        this.newColor = newColor;
    }

    @Override
    public String getColor() {
        return newColor;
    }

    @Override
    public String getSize() {
        return baseClothing.getSize();
    }

    @Override
    public String getBrand() {
        return baseClothing.getBrand();
    }

    @Override
    public double getPrice() {
        return baseClothing.getPrice();
    }

    @Override
    public void setSize(String size) {
        baseClothing.setSize(size);
    }
}
