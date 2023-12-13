import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@SuppressWarnings("ALL")
public class DESCryptography {
    // Определение алгоритма шифрования
    private static final String ALGORITHM = "DES";

    // Метод шифрования строки
    public static String encrypt(String key, String data) throws Exception {
        if (key == null || data == null) {
            throw new IllegalArgumentException("Key value не могут быть null");
        }
        // Создание спецификации ключа
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        // Получение экземпляра Cipher
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // Инициализация Cipher в режиме шифрования
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        // Шифрование строки
        byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        // Кодирование зашифрованных данных в строку
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // Метод дешифрования строки
    public static String decrypt(String key, String data) throws Exception {
        if (key == null || data == null) {
            throw new IllegalArgumentException("Key value не могут быть null");
        }
        // Создание спецификации ключа
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        // Получение экземпляра Cipher
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // Инициализация Cipher в режиме дешифрования
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        // Декодирование зашифрованных данных из строки
        byte[] decoded = Base64.getDecoder().decode(data);
        // Дешифрование строки
        byte[] decrypted = cipher.doFinal(decoded);
        // Преобразование дешифрованных данных в строку
        return new String(decrypted, StandardCharsets.UTF_8);
    }

    // Запуск основной программы
    public static void main(String[] args) throws Exception {
        try {
            // Определение ключа и строки для шифрования
            String key = "mysecret";
            String data = "Hello, World!";
            System.out.println("Изначальная строка: " + data);

            // Шифрование строки
            String encryptedData = encrypt(key, data);
            System.out.println("Закодированное строка: " + encryptedData);

            // Дешифрование строки
            String decryptedData = decrypt(key, encryptedData);
            System.out.println("Раскодированная строка: " + decryptedData);
        } catch (Exception e) {
            //  Если в процессе выполнения кода произойдет ошибка, то ошибка будет
            //  обработана и информация об ошибке будет выведена в консоль
            e.printStackTrace();
        }
    }
}
