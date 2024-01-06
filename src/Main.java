public class Main {
    public static void main(String[] args) {
    ProductManager tShirtManager = new TShirtManager();
    ProductManager shirtManager = new ShirtManager();
    UserDAO userDAO = new UserDAO();
    AdminDAO adminDAO = new AdminDAO(userDAO);
    CartManager cartManager = new CartManager(tShirtManager,shirtManager,userDAO);
    Screens screens = new Screens(userDAO,adminDAO,tShirtManager,shirtManager,cartManager);

    screens.showLoginScreen();

    }
}
