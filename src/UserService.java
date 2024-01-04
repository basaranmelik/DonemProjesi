public interface UserService {
    User login(String username, String password);
    boolean signup(String username, String password);
}
