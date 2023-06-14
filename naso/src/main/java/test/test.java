package test;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class test {
    public static String generateFileName() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String currentDateTime = dateFormat.format(new Date());
        String randomString = generateRandomString(5);

        return currentDateTime + "-" + randomString + ".jpg";
    }

    private static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
