package utils.files.tools;

import components.task.Task;

import java.util.List;

public interface iFileWriteTasks
{
    boolean writeTasksToFile(List<Task> tasks);
}
