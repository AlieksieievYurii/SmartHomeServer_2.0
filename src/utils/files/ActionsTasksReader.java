package utils.files;


import controllers.tcodtask.get.interfaises.iReadActionsTasks;
import action.Action;
import device.Device;
import main.Manifest;
import utils.json.JsonUtils;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.List;

public class ActionsTasksReader implements iReadActionsTasks {
    private FileReader fileReader;

    public ActionsTasksReader(ServletContext servletContext) {
        fileReader = new FileReader(servletContext.getRealPath(Manifest.FILE_ACTIONS));
    }

    @Override
    public List<Action> getActions(Device forDevice) {
        final String res;
        try {
            res = fileReader.readFile();

            if (forDevice != null) {
                List<Action> actions = JsonUtils.toListActions(res);
                return JsonUtils.selectForDevice(forDevice, actions);
            } else
                return JsonUtils.toListActions(res);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getActionsAsString()
    {
        try {
            return fileReader.readFile();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
