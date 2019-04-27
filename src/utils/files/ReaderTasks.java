package utils.files;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import exceptions.TaskException;
import task.Task;
import utils.json.JsonUtils;

import java.io.IOException;
import java.util.List;

public class ReaderTasks implements iReaderTasks
{

    private iReadFile readFile;

    public ReaderTasks(String path) {
        this.readFile = new FileReader(path);
    }

    @Override
    public List<Task> getTasks()
    {
        try {
            final JsonArray jsonElements = new JsonParser().parse(readFile.readFile()).getAsJsonArray();
            return JsonUtils.getListTasks(jsonElements);
        } catch (IOException | TaskException e) {
            e.printStackTrace();
            return null;
        }
    }
}
