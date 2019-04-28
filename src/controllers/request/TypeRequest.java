package controllers.request;

import exceptions.TypeRequestException;

public enum TypeRequest {
    POST_ACTION("postAction"), POST_TASK("postTask"),// It's for ListenerTasks
    GET_ACTIONS("getActions"),GET_HASH_CODE_ACTIONS("getHashCodeActions"),
    GET_TASKS("getTasks"),GET_HASH_CODE_TASKS("getHashCodeTasks"),
    GET_SENSORS("getSensors"),GET_HASH_CODE_SENSORS("getHashCodeSensors");

    private String typeRequest;

    TypeRequest(String typeRequest) {
        this.typeRequest = typeRequest;
    }

    public String getTypeRequest() {
        return typeRequest;
    }

    public static TypeRequest whatTypeRequest(String s) throws TypeRequestException {
        switch (s) {
            case "postAction":
                return POST_ACTION;
            case "postTask":
                return POST_TASK;
            case "getHashCodeActions":
                return GET_HASH_CODE_ACTIONS;
            case "getHashCodeTasks":
                return GET_HASH_CODE_TASKS;
            case "getActions":
                return GET_ACTIONS;
            case "getTasks":
                return GET_TASKS;
            case "getSensors":
                return GET_SENSORS;
            case "getHashCodeSensors":
                return GET_HASH_CODE_SENSORS;
            default:
                throw new TypeRequestException(s);
        }
    }
}
