package utils.files;


import controllers.tcodtask.get.interfaises.iReadTasks;
import action.Action;
import device.Device;
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
    public List<Action> getActions(Device forDevice)
    {
        try {
            final String res = readFileFrom(file);
            List<Action> actions = JsonUtils.toListActions(res);

            if(forDevice != null)
                return JsonUtils.selectForDevice(forDevice,actions);
            else
                return JsonUtils.toListActions(res);
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
