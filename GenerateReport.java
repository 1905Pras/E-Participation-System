
import javax.swing.*;
import java.sql.*;

public class GenerateReport extends JFrame {
    JTable responsesTable;

    public GenerateReport() {
        setTitle("Generate Report");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        responsesTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(responsesTable);
        scrollPane.setBounds(20, 20, 550, 300);
        add(scrollPane);

        loadResponses();
    }

    private void loadResponses() {
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT s.title, r.user_id, r.response FROM Responses r JOIN Surveys s ON r.survey_id = s.survey_id");
             ResultSet rs = stmt.executeQuery()) {

            responsesTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {},
                new String[] { "Survey Title", "User ID", "Response" }
            ));

            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) responsesTable.getModel();
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getString("title"),
                    rs.getInt("user_id"),
                    rs.getString("response")
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
