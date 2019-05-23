package request.post;

import components.action.Action;
import interfaces.FileWorker;
import interfaces.iWriteAction;

import java.util.List;

public class ActionRecorder implements iWriteAction
{
    private FileWorker fileWorker;

    public ActionRecorder(FileWorker fileWorker) {
        this.fileWorker = fileWorker;
    }

    @Override
    public boolean write(Action action)
    {
        final List<Action> actions = fileWorker.readFile();

        if(action != null)
        {
            replaceActionIn(actions,action);
            fileWorker.writeFile(actions);
            return true;
        }else
            return false;
    }


    private void replaceActionIn(List<Action> listAction, Action action)
    {
        for(Action al : listAction)
            if(al.isEquals(action))
            {
                listAction.remove(al);
                listAction.add(action);
                return;
            }

        listAction.add(action);
    }
}
