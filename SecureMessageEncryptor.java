package za.ac.tut.encryption;

public class SecureMessageEncryptor {
    // Caesar cipher shift = 3
    public static String encrypt(String plainText) {
        if (plainText == null) return "";
        StringBuilder sb = new StringBuilder();
        for (char ch : plainText.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                sb.append((char) ('A' + (ch - 'A' + 3) % 26));
            } else if (ch >= 'a' && ch <= 'z') {
                sb.append((char) ('a' + (ch - 'a' + 3) % 26));
            } else {
                sb.append(ch); // keep digits, spaces, punctuation
            }
        }
        return sb.toString();
    }

    private SecureMessageEncryptor() {} // utility class
}
