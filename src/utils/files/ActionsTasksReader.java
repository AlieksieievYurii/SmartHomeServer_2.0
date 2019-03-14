package utils.files;


import controllers.tcodtask.get.interfaises.iReadActionsTasks;
import action.Action;
import device.Device;
import main.Manifest;
import utils.json.JsonUtils;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ActionsTasksReader implements iReadActionsTasks
{
    private File fileActions;

    public ActionsTasksReader(ServletContext servletContext)
    {
        this.fileActions = new File(servletContext.getRealPath(Manifest.FILE_ACTIONS));
    }

    @Override
    public List<Action> getActions(Device forDevice)
    {
        try {
            final String res = readFileFrom(fileActions);

            if(forDevice != null) {
                List<Action> actions = JsonUtils.toListActions(res);
                return JsonUtils.selectForDevice(forDevice, actions);
            }
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
            return readFileFrom(fileActions);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
