package controllers.request;

public enum TypeRequest
{
    HASH_CODE("hashCode"),TASKS("tasks"), //It's for ManagerDevices

    ACTION("action"),TASK("task");// It's for ListenerTasks

    private String typeRequest;

    TypeRequest(String typeRequest) {
        this.typeRequest = typeRequest;
    }

    public String getTypeRequest() {
        return typeRequest;
    }

    public static TypeRequest whatTypeRequest(String s)
    {
        switch (s) {
            case "hashCode":
                return HASH_CODE;
            case "tasks":
                return TASKS;
            case "action":
                return ACTION;
            case "task":
                return TASK;
            default:
                return null;
        }
    }
}
