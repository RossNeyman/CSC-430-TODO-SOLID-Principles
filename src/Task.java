import java.util.UUID;

public class Task {
    private final int id;
    private String title;
    private String description;
    private Priority priority;
    private boolean completed;
    private static int nextId = 1;

    /**
     * Constructor with auto-generated ID
     */
    public Task(String title, String description, Priority priority) {
        this.id = nextId++;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.completed = false;
    }

    /**
     * Constructor with provided ID (useful for loading from storage)
     */
    public Task(int id, String title, String description, Priority priority, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.completed = completed;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", completed=" + completed +
                '}';
    }
}