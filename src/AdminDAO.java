
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminDAO implements AdminService{
    private ProductManager tshirtManager;

    public AdminDAO() {
        tshirtManager = new TShirtManager();


    }
@Override
    public void adminActions() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Admin işlemleri menüsü:");
            System.out.println("1. TShirt Ekle");
            System.out.println("2. Tişörtleri Listele");
            System.out.println("3. Çıkış");

            choice = scanner.nextInt();
            scanner.nextLine(); // Dummy line for clearing the buffer

            switch (choice) {
                case 1:

                    tshirtManager.addProduct();
                    break;
                case 2:
                    tshirtManager.listProduct();
                    break;

                case 3:
                    System.out.println("Admin işlemleri menüsünden çıkılıyor.");
                    break;
                default:
                    System.out.println("Geçersiz Seçim!");
            }
        } while (choice != 3);
    }

    @Override
    public boolean isAdmin(User user) {
        return user != null && "admin".equals(user.getUsername()) && "admin".equals(user.getPassword());
    }



}
