
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class SuggestIdea extends JFrame {
    int userId;

    public SuggestIdea(int userId) {
        this.userId = userId;

        setTitle("Suggest Idea");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel ideaLabel = new JLabel("Idea:");
        ideaLabel.setBounds(20, 30, 80, 25);
        add(ideaLabel);

        JTextArea ideaArea = new JTextArea();
        ideaArea.setBounds(100, 30, 250, 150);
        add(ideaArea);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(150, 200, 100, 30);
        add(submitButton);

        submitButton.addActionListener(e -> {
            String idea = ideaArea.getText();

            try (Connection conn = DatabaseConnection.connect();
                 PreparedStatement stmt = conn.prepareStatement(
                         "INSERT INTO Suggestions (user_id, suggestion) VALUES (?, ?)")) {

                stmt.setInt(1, userId);
                stmt.setString(2, idea);
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Idea submitted successfully!");
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
