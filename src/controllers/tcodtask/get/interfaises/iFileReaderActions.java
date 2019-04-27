package controllers.tcodtask.get.interfaises;

import action.Action;
import action.Device;

import java.util.List;

public interface iFileReaderActions
{
    List<Action> getActions(Device forDevice);
}
