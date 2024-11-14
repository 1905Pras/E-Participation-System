import java.awt.Image;
import javax.swing.*;

public class CitizenDashboard extends JFrame {
    int userId;

    public CitizenDashboard(int userId) {
        this.userId = userId;

        setTitle("Citizen Dashboard");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Prasanth M\\Documents\\NetBeansProjects\\EParticipationSystem\\src\\download.jpeg")
                .getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH))); // Adjust to window size
        backgroundLabel.setBounds(0, 0, 600, 400); // Cover entire window
        add(backgroundLabel);

        JButton participateSurveyButton = new JButton("Participate in Survey");
        participateSurveyButton.setBounds(50, 50, 200, 30);
        add(participateSurveyButton);

        JButton raiseComplaintButton = new JButton("Raise Complaint");
        raiseComplaintButton.setBounds(50, 100, 200, 30);
        add(raiseComplaintButton);

        JButton suggestIdeaButton = new JButton("Suggest Idea");
        suggestIdeaButton.setBounds(50, 150, 200, 30);
        add(suggestIdeaButton);

        participateSurveyButton.addActionListener(e -> new ParticipateSurvey(userId).setVisible(true));
        raiseComplaintButton.addActionListener(e -> new RaiseComplaint(userId).setVisible(true));
        suggestIdeaButton.addActionListener(e -> new SuggestIdea(userId).setVisible(true));
    }
}
