/**
 * Main class for the To-Do List Manager application.
 * Handles user interaction and task management functions.
 */
import java.util.Scanner;
public class Main {
    private static ITaskManager taskManager = new TaskManager();
    private static TaskPrinter printer = new TaskPrinter();
    private static PrioritySorter sorter = new PrioritySorter();
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Entry point for the To-Do List Manager application.
     * Manages user input and invokes corresponding task management methods.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        boolean running = true;

        System.out.println("Welcome to To-Do List Manager!");

        while (running) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    markTaskAsCompleted();
                    break;
                case 3:
                    listAllTasks();
                    break;
                case 4:
                    listTasksByStatus();
                    break;
                case 5:
                    removeTask();
                    break;
                case 6:
                    listTasksByPriority();
                    break;
                case 0:
                    running = false;
                    System.out.println("Thank you for using To-Do List Manager!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    /**
     * Displays the main menu options to the user.
     */
    private static void displayMenu() {
        System.out.println("\n==== TO-DO LIST MANAGER ====");
        System.out.println("1. Add a new task");
        System.out.println("2. Mark task as completed");
        System.out.println("3. List all tasks");
        System.out.println("4. List tasks by status");
        System.out.println("5. Remove a task");
        System.out.println("6. List tasks by priority");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    /**
     * Gets the user's menu choice.
     *
     * @return The chosen menu option as an integer.
     */
    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Prompts the user to add a new task, including title, description, and priority level.
     */
    private static void addTask() {
        System.out.println("\n--- Add a New Task ---");

        System.out.print("Enter task title: ");
        String title = scanner.nextLine();

        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        System.out.println("Select priority level:");
        System.out.println("1. LOW");
        System.out.println("2. MEDIUM");
        System.out.println("3. HIGH");
        System.out.print("Enter your choice (1-3): ");

        Priority priority;
        try {
            int priorityChoice = Integer.parseInt(scanner.nextLine());
            priority = switch (priorityChoice) {
                case 1 -> Priority.LOW;
                case 2 -> Priority.MEDIUM;
                case 3 -> Priority.HIGH;
                default -> {
                    System.out.println("Invalid choice. Setting priority to MEDIUM.");
                    yield Priority.MEDIUM;
                }
            };
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Setting priority to MEDIUM.");
            priority = Priority.MEDIUM;
        }

        taskManager.addTask(title, description, priority);
        System.out.println("Task added successfully!");
    }

    /**
     * Prompts the user to mark a task as completed by entering its ID.
     */
    private static void markTaskAsCompleted() {
        System.out.println("\n--- Mark Task as Completed ---");
        listAllTasks();

        System.out.print("Enter the task ID to mark as completed: ");
        try {
            int taskId = Integer.parseInt(scanner.nextLine());
            boolean success = taskManager.markTaskAsCompleted(taskId);

            if (success) {
                System.out.println("Task #" + taskId + " marked as completed!");
            } else {
                System.out.println("Task not found with ID: " + taskId);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid task ID.");
        }
    }

    /**
     * Lists all tasks currently in the task manager.
     */
    private static void listAllTasks() {
        System.out.println("\n--- All Tasks ---");
        List<Task> tasks = taskManager.listAllTasks();
        printer.printTasks(tasks);
    }

    /**
     * Lists tasks filtered by their completion status.
     * Allows the user to choose between pending and completed tasks.
     */
    private static void listTasksByStatus() {
        System.out.println("\n--- List Tasks by Status ---");
        System.out.println("1. Pending tasks");
        System.out.println("2. Completed tasks");
        System.out.print("Enter your choice: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            List<Task> tasks;

            if (choice == 1) {
                tasks = taskManager.listTasksByStatus(false);
                printer.printTasksWithHeader("PENDING TASKS", tasks);
            } else if (choice == 2) {
                tasks = taskManager.listTasksByStatus(true);
                printer.printTasksWithHeader("COMPLETED TASKS", tasks);
            } else {
                System.out.println("Invalid choice.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid choice.");
        }
    }

    /**
     * Prompts the user to remove a task by entering its ID.
     */
    private static void removeTask() {
        System.out.println("\n--- Remove a Task ---");
        listAllTasks();

        System.out.print("Enter the task ID to remove: ");
        try {
            int taskId = Integer.parseInt(scanner.nextLine());
            boolean success = taskManager.removeTask(taskId);

            if (success) {
                System.out.println("Task #" + taskId + " removed successfully!");
            } else {
                System.out.println("Task not found with ID: " + taskId);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid task ID.");
        }
    }

    /**
     * Lists tasks sorted by priority, either in ascending or descending order.
     */
    private static void listTasksByPriority() {
        System.out.println("\n--- List Tasks by Priority ---");
        System.out.println("1. High to Low priority");
        System.out.println("2. Low to High priority");
        System.out.print("Enter your choice: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            List<Task> tasks = taskManager.listAllTasks();

            if (choice == 1) {
                List<Task> sortedTasks = sorter.sortByPriorityDescending(tasks);
                printer.printTasksWithHeader("TASKS (HIGH TO LOW PRIORITY)", sortedTasks);
            } else if (choice == 2) {
                List<Task> sortedTasks = sorter.sortByPriorityAscending(tasks);
                printer.printTasksWithHeader("TASKS (LOW TO HIGH PRIORITY)", sortedTasks);
            } else {
                System.out.println("Invalid choice.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid choice.");
        }
    }
}