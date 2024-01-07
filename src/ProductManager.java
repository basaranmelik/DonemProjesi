import java.util.List;

public interface ProductManager {
    void addProduct();
    void listProduct();
    void deleteProduct();
    void writeProductToFile();
    void readProductToFile();
    List<? extends Clothes> getProducts();
}
