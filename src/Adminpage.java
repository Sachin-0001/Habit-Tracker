import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Adminpage {

    private JFrame adminFrame;
    private JButton viewUsersButton;
    private JButton manageSettingsButton;
    private JButton logoutButton;

    public Adminpage() {
        // Initialize the frame
        adminFrame = new JFrame("Admin Dashboard");
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setSize(600, 400);
        adminFrame.setLayout(new BorderLayout());
        adminFrame.setLocationRelativeTo(null); // Center the frame

        // Panel to hold content
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(230, 230, 250)); // Light lavender background
        GridBagConstraints gbc = new GridBagConstraints();

        // Set padding for each component
        gbc.insets = new Insets(10, 10, 10, 10);

        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome Admin!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(70, 130, 180)); // Set a nice color

        // Set gridbag constraints for the welcome label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(welcomeLabel, gbc);

        // View Users button
        viewUsersButton = new JButton("View Users");
        viewUsersButton.setFont(new Font("Arial", Font.BOLD, 16));
        viewUsersButton.setBackground(new Color(70, 130, 180));
        viewUsersButton.setForeground(Color.WHITE);
        viewUsersButton.setFocusPainted(false);

        // Set gridbag constraints for the View Users button
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(viewUsersButton, gbc);

        // Manage Settings button
        manageSettingsButton = new JButton("Manage Settings");
        manageSettingsButton.setFont(new Font("Arial", Font.BOLD, 16));
        manageSettingsButton.setBackground(new Color(70, 130, 180));
        manageSettingsButton.setForeground(Color.WHITE);
        manageSettingsButton.setFocusPainted(false);

        // Set gridbag constraints for the Manage Settings button
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(manageSettingsButton, gbc);

        // Logout button
        logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        logoutButton.setBackground(new Color(255, 69, 0)); // Red color for logout
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);

        // Set gridbag constraints for the Logout button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(logoutButton, gbc);

        // Action for View Users button
        viewUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(adminFrame, "Displaying users... (this is a placeholder)");
            }
        });

        // Action for Manage Settings button
        manageSettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(adminFrame, "Managing settings... (this is a placeholder)");
            }
        });

        // Action for Logout button
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to login page (close admin page and show login page)
                adminFrame.setVisible(false);  // Hide the admin page
                new LoginPage();  // Show login page again
            }
        });

        // Add panel to the frame
        adminFrame.add(panel, BorderLayout.CENTER);

        // Make the frame visible
        adminFrame.setVisible(true);
    }

    // Method to open the Admin Page
    public void showAdminPage() {
        System.out.println("Admin Page Opened!");
    }

    public static void main(String[] args) {
        new Adminpage();
    }
}
