package request.post.interfaises;

import action.Action;

import java.util.List;

public interface FileWorker
{
    List<Action> readFile();
    void writeFile(List<Action> actions);
}
