import java.io.Serializable;

public abstract class Clothes implements Serializable {
    private String brand;
    private double price;
    private String size;
    private int stock;

    public Clothes(String brand, double price, String size, int stock) {
        this.brand = brand;
        this.price = price;
        this.size = size;
        this.stock = stock;
    }

    public void decreaseStock(int i) {
        if (stock > 0) {
            stock-=i;
        }
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
