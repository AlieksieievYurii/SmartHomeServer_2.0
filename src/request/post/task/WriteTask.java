package request.post.task;

import task.Task;
import utils.files.FileReaderTasks;
import utils.files.FileWriterTasks;
import utils.files.tools.iFileWriteTasks;
import utils.files.tools.iReaderTasks;

import javax.servlet.ServletContext;
import java.util.List;

public class WriteTask implements iWriteTask {

    private iReaderTasks readerTasks;
    private iFileWriteTasks writeTasks;

    public WriteTask(ServletContext servletContext)
    {
        readerTasks = new FileReaderTasks(servletContext);
        writeTasks = new FileWriterTasks(servletContext);
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
