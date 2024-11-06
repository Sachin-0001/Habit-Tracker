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
}

public class HabitTrackerAppGUI extends JFrame implements ActionListener {
    private HabitTracker tracker;
    private JTextField habitNameField, frequencyField;
    private JTextArea progressArea;

    public HabitTrackerAppGUI() {
        tracker = new HabitTracker();

        setTitle("Habit Tracker");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel addPanel = new JPanel();
        addPanel.setLayout(new FlowLayout());
        
        addPanel.add(new JLabel("Habit Name:"));
        habitNameField = new JTextField(10);
        addPanel.add(habitNameField);
        
        addPanel.add(new JLabel("Frequency (days/week):"));
        frequencyField = new JTextField(5);
        addPanel.add(frequencyField);

        JButton addButton = new JButton("Add Habit");
        addButton.addActionListener(this);
        addButton.setActionCommand("AddHabit");
        addPanel.add(addButton);

        JPanel completePanel = new JPanel();
        completePanel.setLayout(new FlowLayout());

        JButton completeButton = new JButton("Complete Day");
        completeButton.addActionListener(this);
        completeButton.setActionCommand("CompleteHabit");
        completePanel.add(completeButton);

        progressArea = new JTextArea(10, 30);
        progressArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(progressArea);


        JButton resetButton = new JButton("Reset for New Week");
        resetButton.addActionListener(this);
        resetButton.setActionCommand("Reset");

        
        add(addPanel, BorderLayout.NORTH);
        add(completePanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
        add(resetButton, BorderLayout.PAGE_END);
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
                    habitNameField.setText("");
                    frequencyField.setText("");
                    updateProgress(); 
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid number for frequency.");
                }
                break;
    
            case "CompleteHabit":
                name = habitNameField.getText();
                tracker.completeHabit(name);
                updateProgress();
                break;
    
            case "Reset":
                tracker.resetAllHabits();
                updateProgress();
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
