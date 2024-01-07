import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class TShirtManager implements ProductManager {
    private final Scanner scanner;
    private List<TShirt> tShirts;
    public static final String filePath = "C:\\Users\\basar\\IdeaProjects\\DonemProjesi\\src\\TShirts.ser";

    // Constructor
    public TShirtManager() {
        scanner = new Scanner(System.in);
        tShirts = new ArrayList<>();
        readProductToFile(); // Dosyadan ürünleri oku
    }

    // Metodlar
    @Override
    public List<? extends Clothes> getProducts() {
        return tShirts;
    }

    @Override
    public void addProduct() {
        double price = 0.0;
        boolean validPrice = false;
        // Kullanıcıdan T-Shirt bilgilerini al
        System.out.println("T-Shirt markasını giriniz: ");
        String brand = scanner.nextLine();

        System.out.println("Gömlek fiyatını giriniz: ");
        while (!validPrice) {
            try {
                price = InvalidPriceException.validatePrice(scanner);
                validPrice = true;
            } catch (InvalidPriceException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("T-Shirt bedenini giriniz: ");
        String size = scanner.nextLine();

        System.out.println("T-Shirt rengini giriniz: ");
        String color = scanner.nextLine();

        System.out.println("T-Shirt yaka tipini giriniz: ");
        String neck = scanner.nextLine();

        // Yeni bir TShirt nesnesi oluştur ve listeye ekle
        TShirt tShirt = new TShirt(brand, price, size, color, neck);
        tShirts.add(tShirt);

        // Kullanıcıya eklenen T-Shirt bilgisini göster
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
        // tShirts listesi boş ise metotu bitir
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
                tShirts.clear(); // T-Shirtlerin tutulduğu listeyi temizle.
                writeProductToFile(); // Tüm T-Shirtler silindikten sonra dosyaya kaydet
                System.out.println("Tüm T-Shirt'ler başarıyla silindi.");
            } else {
                System.out.println("Silme işlemi iptal edildi.");
            }
        } else {
            int productIndex;
            while (true) {
                try {
                    productIndex = Integer.parseInt(userInput); // userInput'u tam sayıya çevirir.
                    break;
                }
                // Eğer userInput'ta sayısal değer bulunmazsa NumberFormatException fırlatılıyor.
                catch (NumberFormatException e) {
                    System.out.println("Geçersiz bir sayı girdiniz. Lütfen tekrar deneyin veya 'tumunu' yazarak tüm T-Shirt'leri silin.");
                    userInput = scanner.nextLine();
                }
            }

            Iterator<TShirt> iterator = tShirts.iterator(); // Listeyi daha etkin kontrol edebilmek için bir iterator oluştur
            int currentIndex = 0; // Başlangıç index'ini tut
            boolean found = false;

            while (iterator.hasNext()) {
                TShirt currentTShirt = iterator.next(); //Listeyi tek tek gez

                // Girilen index'e ait ürünü bul
                if (currentIndex == productIndex) {
                    iterator.remove();  // Ürünü sil
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

    @SuppressWarnings("unchecked")
    @Override
    public void readProductToFile() {
        List<TShirt> readTShirts = (List<TShirt>) FileUtil.readFromFile(filePath);

        if (readTShirts != null) {
            tShirts = readTShirts;
        }
    }

}
