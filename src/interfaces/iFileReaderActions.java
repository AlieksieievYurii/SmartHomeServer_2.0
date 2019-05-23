package interfaces;

import components.action.Action;
import components.action.Device;

import java.util.List;

public interface iFileReaderActions
{
    List<Action> getActions(Device forDevice);
}
