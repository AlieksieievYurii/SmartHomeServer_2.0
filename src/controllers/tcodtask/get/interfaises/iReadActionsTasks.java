package controllers.tcodtask.get.interfaises;

import action.Action;
import device.Device;

import java.util.List;

public interface iReadActionsTasks
{
    List<Action> getActions(Device forDevice);
}
