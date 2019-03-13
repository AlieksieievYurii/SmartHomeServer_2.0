package request.post;

import action.Action;
import request.post.interfaises.FileWorker;
import utils.files.FileReader;
import utils.files.FileWriter;
import utils.json.JsonUtils;

import java.util.List;

public class FileEmployer implements FileWorker
{
   private FileReader fileReader;
   private FileWriter fileWriter;

    public FileEmployer(String path) {
        fileReader = new FileReader(path);
        fileWriter = new FileWriter(path);
    }

    @Override
    public List<Action> readFile() {
        return fileReader.getActions();
    }

    @Override
    public void writeFile(List<Action> actions)
    {
       fileWriter.write(JsonUtils.toJsonArray(actions).toString());
    }
}
