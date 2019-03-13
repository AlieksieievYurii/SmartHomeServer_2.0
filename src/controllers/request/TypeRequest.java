package controllers.request;

public enum TypeRequest {
    HASH_CODE("hashCode"), TASKS("tasks"), //It's for ManagerDevices

    ACTION("action"), TASK("task"),// It's for ListenerTasks
    HASH_CODE_ACTIONS("hashCodeActions"), HASH_CODE_TASKS("hashCodeTasks"),
    ACTIONS("actions");

    private String typeRequest;

    TypeRequest(String typeRequest) {
        this.typeRequest = typeRequest;
    }

    public String getTypeRequest() {
        return typeRequest;
    }

    public static TypeRequest whatTypeRequest(String s) {
        switch (s) {
            case "hashCode":
                return HASH_CODE;
            case "tasks":
                return TASKS;
            case "action":
                return ACTION;
            case "task":
                return TASK;
            case "hashCodeActions":
                return HASH_CODE_ACTIONS;
            case "hashCodeTasks":
                return HASH_CODE_TASKS;
            case "actions":
                return ACTIONS;
            default:
                return null;
        }
    }
}
