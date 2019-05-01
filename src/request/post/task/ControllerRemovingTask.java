package request.post.task;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import request.post.Response;
import request.post.interfaises.iResponse;
import task.Task;
import utils.files.FileReaderTasks;
import utils.files.FileWriterTasks;
import utils.files.tools.iFileWriteTasks;
import utils.files.tools.iReaderTasks;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ControllerRemovingTask
{
    private iReaderTasks readerTasks;
    private iFileWriteTasks fileWriteTasks;
    private iResponse response;

    private ControllerRemovingTask(iReaderTasks readerTasks,
                                  iFileWriteTasks fileWriteTasks,
                                  iResponse response) {
        this.readerTasks = readerTasks;
        this.fileWriteTasks = fileWriteTasks;
        this.response = response;
    }

    public void execute(HttpServletRequest request)
    {
        final String data = request.getParameter("data");
        if(data != null)
        {
            final JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
            int id = jsonObject.get("id").getAsInt();
            if(isTaskWithId(id))
                removeTask(id);
            else
                response.responseWRONG("Not found task[id:"+id+"]");
        }else
            response.responseWRONG("Data is null");

    }

    private void removeTask(int id) {
        final List<Task> tasks = readerTasks.getTasks();

        for(int i = 0; i < tasks.size(); i++)
            if(tasks.get(i).getId() == id) {
                tasks.remove(i);
                break;
            }

        fileWriteTasks.writeTasksToFile(tasks);
        response.responseOk();
    }

    private boolean isTaskWithId(int id)
    {
        final List<Task> tasks = readerTasks.getTasks();

        for(Task t : tasks)
            if(t.getId() == id)
                return true;
        return false;
    }

    public static ControllerRemovingTask build(ServletContext context, HttpServletResponse r)
    {
        final iReaderTasks readerTasks = new FileReaderTasks(context);
        final iFileWriteTasks fileWriteTasks = new FileWriterTasks(context);
        final iResponse response = new Response(r);

        return new ControllerRemovingTask(readerTasks,fileWriteTasks,response);
    }
}
