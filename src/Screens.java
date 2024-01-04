import java.util.List;
import java.util.Scanner;

public class Screens {
    private UserService userService;

    public Screens(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    private AdminService adminService;



    public void showLoginScreen() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("1. Giriş Yap");
            System.out.println("2. Kayıt Ol");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Dummy line for clearing the buffer

            switch (choice) {
                case 1:
                    System.out.println("Kullanıcı adınızı giriniz: ");
                    String loginUsername = scanner.nextLine();
                    System.out.println("Şifrenizi giriniz:");
                    String loginPassword = scanner.nextLine();
                    User loggedInUser = userService.login(loginUsername, loginPassword);

                    if (loggedInUser != null) {
                        if (adminService.isAdmin(loggedInUser)) {
                            System.out.println("Admin giriş yaptı.");
                            adminService.adminActions();
                        } else {
                            System.out.println("Başarıyla giriş yapıldı.");
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

                default:
                    System.out.println("Geçersiz Seçim!");
            }
        } while (true);
    }

}