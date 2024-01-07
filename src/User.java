import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public record User(String username, String password, List<Clothes> purchasedProducts) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

}
