package utils;

import java.util.Random;

public final class RandomHelper {

    private RandomHelper() {
    }

    public static String randomCode() {
        Random random = new Random();
        return String.valueOf(random.nextInt(9999 - 1000 + 1) + 1000);
    }
}
