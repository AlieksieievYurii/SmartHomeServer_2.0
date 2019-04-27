package request.post.task;

import task.Task;
import utils.files.ReaderTasks;
import utils.files.WriteTasksToFile;
import utils.files.iFileWriteTasks;
import utils.files.iReaderTasks;

import java.util.List;

public class WriteTask implements iWriteTask {

    private iReaderTasks readerTasks;
    private iFileWriteTasks writeTasks;

    public WriteTask(String path)
    {
        readerTasks = new ReaderTasks(path);
        writeTasks = new WriteTasksToFile(path);
    }

    @Override
    public boolean writeTask(Task task)
    {
        final List<Task> tasks = readerTasks.getTasks();

        if(!replace(tasks, task))
            tasks.add(task);

        return writeTasks.writeTasksToFile(tasks);
    }

    private boolean replace(List<Task> tasks, Task task)
    {
       for(int i = 0; i < tasks.size(); i++)
       {
           Task t = tasks.get(i);

           if(t.equals(task)) {
               tasks.set(i, task);
               return true;
           }
       }
       return false;
    }
}
