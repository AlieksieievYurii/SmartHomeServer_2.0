package request.post.task;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import exceptions.TaskException;
import task.Task;

import javax.servlet.http.HttpServletRequest;

public class RequestTask implements iRequestTask
{
    private static final String API_ACTION = "data";
    @Override
    public Task getTask(HttpServletRequest request) throws TaskException
    {
        final String data = request.getParameter(API_ACTION);
        try
        {
            return Task.getTaskByJson(new JsonParser().parse(data).getAsJsonObject());
        }catch (JsonSyntaxException | IllegalStateException  e)
        {
            throw new TaskException(e.getMessage());
        }catch (NullPointerException e)
        {
            throw new TaskException("data<Task> is empty!");
        }

    }
}
