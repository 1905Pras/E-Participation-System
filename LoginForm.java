import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginForm extends JFrame {
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;

    public LoginForm() {
        setTitle("E-Participation System - Login");

        // Set the window size
        setSize(600, 400);

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use absolute layout for custom positioning
        setLayout(null);

        // Add a background image (cover entire window)
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Prasanth M\\Documents\\NetBeansProjects\\EParticipationSystem\\src\\download.jpeg")
                .getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH))); // Adjust to window size
        backgroundLabel.setBounds(0, 0, 600, 400); // Cover entire window
        add(backgroundLabel);

        // Add a welcome message
        JLabel welcomeLabel = new JLabel("Welcome to E-Participation System. Please Login");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.WHITE); // Ensure text is visible over the image
        welcomeLabel.setBounds(100, 20, 500, 30);
        backgroundLabel.add(welcomeLabel);

        // Username Label and Field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameLabel.setBounds(150, 100, 100, 30);
        backgroundLabel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(270, 100, 180, 30);
        backgroundLabel.add(usernameField);

        // Password Label and Field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordLabel.setBounds(150, 150, 100, 30);
        backgroundLabel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(270, 150, 180, 30);
        backgroundLabel.add(passwordField);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setBounds(250, 220, 100, 30);
        backgroundLabel.add(loginButton);

        // ActionListener for login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Users WHERE username = ? AND password = ?")) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                if ("admin".equals(role)) {
                    new AdminDashboard().setVisible(true);
                } else {
                    new CitizenDashboard(rs.getInt("user_id")).setVisible(true);
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Set look and feel for better UI (optional)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new LoginForm().setVisible(true);
    }
}
