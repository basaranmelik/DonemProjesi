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
        scanner.nextLine(); // Buffer'ı temizlemek için dummy satır

        if (productTypeChoice == 1) {
            tshirtManager.listProduct();
            addToShoppingCart(tshirtManager.getProducts());
        } else if (productTypeChoice == 2) {
            shirtManager.listProduct();
            addToShoppingCart(shirtManager.getProducts());
        } else {
            System.out.println("Geçersiz ürün türü seçimi.");
        }
        userService.saveUsers();
    }
    private void addToShoppingCart(List<? extends Clothes> products) {
        do {
            System.out.println("Eklemek istediğiniz ürünün ID'sini girin veya 'q' tuşuna basarak çıkın:");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                return; // Çıkış yap
            }

            try {
                int productIndex = Integer.parseInt(input) - 1;

                if (productIndex >= 0 && productIndex < products.size()) {
                    Clothes selectedProduct = products.get(productIndex);

                    userService.getLoggedInUser().getPurchasedProducts().add(selectedProduct);
                    userService.saveUsers();

                    System.out.println("Ürün sepete eklendi: " + selectedProduct);

                } else {
                    System.out.println("Geçersiz ürün index'i.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Geçersiz giriş. Lütfen bir sayı veya 'q' girin.");
            }
        } while (true);
    }
    public void showShoppingCart() {
        if (userService.getLoggedInUser().getPurchasedProducts().isEmpty()) {
            System.out.println("Sepetiniz boş.");
        } else {
            System.out.println("Sepetinizdeki Ürünler:");
            for (Clothes product : userService.getLoggedInUser().getPurchasedProducts()) {
                System.out.println(product);
            }
        }
        System.out.println(calculateTotalPrice());
    }
    private double calculateTotalPrice() {
        double totalPrice = 0;

        for (Clothes product : userService.getLoggedInUser().getPurchasedProducts()) {
            totalPrice += product.getPrice();
        }

        return totalPrice;
    }
    public void removeFromShoppingCart() {
        if (userService.getLoggedInUser().getPurchasedProducts().isEmpty()) {
            System.out.println("Sepetiniz boş.");
        } else {
            System.out.println("Sepetinizdeki Ürünleri Listele:");
            showShoppingCart();

            do {
                System.out.println("Silmek istediğiniz ürünün index'ini girin veya 'q' tuşuna basarak çıkın:");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("q")) {
                    return; // Çıkış yap
                }
                try {
                    int productIndex = Integer.parseInt(input);

                    if (productIndex >= 0 && productIndex < userService.getLoggedInUser().getPurchasedProducts().size()) {
                        Clothes removedProduct = userService.getLoggedInUser().getPurchasedProducts().remove(productIndex);
                        System.out.println("Ürün sepetten kaldırıldı: " + removedProduct);
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
