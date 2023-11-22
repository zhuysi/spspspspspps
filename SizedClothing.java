public class SizedClothing implements Clothing {
    private Clothing baseClothing;
    private String newSize;

    public SizedClothing(Clothing baseClothing, String newSize) {
        this.baseClothing = baseClothing;
        this.newSize = newSize;
    }

    @Override
    public String getColor() {
        return baseClothing.getColor();
    }

    @Override
    public String getSize() {
        return newSize;
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
        this.newSize = size;
    }
    public void changeSize(String newSize) {
        this.newSize = newSize;
    }
}
