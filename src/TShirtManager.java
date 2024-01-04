import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TShirtManager implements ProductManager {
    private List<TShirt> tShirts;
    public final String filePath = "C:\\Users\\basar\\Desktop\\TShirts.ser";

    public TShirtManager() {

        tShirts = new ArrayList<>();
        readProductToFile();
    }


    @Override
    public void addProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("T-Shirt markasını giriniz: ");
        String brand = scanner.nextLine();

        System.out.println("T-Shirt fiyatını giriniz: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Dummy line for clearing the buffer

        System.out.println("T-Shirt bedenini giriniz: ");
        String size = scanner.nextLine();

        TShirt tShirt = new TShirt(brand, price, size);
        tShirts.add(tShirt);
        System.out.println("T-Shirt başarıyla eklendi: " + tShirt);

        // Listeyi dosyaya yaz
        writeProductToFile(tShirts);
    }

    @Override
    public void listProduct() {
        for (TShirt tShirt : tShirts) {
            System.out.println(tShirt);
        }
    }

    @Override
    public void writeProductToFile(List<? extends Clothes> tshirts) {
        try (ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(filePath))) {
            objOut.writeObject(tshirts);
        } catch (IOException e) {
            System.out.println("Dosya bulunamadı veya yazma hatası.");
        }
    }

    @Override
    public void readProductToFile() {

        try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(filePath))) {
            tShirts = (List<TShirt>) objIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Dosya bulunamadı veya okuma hatası.");
        }

    }
}
