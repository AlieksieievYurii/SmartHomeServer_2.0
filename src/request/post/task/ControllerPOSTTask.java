package request.post.task;
import exceptions.TaskException;
import request.post.Response;
import request.post.interfaises.iResponse;
import task.Task;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerPOSTTask {
    private iRequestTask requestTask;
    private iWriteTask writeTask;
    private iResponse response;

    private ControllerPOSTTask(iRequestTask requestTask, iWriteTask writeTask, iResponse response) {
        this.requestTask = requestTask;
        this.writeTask = writeTask;
        this.response = response;
    }

    public void execute(HttpServletRequest request) {

        final Task task;

        try {
            task = requestTask.getTask(request);
        } catch (TaskException e) {
            response.responseWRONG(e.getMessage());
            return;
        }

        if(writeTask.writeTask(task))
            response.responseOk();
        else
            response.responseWRONG("Error with writing Task");
    }

    public static ControllerPOSTTask build(ServletContext context, HttpServletResponse r)
    {
        final iRequestTask requestTask = new RequestTask();
        final iWriteTask writeTask = new WriteTask(context);
        final iResponse response = new Response(r);

        return new ControllerPOSTTask(requestTask,writeTask,response);
    }
}
