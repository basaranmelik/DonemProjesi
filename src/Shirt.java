import java.io.Serializable;

public class Shirt extends Clothes implements Serializable {
    private String pattern;
    private String fabric;

    public Shirt(String brand, double price, String size, String pattern, String fabric) {
        super(brand, price, size);
        this.pattern = pattern;
        this.fabric = fabric;
    }
    @Override
    public String toString() {
        return "Gömlek {" +
                "Marka:" + getBrand() +
                ", Fiyat:" + getPrice() +
                ", Beden:" + getSize() +
                ", Desen:" + pattern +
                ", Kumaş:" + fabric +
                '}';
    }
}
