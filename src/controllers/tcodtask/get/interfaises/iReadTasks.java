package controllers.tcodtask.get.interfaises;

import action.Action;
import device.Device;

import java.util.List;

public interface iReadTasks
{
    List<Action> getActions(Device forDevice);
}
