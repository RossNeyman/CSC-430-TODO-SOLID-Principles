import java.util.List;


/**
 * Interface for task management operations.
 * Follows the Interface Segregation Principle by providing focused task management methods.
 */
public interface ITaskManager {
    void addTask(String title, String description, Priority priority);
    boolean markTaskAsCompleted(int taskId);
    List<Task> listAllTasks();
    List<Task> listTasksByStatus(boolean completed);
    boolean removeTask(int taskId);
}
