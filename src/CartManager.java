import java.util.List;
import java.util.Scanner;

public class CartManager {
    private final ProductManager tshirtManager;
    private final ProductManager shirtManager;
    private final UserDAO userService;
    private final Scanner scanner;

    public CartManager(ProductManager tshirtManager, ProductManager shirtManager, UserDAO userService) {
        scanner = new Scanner(System.in);
        this.shirtManager = shirtManager;
        this.tshirtManager = tshirtManager;
        this.userService = userService;
    }
    public void addToShoppingCart() {
        System.out.println("Eklemek istediğiniz ürünün türünü seçin:");
        System.out.println("1. TShirt");
        System.out.println("2. Gömlek");

        int productTypeChoice = scanner.nextInt();
        scanner.nextLine();

        if (productTypeChoice == 1) {
            tshirtManager.listProduct();
            addToShoppingCart(tshirtManager.getProducts()); // Overload edilmiş metodu çağır
        } else if (productTypeChoice == 2) {
            shirtManager.listProduct();
            addToShoppingCart(shirtManager.getProducts()); // Overload edilmiş metodu çağır
        } else {
            System.out.println("Geçersiz ürün türü seçimi.");
        }
        userService.saveUsers(); // Kullanıcı sepetini güncelle
    }
    private void addToShoppingCart(List<? extends Clothes> products) {
        do {
            System.out.println("Eklemek istediğiniz ürünün ID'sini girin veya 'q' tuşuna basarak çıkın:");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                return; // Çıkış yap
            }

            try {
                int productIndex = Integer.parseInt(input) - 1; // Girilen ifadeyi int'e çevir

                if (productIndex >= 0 && productIndex < products.size()) {
                    Clothes selectedProduct = products.get(productIndex);

                    userService.getLoggedInUser().purchasedProducts().add(selectedProduct); // Giriş yapan kullanıcının sepetine seçilen ürünü ekle
                    userService.saveUsers(); // Kullanıcı sepetini dosyaya kaydet

                    System.out.println("Ürün sepete eklendi: " + selectedProduct);

                } else {
                    System.out.println("Geçersiz ürün ID'si.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Geçersiz giriş. Lütfen bir sayı veya 'q' girin.");
            }
        } while (true);
    }
    public void showShoppingCart() {
        List<Clothes> purchasedProducts = userService.getLoggedInUser().purchasedProducts();

        if (purchasedProducts.isEmpty()) {
            System.out.println("Sepetiniz boş.");
        } else {
            System.out.println("Sepetinizdeki Ürünler:");

            for (int i = 0; i < purchasedProducts.size(); i++) {
                Clothes product = purchasedProducts.get(i);
                int productId = i + 1; // ID'yi 1'den başlayarak belirle

                System.out.println("Ürün ID: " + productId+ " " + product);
            }
        }

        System.out.println("Toplam Sepet Tutarınız: " + calculateTotalPrice());
    }

    private double calculateTotalPrice() {
        // Sepeti gez ve her ürünün fiyatını toplayıp döndür
        double totalPrice = 0;
        for (Clothes product : userService.getLoggedInUser().purchasedProducts()) {
            totalPrice += product.getPrice();
        }

        return totalPrice;
    }
    public void removeFromShoppingCart() {
        if (userService.getLoggedInUser().purchasedProducts().isEmpty()) {
            System.out.println("Sepetiniz boş.");
        } else {
            do {
                showShoppingCart();
                System.out.println("Silmek istediğiniz ürünün index'ini girin veya 'q' tuşuna basarak çıkın:");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("q")) {
                    return; // Çıkış yap
                }
                try {
                    int productIndex = Integer.parseInt(input)-1; // Girilen string ifadeyi int'e çevir ve 0 yerine 1'den başlamsını sağla

                    if (productIndex >= 0 && productIndex < userService.getLoggedInUser().purchasedProducts().size()) {
                        // Seçilen indexi listede bul ve sil
                        Clothes removedProduct = userService.getLoggedInUser().purchasedProducts().remove(productIndex);
                        System.out.println("Ürün sepetten kaldırıldı: " + removedProduct);
                        // Kullanıcının sepetini dosyaya kaydet
                        userService.saveUsers();
                    } else {
                        System.out.println("Geçersiz ürün index'i.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Geçersiz giriş. Lütfen bir sayı veya 'q' girin.");
                }
            } while (true);
        }
    }
}
