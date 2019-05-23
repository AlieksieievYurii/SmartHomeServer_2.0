package utils.files;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import exceptions.TaskException;
import main.Manifest;
import components.task.Task;
import utils.files.tools.FileReader;
import utils.files.tools.iReadFile;
import utils.files.tools.iReaderTasks;
import utils.JsonUtils;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.List;

public class FileReaderTasks implements iReaderTasks
{

    private iReadFile readFile;

    public FileReaderTasks(ServletContext servletContext) {
        this.readFile = new FileReader(servletContext.getRealPath(Manifest.FILE_TASKS));
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

    @Override
    public String getAsString() {
        try {
            return readFile.readFile();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
