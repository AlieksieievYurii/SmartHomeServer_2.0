package task;

import exceptions.TaskModeException;

public enum TaskMode
{
    once("once"),always("always");

    private String inJson;

    TaskMode(String inJson) {
        this.inJson = inJson;
    }

    public static TaskMode getTaskModeByName(String name) throws TaskModeException {
        if(once.inJson.equals(name))
            return once;
        else if(always.inJson.equals(name))
            return always;
        else
            throw new TaskModeException(name);
    }
}
