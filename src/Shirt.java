import java.io.Serializable;

public class Shirt extends Clothes{
    private String size;
    private String renk;

    public Shirt(String brand, double price, String size, String renk) {
        super(brand, price);
        this.renk = renk;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Gömlek [marka=" + getBrand() + ", fiyat=" + getPrice() + ", beden=" + size + ", renk=" + renk + "]";
    }
}
