package utils.files;


import controllers.tcodtask.iReadTasks;
import task.Task;
import utils.json.JsonUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileReader implements iReadTasks
{
    private File file;

    public FileReader(String path)
    {
        this.file = new File(path);
    }

    @Override
    public List<Task> getTasks()
    {
        try {
            final String res = readFileFrom(file);
            return JsonUtils.convertTaskToList(res);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String readFileFrom(File file) throws IOException {
        final BufferedReader bufferedReader =
                new BufferedReader(new java.io.FileReader(file));

        final StringBuilder textFromFile = new StringBuilder();

        String str;
        while((str = bufferedReader.readLine()) != null)
            textFromFile.append(str);

        return textFromFile.toString();
    }

    public String readFile()
    {
        try {
            return readFileFrom(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
