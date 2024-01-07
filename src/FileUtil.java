import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    // Girilen dosya yolundak
    public static void writeToFile(List<? extends Clothes> clothes, String filePath) {
        try (ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(filePath))) {
            objOut.writeObject(clothes);
        } catch (IOException e) {
            System.out.println("Dosya bulunamadı veya yazma hatası.");
        }
    }

    // Girilen dosya yolundan Clothes sınıfından kalıtılmış sınıfların nesnelerini oku ve girilen listeye kaydet
    @SuppressWarnings("unchecked")
    public static List<? extends Clothes> readFromFile(String filePath) {
        List<? extends Clothes> clothes = new ArrayList<>();
        try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(filePath))) {
            clothes = (List<? extends Clothes>) objIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Dosya bulunamadı veya okuma hatası.");
        }
        return clothes;
    }
}
