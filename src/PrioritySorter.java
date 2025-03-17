import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PrioritySorter {
    public List<Task> sortByPriorityDescending(List<Task> tasks) {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getPriority).reversed())
                .collect(Collectors.toList());
    }

    public List<Task> sortByPriorityAscending(List<Task> tasks) {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getPriority))
                .collect(Collectors.toList());
    }
}