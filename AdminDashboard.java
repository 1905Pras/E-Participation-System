import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {
    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(600, 400); // Increase the window size
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Load the background image
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Prasanth M\\Documents\\NetBeansProjects\\EParticipationSystem\\src\\download.jpeg")
                .getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH))); // Adjust to window size
        backgroundLabel.setBounds(0, 0, 600, 400); // Cover entire window
        add(backgroundLabel);

        // Create and add buttons
        JButton createSurveyButton = new JButton("Create Survey");
        createSurveyButton.setBounds(50, 50, 200, 40);
        backgroundLabel.add(createSurveyButton);

        JButton viewComplaintsButton = new JButton("View Complaints");
        viewComplaintsButton.setBounds(50, 120, 200, 40);
        backgroundLabel.add(viewComplaintsButton);

        JButton generateReportButton = new JButton("Generate Reports");
        generateReportButton.setBounds(50, 190, 200, 40);
        backgroundLabel.add(generateReportButton);

        // Add action listeners to buttons
        createSurveyButton.addActionListener(e -> new CreateSurvey().setVisible(true));
        viewComplaintsButton.addActionListener(e -> new ViewComplaints().setVisible(true));
        generateReportButton.addActionListener(e -> new GenerateReport().setVisible(true));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminDashboard adminDashboard = new AdminDashboard();
            adminDashboard.setVisible(true);
        });
    }
}
