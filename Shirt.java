public class Shirt implements Clothing {
    private String color;
    private String size;
    private String brand;
    private double price;

    public Shirt(String color, String size, String brand, double price) {
        this.color = color;
        this.size = size;
        this.brand = brand;
        this.price = price;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getSize() {
        return size;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setSize(String size) {
        this.size = size;
    }
}
