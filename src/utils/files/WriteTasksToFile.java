package utils.files;

import com.google.gson.JsonArray;
import task.Task;

import java.util.List;

public class WriteTasksToFile implements iFileWriteTasks {

    private iWriteFile writeFile;

    public WriteTasksToFile(String path) {
        this.writeFile = new FileWriter(path);
    }

    @Override
    public boolean writeTasksToFile(List<Task> tasks)
    {
        final JsonArray jsonElements = new JsonArray();

        for(Task t : tasks)
            jsonElements.add(Task.toJsonObject(t));


        return writeFile.write(jsonElements.toString());
    }
}
