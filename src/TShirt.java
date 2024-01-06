import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TShirt extends Clothes {
    private String size;
    private String renk;


    public TShirt(String brand, double price, String size,String renk) {
        super(brand, price);
        this.size = size;
        this.renk = renk;

    }


    @Override
    public String toString() {
        return "TShirt [brand=" + getBrand() + ", price=" + getPrice() + ", size=" + size + "]";
    }
}
