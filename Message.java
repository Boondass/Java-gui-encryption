package za.ac.tut.message;

public class Message {
    private String plainText;
    private String encryptedText;

    public Message() {
        this("", "");
    }

    public Message(String plainText, String encryptedText) {
        this.plainText = plainText;
        this.encryptedText = encryptedText;
    }

    public String getPlainText() { return plainText; }
    public void setPlainText(String plainText) { this.plainText = plainText; }

    public String getEncryptedText() { return encryptedText; }
    public void setEncryptedText(String encryptedText) { this.encryptedText = encryptedText; }
}
