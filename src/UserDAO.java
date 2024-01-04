import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class UserDAO implements UserService {
    private List<User> users;
    private final String filePath = "C:\\Users\\basar\\Desktop\\Users.ser";
    private final Scanner scanner;

    public UserDAO() {

        this.scanner = new Scanner(System.in);
        this.users = new ArrayList<>();

        loadUsers();
    }

    private void loadUsers() {
        try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(filePath))) {
            users = (List<User>) objIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            saveUsers(); // Eğer dosya bulunamazsa veya okuma hatası olursa, yeni bir dosya oluştur
        }
    }

    private void saveUsers() {
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
    @Override
    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean signup(String username, String password) {
        if (!userExists(username)) {
            User newUser = new User(username, password);
            users.add(newUser);
            saveUsers();
            return true;
        }
        return false;
    }


}

