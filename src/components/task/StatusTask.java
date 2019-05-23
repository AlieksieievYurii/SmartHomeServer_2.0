package components.task;

import exceptions.StatusTaskException;

public enum StatusTask
{
    enable("enable"),disable("disable");

    private String inJson;

    StatusTask(String inJson) {
        this.inJson = inJson;
    }

    public String getInJson() {
        return inJson;
    }

    public static StatusTask getStatusTasksByName(String name) throws StatusTaskException {
        if(enable.inJson.equals(name))
            return enable;
        else if(disable.inJson.equals(name))
            return disable;
        else
            throw new StatusTaskException(name);
    }
}
