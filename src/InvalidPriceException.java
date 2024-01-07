import java.util.Scanner;

public class InvalidPriceException extends Exception {
    public InvalidPriceException(String message) {
        super(message);
    }

    public static double validatePrice(Scanner scanner) throws InvalidPriceException {
        try {
            double price = Double.parseDouble(scanner.nextLine());
            if (price < 0) {
                throw new InvalidPriceException("Hata: Fiyat negatif olamaz. Lütfen pozitif bir değer girin.");
            }
            return price;
        } catch (NumberFormatException e) {
            throw new InvalidPriceException("Hata: Geçersiz fiyat formatı. Lütfen sayısal bir değer girin.");
        }
    }
}
