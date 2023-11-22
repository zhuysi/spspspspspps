public class BrandDecorator implements ClothingDecorator {
    private String newBrand;

    public BrandDecorator(String newBrand) {
        this.newBrand = newBrand;
    }

    @Override
    public Clothing decorate(Clothing baseClothing) {
        return new BrandedClothing(baseClothing, newBrand);
    }
}
