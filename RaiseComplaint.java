
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class RaiseComplaint extends JFrame {
    int userId;

    public RaiseComplaint(int userId) {
        this.userId = userId;

        setTitle("Raise Complaint");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel complaintLabel = new JLabel("Complaint:");
        complaintLabel.setBounds(20, 30, 80, 25);
        add(complaintLabel);

        JTextArea complaintArea = new JTextArea();
        complaintArea.setBounds(100, 30, 250, 150);
        add(complaintArea);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(150, 200, 100, 30);
        add(submitButton);

        submitButton.addActionListener(e -> {
            String complaint = complaintArea.getText();

            try (Connection conn = DatabaseConnection.connect();
                 PreparedStatement stmt = conn.prepareStatement(
                         "INSERT INTO Complaints (user_id, complaint) VALUES (?, ?)")) {

                stmt.setInt(1, userId);
                stmt.setString(2, complaint);
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Complaint submitted successfully!");
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
