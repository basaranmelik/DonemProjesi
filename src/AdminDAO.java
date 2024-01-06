
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class AdminDAO {
    private final String filePath = "C:\\Users\\basar\\Desktop\\Admins.ser";
    Scanner scanner = new Scanner(System.in);
    private UserDAO userService;  // UserDAO sınıfına erişim için

    public AdminDAO(UserDAO userService) {
        this.userService = userService; // UserService eklenmiş

    }

    public void addUser() {
        System.out.println("Yeni kullanıcı eklemek için bilgileri girin:");
        System.out.println("Kullanıcı adı: ");
        String newUsername = scanner.nextLine();
        System.out.println("Şifre: ");
        String newPassword = scanner.nextLine();

        if (userService.signup(newUsername, newPassword)) {
            System.out.println("Kullanıcı başarıyla eklenmiştir.");
        } else {
            System.out.println("Kullanıcı eklenemedi. Kullanıcı adı zaten mevcut.");
        }
    }

    public void deleteUser() {
        System.out.println("Silmek istediğiniz kullanıcının kullanıcı adını girin:");
        String username = scanner.nextLine();
        Iterator<User> iterator = userService.getUsers().iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getUsername() != null && user.getUsername().equals(username)) {
                iterator.remove();
                userService.saveUsers(); // Kullanıcı silindikten sonra dosyaya kaydet
                System.out.println("Kullanıcı başarıyla silindi.");
                found = true;
                break; // Kullanıcı bulundu, döngüden çık
            }
        }

        if (!found) {
            System.out.println("Kullanıcı bulunamadı.");
        }
    }

    public void listUsers(UserDAO userService) {
        List<User> users = userService.getUsers();
        if (users.isEmpty()) {
            System.out.println("Kullanıcı bulunamadı.");
        } else {
            for (User user : users) {
                System.out.println("Kullanıcı adı: " + user.getUsername());
                // Diğer kullanıcı bilgilerini yazdırabilirsiniz
            }
        }
    }

    public boolean isAdmin(User user) {
        return user != null && "admin".equals(user.getUsername()) && "admin".equals(user.getPassword());
    }


}
