public class BrandedClothing implements Clothing {
    private Clothing baseClothing;
    private String newBrand;

    public BrandedClothing(Clothing baseClothing, String newBrand) {
        this.baseClothing = baseClothing;
        this.newBrand = newBrand;
    }

    @Override
    public String getColor() {
        return baseClothing.getColor();
    }

    @Override
    public String getSize() {
        return baseClothing.getSize();
    }

    @Override
    public String getBrand() {
        return newBrand;
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
