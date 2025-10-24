package securemessagesapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SecureMessagesApp extends JFrame {
    private JTextArea plainTextArea;
    private JTextArea encryptedTextArea;

    public SecureMessagesApp() {
        setTitle("Secure Messages");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 🔹 Layout
        setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("Message Encryptor", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 22));
        titleLabel.setForeground(Color.BLUE);
        add(titleLabel, BorderLayout.NORTH);

        // Text Areas Panel
        JPanel textPanel = new JPanel(new GridLayout(2, 2));

        JLabel plainLabel = new JLabel("Plain message");
        plainTextArea = new JTextArea(5, 20);
        JScrollPane plainScroll = new JScrollPane(plainTextArea);

        JLabel encryptedLabel = new JLabel("Encrypted message");
        encryptedTextArea = new JTextArea(5, 20);
        JScrollPane encryptedScroll = new JScrollPane(encryptedTextArea);

        textPanel.add(plainLabel);
        textPanel.add(encryptedLabel);
        textPanel.add(plainScroll);
        textPanel.add(encryptedScroll);

        add(textPanel, BorderLayout.CENTER);

        // 🔹 Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem openItem = new JMenuItem("Open file...");
        JMenuItem encryptItem = new JMenuItem("Encrypt message...");
        JMenuItem saveItem = new JMenuItem("Save encrypted message...");
        JMenuItem clearItem = new JMenuItem("Clear");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(openItem);
        fileMenu.add(encryptItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(clearItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // 🔹 Event Handlers
        openItem.addActionListener(e -> openFile());
        encryptItem.addActionListener(e -> encryptMessage());
        saveItem.addActionListener(e -> saveFile());
        clearItem.addActionListener(e -> {
            plainTextArea.setText("");
            encryptedTextArea.setText("");
        });
        exitItem.addActionListener(e -> System.exit(0));
    }

    // --- Helper Methods ---
    private void openFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile()))) {
                plainTextArea.read(br, null);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error opening file.");
            }
        }
    }

    private void encryptMessage() {
        String plain = plainTextArea.getText();
        StringBuilder encrypted = new StringBuilder();
        for (char c : plain.toCharArray()) {
            encrypted.append((char) (c + 3)); // Caesar shift
        }
        encryptedTextArea.setText(encrypted.toString());
    }

    private void saveFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(chooser.getSelectedFile()))) {
                encryptedTextArea.write(bw);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SecureMessagesApp().setVisible(true);
        });
    }
}
