
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class AdminDAO {
    Scanner scanner;
    private final UserDAO userService;  // UserDAO sınıfına erişim için

    public AdminDAO(UserDAO userService) {
        this.userService = userService; // UserService eklenmiş
        scanner = new Scanner(System.in);
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
        listUsers(userService);
        System.out.println("Silmek istediğiniz kullanıcının kullanıcı adını girin:");
        String username = scanner.nextLine();
        Iterator<User> iterator = userService.getUsers().iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            User user = iterator.next(); // users listesini tek tek gez
            if (user.username() != null && user.username().equals(username)) {
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
                System.out.println("Kullanıcı adı: " + user.username());
            }
        }
    }

    public boolean isAdmin(User user) {
        return user != null && "admin".equals(user.username()) && "admin".equals(user.password());
    }


}
