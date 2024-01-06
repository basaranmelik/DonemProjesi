public class Main {
    public static void main(String[] args) {
    ProductManager tShirtManager = new TShirtManager();
    ProductManager shirtManager = new ShirtManager();
    UserDAO userDAO = new UserDAO();
    AdminDAO adminDAO = new AdminDAO(userDAO);
    Screens screens = new Screens(userDAO,adminDAO,tShirtManager,shirtManager);

    screens.showLoginScreen();

    }
}
