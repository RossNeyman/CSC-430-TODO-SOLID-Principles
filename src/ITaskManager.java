import java.util.List;

/**
 * Interface for task management operations.
 * Follows the Interface Segregation Principle by providing focused task management methods.
 */
public interface ITaskManager {
    /**
     * Add a new task
     * @param task The task to add
     * @return The ID of the added task
     */
    String addTask(Task task);

    /**
     * Mark a task as completed
     * @param taskId The ID of the task to mark as completed
     * @return true if the task was found and updated, false otherwise
     */
    boolean markTaskCompleted(String taskId);

    /**
     * Get a list of all tasks
     * @return List of all tasks
     */
    List<Task> listAllTasks();

    /**
     * Get a list of tasks filtered by completion status
     * @param completed Whether to return completed or pending tasks
     * @return Filtered list of tasks
     */
    List<Task> listTasks(boolean completed);

    /**
     * Remove a task
     * @param taskId The ID of the task to remove
     * @return true if the task was found and removed, false otherwise
     */
    boolean removeTask(String taskId);
}
