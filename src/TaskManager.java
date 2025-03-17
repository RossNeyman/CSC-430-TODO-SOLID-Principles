import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager implements ITaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String title, String description, Priority priority) {
        tasks.add(new Task(title, description, priority));
    }

    public boolean markTaskAsCompleted(int taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                task.setCompleted(true);
                return true;
            }
        }
        return false;
    }

    public List<Task> listAllTasks() {
        return new ArrayList<>(tasks);
    }

    public List<Task> listTasksByStatus(boolean completed) {
        return tasks.stream()
                .filter(task -> task.isCompleted() == completed)
                .collect(Collectors.toList());
    }

    public boolean removeTask(int taskId) {
        return tasks.removeIf(task -> task.getId() == taskId);
    }
}
