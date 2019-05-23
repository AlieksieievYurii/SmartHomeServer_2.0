package utils.files;


import components.action.Action;
import interfaces.iFileReaderActions;
import components.action.Device;
import exceptions.*;
import main.Manifest;
import utils.files.tools.FileReader;
import utils.JsonUtils;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.List;

public class FileReaderActions implements iFileReaderActions {
    private FileReader fileReader;

    public FileReaderActions(ServletContext servletContext) {
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

        }  catch (ActionException | IOException e) {
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
