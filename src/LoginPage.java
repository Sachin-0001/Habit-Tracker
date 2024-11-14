import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {

    // Main GUI components
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel errorLabel;

    public LoginPage() {
        // Initialize frame
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Center the frame
        
        // Panel for the login form
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(240, 248, 255)); // Light background color
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Set padding for each component
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Username label and field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Password label and field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Login button
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(70, 130, 180)); // Set button color
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        
        // Error label
        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        
        // Set gridbag constraints for username label and field
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);
        
        gbc.gridx = 1;
        panel.add(usernameField, gbc);
        
        // Set gridbag constraints for password label and field
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);
        
        gbc.gridx = 1;
        panel.add(passwordField, gbc);
        
        // Set gridbag constraints for the login button
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(loginButton, gbc);
        
        // Set gridbag constraints for the error label
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(errorLabel, gbc);
        
        // Add panel to the frame
        frame.add(panel, BorderLayout.CENTER);
        
        // Set action for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                
                // Validate credentials (replace this with your actual validation logic)
                if (validateLogin(username, password)) {
                    // Assuming the user ID is the username (you can modify this logic)
                    String userId = username; 

                    // Check if the user is an admin or a regular user
                    if ("admin".equals(username)) {
                        openAdminPage();  // Open Admin page if username is "admin"
                    } else {
                        openHabitTracker(userId);  // Open Habit Tracker for regular user
                    }
                } else {
                    errorLabel.setText("Invalid username or password.");
                }
            }
        });
        
        // Set frame visibility
        frame.setVisible(true);
    }
    
    // Dummy method for validating login (replace with your actual logic)
    private boolean validateLogin(String username, char[] password) {
        // Simple validation logic: "admin" and "user" for username, "password" for password
        return ("admin".equals(username) || "user".equals(username)) && "password".equals(new String(password));
    }
    
    // Method to open the Admin page
    private void openAdminPage() {
        // Close the login frame
        frame.setVisible(false);
        
        // Create and show the Admin page
        Adminpage adminPage = new Adminpage();
        adminPage.showAdminPage();  // Assuming your Adminpage has a method to display the page
    }
    
    // Method to open the Habit Tracker for regular user
    private void openHabitTracker(String userId) {
        // Close the login frame
        frame.setVisible(false);
        
        // Create and show the Habit Tracker for the logged-in user
        HabitTrackerAppGUI habitTrackerAppGUI = new HabitTrackerAppGUI();
        habitTrackerAppGUI.setVisible(true);  // Assuming your HabitTrackerAppGUI has a method to display the tracker
    }
    
    public static void main(String[] args) {
        new LoginPage();
    }
}
