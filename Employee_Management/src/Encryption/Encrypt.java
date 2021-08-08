package Encryption;

public class Encrypt {
    private static final String secretKey = "sjhdjsjkd@#!@!";

    public static String encrypted(String password) {
        return AESUtils.encrypt(password, secretKey);
    }
}
