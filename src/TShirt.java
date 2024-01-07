
public class TShirt extends Clothes {
    private final String color;
    private final String neck;


    public TShirt(String brand, double price, String size, String color, String neck) {
        super(brand, price, size);
        this.color = color;
        this.neck = neck;
    }
    @Override
    public String toString() {
        return "TShirt {" +
                "Marka:" + getBrand() +
                ", Fiyat:" + getPrice() +
                ", Beden:" + getSize() +
                ", Renk:" + color +
                ", Yaka Tipi:" + neck +
                '}';
    }


}
