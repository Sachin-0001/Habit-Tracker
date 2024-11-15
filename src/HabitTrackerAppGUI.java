import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Habit {
    private String name;
    private int frequency;
    private int daysCompleted;

    public Habit(String name, int frequency) {
        this.name = name;
        this.frequency = frequency;
        this.daysCompleted = 0;
    }

    public String getName() {
        return name;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getDaysCompleted() {
        return daysCompleted;
    }

    public void completeDay() {
        if (daysCompleted < frequency) {
            daysCompleted++;
        }
    }

    public void resetWeek() {
        daysCompleted = 0;
    }

    public String getProgress() {
        return name + ": " + daysCompleted + "/" + frequency + " days completed";
    }
}


class HabitTracker {
    private ArrayList<Habit> habits;

    public HabitTracker() {
        habits = new ArrayList<>();
    }

    public void addHabit(String name, int frequency) {
        habits.add(new Habit(name, frequency));
    }

    public void completeHabit(String name) {
        for (Habit habit : habits) {
            if (habit.getName().equalsIgnoreCase(name)) {
                habit.completeDay();
                return;
            }
        }
    }

    public void resetAllHabits() {
        for (Habit habit : habits) {
            habit.resetWeek();
        }
    }

    public String getAllProgress() {
        StringBuilder progress = new StringBuilder();
        for (Habit habit : habits) {
            progress.append(habit.getProgress()).append("\n");
        }
        return progress.toString();
    }

    public ArrayList<String> getHabitNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Habit habit : habits) {
            names.add(habit.getName());
        }
        return names;
    }
}


public class HabitTrackerAppGUI extends JFrame implements ActionListener {
    private HabitTracker tracker;
    private JTextField habitNameField, frequencyField;
    private JTextArea progressArea;
    private JComboBox<String> habitComboBox;

    public HabitTrackerAppGUI() {
        tracker = new HabitTracker();

        setTitle("Habit Tracker");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("Weekly Habit Tracker", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(70, 130, 180));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Input Panel for adding habits
        JPanel addPanel = new JPanel(new GridBagLayout());
        addPanel.setBorder(BorderFactory.createTitledBorder("Add New Habit"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Habit Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        habitNameField = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        addPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        addPanel.add(habitNameField, gbc);

        JLabel frequencyLabel = new JLabel("Frequency (days/week):");
        frequencyLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        frequencyField = new JTextField(5);

        gbc.gridx = 0;
        gbc.gridy = 1;
        addPanel.add(frequencyLabel, gbc);
        gbc.gridx = 1;
        addPanel.add(frequencyField, gbc);

        JButton addButton = new JButton("Add Habit");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBackground(new Color(34, 139, 34));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.addActionListener(this);
        addButton.setActionCommand("AddHabit");

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        addPanel.add(addButton, gbc);

        add(addPanel, BorderLayout.WEST);

        
        JPanel completePanel = new JPanel(new GridBagLayout());
        completePanel.setBorder(BorderFactory.createTitledBorder("Track Habit Progress"));

        JLabel selectHabitLabel = new JLabel("Select Habit:");
        selectHabitLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        habitComboBox = new JComboBox<>();
        habitComboBox.setPreferredSize(new Dimension(150, 25));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        completePanel.add(selectHabitLabel, gbc);
        gbc.gridx = 1;
        completePanel.add(habitComboBox, gbc);

        JButton completeButton = new JButton("Complete Day");
        completeButton.setFont(new Font("Arial", Font.BOLD, 14));
        completeButton.setBackground(new Color(30, 144, 255));
        completeButton.setForeground(Color.WHITE);
        completeButton.setFocusPainted(false);
        completeButton.addActionListener(this);
        completeButton.setActionCommand("CompleteHabit");

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        completePanel.add(completeButton, gbc);

        add(completePanel, BorderLayout.CENTER);

        // Progress Display Area
        progressArea = new JTextArea(10, 30);
        progressArea.setEditable(false);
        progressArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        progressArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane scrollPane = new JScrollPane(progressArea);

        JPanel progressPanel = new JPanel(new BorderLayout());
        progressPanel.setBorder(BorderFactory.createTitledBorder("Weekly Progress"));
        progressPanel.add(scrollPane, BorderLayout.CENTER);

        add(progressPanel, BorderLayout.EAST);

        // Reset Button
        JButton resetButton = new JButton("Reset for New Week");
        resetButton.setFont(new Font("Arial", Font.BOLD, 14));
        resetButton.setBackground(new Color(178, 34, 34));
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusPainted(false);
        resetButton.addActionListener(this);
        resetButton.setActionCommand("Reset");

        add(resetButton, BorderLayout.SOUTH);

        // Logout Button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.setBackground(new Color(255, 69, 0));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
        logoutButton.addActionListener(this);
        logoutButton.setActionCommand("Logout");

        add(logoutButton, BorderLayout.NORTH); // Place the logout button

        updateProgress();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "AddHabit":
                String name = habitNameField.getText();
                try {
                    int frequency = Integer.parseInt(frequencyField.getText());
                    tracker.addHabit(name, frequency);
                    habitComboBox.addItem(name);
                    habitNameField.setText("");
                    frequencyField.setText("");
                    updateProgress();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid number for frequency.");
                }
                break;

            case "CompleteHabit":
                String selectedHabit = (String) habitComboBox.getSelectedItem();
                if (selectedHabit != null) {
                    tracker.completeHabit(selectedHabit);
                    updateProgress();
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a habit to complete.");
                }
                break;

            case "Reset":
                tracker.resetAllHabits();
                updateProgress();
                break;

            case "Logout":
                this.setVisible(false); // Hide the HabitTrackerAppGUI
                new LoginPage().setVisible(true); // Show the login page
                break;
        }
    }

    private void updateProgress() {
        progressArea.setText(tracker.getAllProgress());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HabitTrackerAppGUI app = new HabitTrackerAppGUI();
            app.setVisible(true);
        });
    }
}
