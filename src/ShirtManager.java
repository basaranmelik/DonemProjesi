import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ShirtManager implements ProductManager {
    private List<Shirt> shirts;
    public final String filePath = "C:\\Users\\basar\\Desktop\\Shirts.ser";

    public ShirtManager() {
        shirts = new ArrayList<>();
        readProductToFile();
    }

    @Override
    public void addProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Gömlek markasını giriniz: ");
        String brand = scanner.nextLine();

        System.out.println("Gömlek fiyatını giriniz: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Dummy line for clearing the buffer

        System.out.println("Gömlek bedenini giriniz: ");
        String size = scanner.nextLine();

        System.out.println("Gömlek rengini giriniz: ");
        String renk = scanner.nextLine();

        Shirt shirt = new Shirt(brand, price, size, renk);
        shirts.add(shirt);
        System.out.println("Gömlek başarıyla eklendi: " + shirt);

        // Listeyi dosyaya yaz
        writeProductToFile();
    }

    @Override
    public void listProduct() {
        for (Shirt shirt : shirts) {
            System.out.println(shirt);
        }
    }

    @Override
    public void deleteProduct() {
        Scanner scanner = new Scanner(System.in);

        if (shirts.isEmpty()) {
            System.out.println("Silinecek Gömlek bulunamadı. Liste boş.");
            return;
        }

        System.out.println("Silmek istediğiniz Gömleğin index'ini girin veya 'tumunu' yazarak tüm Gömlek'leri silin:");

        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase("tumunu")) {
            System.out.println("Emin misiniz? (E/H)");
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("E")) {
                shirts.clear();
                writeProductToFile(); // Tüm T-Shirt'ler silindikten sonra dosyaya kaydet
                System.out.println("Tüm Gömlekler başarıyla silindi.");
            } else {
                System.out.println("Silme işlemi iptal edildi.");
            }
        } else {
            int productIndex;
            while (true) {
                try {
                    productIndex = Integer.parseInt(userInput);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Geçersiz bir sayı girdiniz. Lütfen tekrar deneyin veya 'tumunu' yazarak tüm Gömlekleri silin.");
                    userInput = scanner.nextLine();
                }
            }

            Iterator<Shirt> iterator = shirts.iterator();
            int currentIndex = 0;
            boolean found = false;

            while (iterator.hasNext()) {
                Shirt currentShirt = iterator.next();

                if (currentIndex == productIndex) {
                    iterator.remove();
                    writeProductToFile(); // Ürün silindikten sonra dosyaya kaydet
                    System.out.println("T-Shirt başarıyla silindi: " + currentShirt);
                    found = true;
                    break;
                }

                currentIndex++;
            }

            if (!found) {
                System.out.println("Geçersiz index. Lütfen doğru bir index girin.");
            }
        }
    }

    @Override
    public void writeProductToFile() {
        FileUtil.writeToFile(shirts, filePath);
    }

    @Override
    public void readProductToFile() {
        List<Shirt> readShirts = (List<Shirt>) FileUtil.readFromFile(filePath);

        if (readShirts != null) {
            shirts = readShirts;
        }
    }

    @Override
    public List<? extends Clothes> getProducts() {
        return shirts;
    }
}
