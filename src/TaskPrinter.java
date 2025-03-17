
import java.util.List;

public class TaskPrinter {
    public void printTask(Task task) {
        System.out.println(task);
    }

    public void printTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        for (Task task : tasks) {
            printTask(task);
            System.out.println(); // Empty line for better readability
        }
    }

    public void printTasksWithHeader(String header, List<Task> tasks) {
        System.out.println("===== " + header + " =====");
        printTasks(tasks);
    }
}