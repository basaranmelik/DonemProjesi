import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TShirt extends Clothes {
    private String color;
    private String neck;


    public TShirt(String brand, double price, String size, String color, String neck, int stock) {
        super(brand, price, size, stock);
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
                ", Stok:" + getStock() +
                '}';
    }


}
