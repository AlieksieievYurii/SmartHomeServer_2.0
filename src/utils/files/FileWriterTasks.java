package utils.files;

import com.google.gson.JsonArray;
import main.Manifest;
import components.task.Task;
import utils.files.tools.FileWriter;
import utils.files.tools.iFileWriteTasks;
import utils.files.tools.iWriteFile;
import utils.JsonUtils;

import javax.servlet.ServletContext;
import java.util.List;

public class FileWriterTasks implements iFileWriteTasks {

    private iWriteFile writeFile;

    public FileWriterTasks(ServletContext servletContext) {
        this.writeFile = new FileWriter(servletContext.getRealPath(Manifest.FILE_TASKS));
    }

    @Override
    public boolean writeTasksToFile(List<Task> tasks)
    {
        final JsonArray jsonElements = JsonUtils.tasksToJsonArray(tasks);
        return writeFile.write(jsonElements.toString());
    }
}
