
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class CreateSurvey extends JFrame {
    JTextField titleField;
    JTextArea descriptionArea;

    public CreateSurvey() {
        setTitle("Create Survey");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(20, 20, 80, 25);
        add(titleLabel);

        titleField = new JTextField();
        titleField.setBounds(100, 20, 250, 25);
        add(titleField);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(20, 60, 80, 25);
        add(descriptionLabel);

        descriptionArea = new JTextArea();
        descriptionArea.setBounds(100, 60, 250, 100);
        add(descriptionArea);

        JButton createButton = new JButton("Create");
        createButton.setBounds(100, 180, 100, 30);
        add(createButton);

        createButton.addActionListener(e -> createSurvey());
    }

    private void createSurvey() {
        String title = titleField.getText();
        String description = descriptionArea.getText();

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Surveys (title, description, created_by) VALUES (?, ?, ?)")) {
            
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setInt(3, 1); // Assuming admin ID = 1
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Survey created successfully!");
            dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
