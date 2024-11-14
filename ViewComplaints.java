
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class ViewComplaints extends JFrame {
    JTable complaintsTable;

    public ViewComplaints() {
        setTitle("View Complaints");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        

        complaintsTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(complaintsTable);
        scrollPane.setBounds(20, 20, 550, 300);
        add(scrollPane);

        JButton markReviewedButton = new JButton("Mark as Reviewed");
        markReviewedButton.setBounds(20, 330, 150, 30);
        add(markReviewedButton);

        loadComplaints();

        markReviewedButton.addActionListener(e -> markAsReviewed());
    }

    private void loadComplaints() {
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Complaints");
             ResultSet rs = stmt.executeQuery()) {

            complaintsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {},
                new String[] { "ID", "User ID", "Complaint", "Status" }
            ));

            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) complaintsTable.getModel();
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getInt("complaint_id"),
                    rs.getInt("user_id"),
                    rs.getString("complaint"),
                    rs.getString("status")
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void markAsReviewed() {
        int selectedRow = complaintsTable.getSelectedRow();
        if (selectedRow != -1) {
            int complaintId = (int) complaintsTable.getValueAt(selectedRow, 0);

            try (Connection conn = DatabaseConnection.connect();
                 PreparedStatement stmt = conn.prepareStatement(
                         "UPDATE Complaints SET status = 'Reviewed' WHERE complaint_id = ?")) {

                stmt.setInt(1, complaintId);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Complaint marked as reviewed!");
                loadComplaints();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a complaint to mark as reviewed!");
        }
    }
}
