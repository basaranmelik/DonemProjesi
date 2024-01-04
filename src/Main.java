public class Main {
    public static void main(String[] args) {
    ProductManager productManager;
    UserService userService = new UserDAO();
    AdminService adminService = new AdminDAO();
    Screens screen = new Screens(userService,adminService);
    screen.showLoginScreen();

    }
}
