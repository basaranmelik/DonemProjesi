import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    List<Clothes> purchasedProducts;


    public User(String username, String password,List<Clothes> purchasedProducts) {
        this.username = username;
        this.password = password;
        this.purchasedProducts= purchasedProducts;
    }
    public List<Clothes> getPurchasedProducts() {
        return purchasedProducts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}