import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class TShirtManager implements ProductManager {
    private List<TShirt> tShirts;
    public static final String filePath = "C:\\Users\\basar\\IdeaProjects\\DonemProjesi\\src\\TShirts.ser";

    public TShirtManager() {

        tShirts = new ArrayList<>();
        readProductToFile();
    }
    @Override
    public void decreaseStock(int productIndex, int i) {
        if (productIndex >= 0 && productIndex < tShirts.size()) {
            TShirt selectedTShirt = tShirts.get(productIndex);
            selectedTShirt.decreaseStock(i); // decreaseStock metodu olmalıdır
            // Diğer işlemler...
        }
    }
    @Override
    public List<? extends Clothes> getProducts() {
        return tShirts;
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

        System.out.println("T-Shirt rengini giriniz: ");
        String color = scanner.nextLine();

        System.out.println("T-Shirt yaka tipini giriniz: ");
        String neck = scanner.nextLine();

        System.out.println("Kaç adet eklemek istediğinizi giriniz:");
        int stock = scanner.nextInt();

        TShirt tShirt = new TShirt(brand, price, size, color, neck, stock);
        tShirts.add(tShirt);
        System.out.println("T-Shirt başarıyla eklendi: " + tShirt);

        // Listeyi dosyaya yaz
        writeProductToFile();
    }
    @Override
    public void listProduct() {
        int index = 1;

        for (TShirt tShirt : tShirts) {
            System.out.println("TShirt ID: " + index + " - " + tShirt);
            index++;
        }
    }
    @Override
    public void deleteProduct() {
        Scanner scanner = new Scanner(System.in);

        if (tShirts.isEmpty()) {
            System.out.println("Silinecek T-Shirt bulunamadı. Liste boş.");
            return;
        }

        System.out.println("Silmek istediğiniz T-Shirt'ün index'ini girin veya 'tumunu' yazarak tüm T-Shirt'leri silin:");

        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase("tumunu")) {
            System.out.println("Emin misiniz? (E/H)");
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("E")) {
                tShirts.clear();
                writeProductToFile(); // Tüm T-Shirt'ler silindikten sonra dosyaya kaydet
                System.out.println("Tüm T-Shirt'ler başarıyla silindi.");
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
                    System.out.println("Geçersiz bir sayı girdiniz. Lütfen tekrar deneyin veya 'tumunu' yazarak tüm T-Shirt'leri silin.");
                    userInput = scanner.nextLine();
                }
            }

            Iterator<TShirt> iterator = tShirts.iterator();
            int currentIndex = 0;
            boolean found = false;

            while (iterator.hasNext()) {
                TShirt currentTShirt = iterator.next();

                if (currentIndex == productIndex) {
                    iterator.remove();
                    writeProductToFile(); // Ürün silindikten sonra dosyaya kaydet
                    System.out.println("T-Shirt başarıyla silindi: " + currentTShirt);
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
        FileUtil.writeToFile(tShirts, filePath);
    }
    @Override
    public void readProductToFile() {
        List<TShirt> readTShirts = (List<TShirt>) FileUtil.readFromFile(filePath);

        if (readTShirts != null) {
            tShirts = readTShirts;
        }
    }
}
