package request.post.task;
import exceptions.TaskException;
import interfaces.iRequestTask;
import interfaces.iWriteTask;
import request.post.ResponseStatus;
import interfaces.iResponseStatus;
import components.task.Task;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerPOSTTask {
    private iRequestTask requestTask;
    private iWriteTask writeTask;
    private iResponseStatus response;

    private ControllerPOSTTask(iRequestTask requestTask, iWriteTask writeTask, iResponseStatus response) {
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
        final iResponseStatus response = new ResponseStatus(r);

        return new ControllerPOSTTask(requestTask,writeTask,response);
    }
}
