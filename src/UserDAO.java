import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class UserDAO {
    private List<User> users;
    private final String filePath = "C:\\Users\\basar\\IdeaProjects\\DonemProjesi\\src\\Users.ser";
    List<Clothes> purchasedProducts;
    private User loggedInUser;

    public UserDAO() {
        this.users = new ArrayList<>();

        loadUsers();
    }
    @SuppressWarnings("unchecked")
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
            if (user.username().equals(username)) {
                return true;
            }
        }
        return false;
    }
    public User login(String username, String password) {
        for (User user : users) {
            if (user.username().equals(username) && user.password().equals(password)) {
                return user;
            }
        }
        return null;
    }
    public boolean signup(String username, String password) {
        // Boşluk karakterlerini kontrol et
        if (username.contains(" ") || password.contains(" ")) {
            System.out.println("Hata: Kullanıcı adı veya şifre boşluk karakteri içeremez.");
            return false;
        }

        // Kullanıcı adının mevcut olup olmadığını kontrol et
        if (!userExists(username)) {
            purchasedProducts = new ArrayList<>();
            User newUser = new User(username, password, purchasedProducts);
            users.add(newUser);
            saveUsers();
            return true;
        }

        System.out.println("Hata: Kullanıcı adı zaten mevcut.");
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