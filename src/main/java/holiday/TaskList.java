package holiday;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int indexOneBased) {
        return tasks.get(indexOneBased - 1);
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task remove(int indexOneBased) {
        return tasks.remove(indexOneBased - 1);
    }
    /*
    Searches task list for Tasks matching the keyword, returns the matches
     */
    public List<Task> find(String keyword) {
        List<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                matches.add(task);
            }
        }
        return matches;
    }

}
