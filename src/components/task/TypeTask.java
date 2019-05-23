package components.task;

import exceptions.TypeTaskException;

public enum TypeTask {
    Timer("timer");

    private String inJson;

    TypeTask(String inJson) {
        this.inJson = inJson;
    }

    public String getInJson() {
        return inJson;
    }

    public static TypeTask getTypeTasksByName(String name) throws TypeTaskException {
        switch (name) {
            case "timer":
                return Timer;

            default:
                throw new TypeTaskException(name);
        }
    }
}
