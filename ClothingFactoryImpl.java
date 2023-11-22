public class ClothingFactoryImpl implements ClothingFactory {
    @Override
    public Clothing createClothing(String type, String color, String size, String brand, double price) {
        if ("jeans".equalsIgnoreCase(type)) {
            return new Jeans(color, size, brand, price);
        } else if ("cap".equalsIgnoreCase(type)) {
            return new Cap(color, size, brand, price);
        } else if ("shirt".equalsIgnoreCase(type)) {
            return new Shirt(color, size, brand, price);
        }
        return null;
    }

    @Override
    public Clothing createClothing(String clothingType, String черный, String adidas) {
        return null;
    }
}
