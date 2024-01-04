import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TShirt extends Clothes {
    private String size;


    public TShirt(String brand, double price, String size) {
        super(brand, price);
        this.size = size;

    }


    @Override
    public String toString() {
        return "TShirt [brand=" + getBrand() + ", price=" + getPrice() + ", size=" + size + "]";
    }
}
