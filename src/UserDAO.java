import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class UserDAO {
    private List<User> users;
    private final String filePath = "C:\\Users\\basar\\IdeaProjects\\DonemProjesi\\src\\Users.ser";
    private final Scanner scanner;
    List<Clothes> purchasedProducts;
    private User loggedInUser;

    public UserDAO() {

        this.scanner = new Scanner(System.in);
        this.users = new ArrayList<>();

        loadUsers();
    }
    public void loadUsers() {
        try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(filePath))) {
            users = (List<User>) objIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            saveUsers(); // Eğer dosya bulunamazsa veya okuma hatası olursa, yeni bir dosya oluştur
        }
    }
    public void saveUsers() {
        try (ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(filePath))) {
            objOut.writeObject(users);
        } catch (IOException e) {
            System.out.println("Dosya bulunamadı veya yazma hatası oluştu.");
        }
    }
    private boolean userExists(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    public boolean signup(String username, String password) {
        if (!userExists(username)) {
            purchasedProducts = new ArrayList<>();
            User newUser = new User(username, password,purchasedProducts);
            users.add(newUser);
            saveUsers();
            return true;
        }
        return false;
    }
    public User getLoggedInUser() {
        return loggedInUser;
    }
    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
    public List<User> getUsers() {
        return users;
    }


}