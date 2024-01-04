import java.util.List;

public interface ProductManager {
    void addProduct();
    void listProduct();
    void writeProductToFile(List<? extends Clothes> clothes);
    void readProductToFile();
}
