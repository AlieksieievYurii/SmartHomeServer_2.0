package request.post;

import action.Action;
import main.Manifest;
import request.post.interfaises.FileWorker;
import utils.files.ActionsTasksReader;
import utils.files.FileWriter;
import utils.json.JsonUtils;

import javax.servlet.ServletContext;
import java.util.List;

public class FileEmployer implements FileWorker
{
   private ActionsTasksReader fileReader;
   private FileWriter fileWriter;

    public FileEmployer(ServletContext servletContext) {
        fileReader = new ActionsTasksReader(servletContext);
        fileWriter = new FileWriter(servletContext.getRealPath(Manifest.FILE_ACTIONS));
    }

    @Override
    public List<Action> readFile() {
        return fileReader.getActions(null);
    }

    @Override
    public void writeFile(List<Action> actions)
    {
       fileWriter.write(JsonUtils.toJsonArray(actions).toString());
    }
}
