import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Screens {
    Scanner scanner = new Scanner(System.in);
    private UserDAO userService;
    private AdminDAO adminService;
    private ProductManager tshirtManager;
    private ProductManager shirtManager;
    private User loggedInUser;

    public Screens(UserDAO userService, AdminDAO adminService, ProductManager tshirtManager, ProductManager shirtManager) {
        this.userService = userService;
        this.adminService = adminService;
        this.tshirtManager = tshirtManager;
        this.shirtManager = shirtManager;

    }
    public void showLoginScreen() {

        int choice;
        do {
            System.out.println("1. Giriş Yap");
            System.out.println("2. Kayıt Ol");
            System.out.println("3. Çıkış Yap");
            choice = scanner.nextInt();
            scanner.nextLine(); // Dummy line for clearing the buffer

            switch (choice) {
                case 1:
                    System.out.println("Kullanıcı adınızı giriniz: ");
                    String loginUsername = scanner.nextLine();
                    System.out.println("Şifrenizi giriniz:");
                    String loginPassword = scanner.nextLine();
                    loggedInUser = userService.login(loginUsername, loginPassword);

                    if (loggedInUser != null) {
                        if (adminService.isAdmin(loggedInUser)) {
                            System.out.println("Admin giriş yaptı.");
                            showAdminScreen();
                        } else {
                            System.out.println("Başarıyla giriş yapıldı.");
                            showShoppingScreen(); // Giriş yapıldıktan sonra alışveriş ekranını göster
                        }
                    } else {
                        System.out.println("Yanlış kullanıcı adı veya şifre.");
                    }
                    break;

                case 2:
                    System.out.println("Kayıt olmak için kullanıcı adınızı giriniz: ");
                    String signupUsername = scanner.nextLine();
                    System.out.println("Kayıt olmak için şifrenizi giriniz:");
                    String signupPassword = scanner.nextLine();

                    if (userService.signup(signupUsername, signupPassword)) {
                        System.out.println("Kullanıcı başarıyla kaydedildi.");
                    } else {
                        System.out.println("Kullanıcı adı zaten kayıtlı. Lütfen başka bir kullanıcı adı seçin.");
                    }
                    break;
                case 3:
                    System.out.println("Çıkış yapılıyor...");
                    System.exit(500);
                    break;

                default:
                    System.out.println("Geçersiz Seçim!");
            }
        } while (true);
    }
    private void showShoppingScreen() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("1. Ürünleri Listele");
            System.out.println("2. Sepete Ekle");
            System.out.println("3. Sepeti Göster");
            System.out.println("4. Sepetten Ürün Kaldır.");
            System.out.println("5. Çıkış");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Dummy line for clearing the buffer

            switch (choice) {
                case 1:
                    tshirtManager.listProduct();
                    break;

                case 2:
                    addToShoppingCart();
                    break;

                case 3:
                    showShoppingCart();
                    waitForEnter();
                    break;

                case 4:
                    removeFromShoppingCart();
                    break;
                case 5:
                    System.out.println("Çıkış Yapılıyor...");
                    return;
                default:
                    System.out.println("Geçersiz Seçim!");
            }
        } while (true);
    }
    public void showAdminScreen() {

        int choice;

        do {

            System.out.println("Admin işlemleri menüsü:");
            System.out.println("1. Kullanıcı Ekle");
            System.out.println("2. Kullanıcı Sil");
            System.out.println("3. Kullanıcı Listele");
            System.out.println("4. TShirt Ekle");
            System.out.println("5. TShirt Listele");
            System.out.println("6. TShirt Sil");
            System.out.println("7. Gömlek Ekle");
            System.out.println("8. Gömlek Listele");
            System.out.println("9. Gömlek Sil");


            choice = scanner.nextInt();
            scanner.nextLine(); // Dummy line for clearing the buffer

            switch (choice) {
                case 1:
                    adminService.addUser();
                    break;
                case 2:
                    adminService.deleteUser();
                    break;
                case 3:
                    adminService.listUsers(userService);
                    waitForEnter();
                    break;
                case 4:
                    tshirtManager.addProduct();
                    break;
                case 5:
                    tshirtManager.listProduct();
                    waitForEnter();
                    break;
                case 6:
                    tshirtManager.deleteProduct();
                    break;
                case 7:
                    shirtManager.addProduct();
                    break;
                case 8:
                    shirtManager.listProduct();
                    waitForEnter();
                    break;
                case 9:
                    shirtManager.deleteProduct();
                    break;
                default:
                    System.out.println("Geçersiz Seçim!");
            }
        } while (choice != 1111);
    }
    private void addToShoppingCart() {
        System.out.println("Eklemek istediğiniz ürünün türünü seçin:");
        System.out.println("1. TShirt");
        System.out.println("2. Gömlek");

        int productTypeChoice = scanner.nextInt();
        scanner.nextLine(); // Buffer'ı temizlemek için dummy satır

        if (productTypeChoice == 1) {
            // TShirt'i sepete ekleme
            addToShoppingCart(tshirtManager.getProducts());
        } else if (productTypeChoice == 2) {
            // Gömlek'i sepete ekleme
            addToShoppingCart(shirtManager.getProducts());
        } else {
            System.out.println("Geçersiz ürün türü seçimi.");
        }
        userService.saveUsers();
    }
    private void addToShoppingCart(List<? extends Clothes> products) {
        do {
            System.out.println("Eklemek istediğiniz ürünün index'ini girin veya 'q' tuşuna basarak çıkın:");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                return; // Çıkış yap
            }

            try {
                int productIndex = Integer.parseInt(input);

                if (productIndex >= 0 && productIndex < products.size()) {
                    Clothes selectedProduct = products.get(productIndex);
                    loggedInUser.getPurchasedProducts().add(selectedProduct);
                    System.out.println("Ürün sepete eklendi: " + selectedProduct);
                } else {
                    System.out.println("Geçersiz ürün index'i.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Geçersiz giriş. Lütfen bir sayı veya 'q' girin.");
            }
        } while (true);
    }
    private void showShoppingCart() {
        if (loggedInUser.getPurchasedProducts().isEmpty()) {
            System.out.println("Sepetiniz boş.");
        } else {
            System.out.println("Sepetinizdeki Ürünler:");
            for (Clothes product : loggedInUser.getPurchasedProducts()) {
                System.out.println(product);
            }
        }
    }
    private void removeFromShoppingCart() {
        if (loggedInUser.getPurchasedProducts().isEmpty()) {
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

                    if (productIndex >= 0 && productIndex < loggedInUser.getPurchasedProducts().size()) {
                        Clothes removedProduct = loggedInUser.getPurchasedProducts().remove(productIndex);
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
    private void waitForEnter() {

        System.out.println("Devam etmek için bir tuşa basın...");
        scanner.nextLine();
    }
}
