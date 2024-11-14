public class Habit {
    private String name;
    private int targetFrequency;  // Target frequency for the week
    private int completedDays;    // Number of days completed so far

    public Habit(String name, int targetFrequency) {
        this.name = name;
        this.targetFrequency = targetFrequency;
        this.completedDays = 0;  // Initialize completed days to zero
    }

    public String getName() {
        return name;
    }

    public int getTargetFrequency() {
        return targetFrequency;
    }

    public int getCompletedDays() {
        return completedDays;
    }

    public void incrementCompletedDays() {
        if (completedDays < targetFrequency) {
            completedDays++;
        }
    }

    public void resetCompletedDays() {
        completedDays = 0;
    }
}
