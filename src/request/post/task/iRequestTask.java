package request.post.task;

import exceptions.TaskException;
import task.Task;

import javax.servlet.http.HttpServletRequest;

public interface iRequestTask
{
    Task getTask(HttpServletRequest request) throws TaskException;
}
