package interfaces;

import exceptions.TaskException;
import components.task.Task;

import javax.servlet.http.HttpServletRequest;

public interface iRequestTask
{
    Task getTask(HttpServletRequest request) throws TaskException;
}
