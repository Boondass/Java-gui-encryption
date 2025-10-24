package za.ac.tut.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SecureMessagesFrame extends JFrame {
    private JTextArea inputArea;
    private JTextArea outputArea;
    private JButton encryptBtn;
    private JButton decryptBtn;
    private JButton exitBtn;

    public SecureMessagesFrame() {
        setTitle("Secure Messages App");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panels
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // Components
        inputArea = new JTextArea(5, 40);
        outputArea = new JTextArea(5, 40);
        outputArea.setEditable(false);

        encryptBtn = new JButton("Encrypt");
        decryptBtn = new JButton("Decrypt");
        exitBtn = new JButton("Exit");

        // Add buttons to panel
        buttonPanel.add(encryptBtn);
        buttonPanel.add(decryptBtn);
        buttonPanel.add(exitBtn);

        // Add components to main panel
        panel.add(new JScrollPane(inputArea), BorderLayout.NORTH);
        panel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        // Button actions
        encryptBtn.addActionListener(e -> {
            String text = inputArea.getText();
            outputArea.setText(encrypt(text));
        });

        decryptBtn.addActionListener(e -> {
            String text = inputArea.getText();
            outputArea.setText(decrypt(text));
        });

        exitBtn.addActionListener(e -> System.exit(0));
    }

    // Caesar cipher (shift 3)
    private String encrypt(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                result.append((char) ((c - base + 3) % 26 + base));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    private String decrypt(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                result.append((char) ((c - base - 3 + 26) % 26 + base));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
