
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class ParticipateSurvey extends JFrame {
    int userId;

    public ParticipateSurvey(int userId) {
        this.userId = userId;

        setTitle("Participate in Surveys");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel surveyLabel = new JLabel("Select Survey:");
        surveyLabel.setBounds(20, 20, 100, 25);
        add(surveyLabel);

        JComboBox<String> surveyCombo = new JComboBox<>();
        surveyCombo.setBounds(150, 20, 300, 25);
        add(surveyCombo);

        JLabel responseLabel = new JLabel("Your Response:");
        responseLabel.setBounds(20, 70, 100, 25);
        add(responseLabel);

        JTextArea responseArea = new JTextArea();
        responseArea.setBounds(150, 70, 300, 150);
        add(responseArea);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(150, 250, 100, 30);
        add(submitButton);

        // Load surveys
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Surveys");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                surveyCombo.addItem(rs.getString("survey_id") + " - " + rs.getString("title"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        submitButton.addActionListener(e -> {
            String selectedSurvey = (String) surveyCombo.getSelectedItem();
            if (selectedSurvey != null) {
                int surveyId = Integer.parseInt(selectedSurvey.split(" - ")[0]);
                String response = responseArea.getText();

                try (Connection conn = DatabaseConnection.connect();
                     PreparedStatement stmt = conn.prepareStatement(
                             "INSERT INTO Responses (survey_id, user_id, response) VALUES (?, ?, ?)")) {

                    stmt.setInt(1, surveyId);
                    stmt.setInt(2, userId);
                    stmt.setString(3, response);
                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Response submitted successfully!");
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
