package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashPassword {
  private static final String SHA_512 = "SHA-512";

  private HashPassword() {
  }

  public static String getShaSecurePassword(
          final String passwordToHash,
          final String salt) {
    String generatedPassword = null;
    try {
      MessageDigest messageDigest = MessageDigest.getInstance(SHA_512);
      messageDigest.update(salt.getBytes(StandardCharsets.UTF_8));
      byte[] bytes = messageDigest
              .digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < bytes.length; i++) {
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }
      generatedPassword = sb.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return generatedPassword;
  }
}
