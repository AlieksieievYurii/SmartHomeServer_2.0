package utils.files.tools;

import task.Task;

import java.util.List;

public interface iFileWriteTasks
{
    boolean writeTasksToFile(List<Task> tasks);
}
