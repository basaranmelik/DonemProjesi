import java.io.Serializable;

public abstract class Clothes implements Serializable {
    private String brand;
    private double price;
    private String size;

    public Clothes(String brand, double price, String size) {
        this.brand = brand;
        this.price = price;
        this.size = size;

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
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
