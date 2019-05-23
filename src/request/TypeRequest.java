package request;

import exceptions.TypeRequestException;

public enum TypeRequest {

    //--------ListenerTasks[POST]------------
    POST_ACTION("postAction"),
    POST_TASK("postTask"),
    POST_REGISTERED_PIN("postRegisteredPin"),
    DELETE_REMOVE_TASK("removeTask"),
    EDIT_REGISTERED_PIN("editRegisteredPin"),
    //--------ListenerTasks[GET]------------
    GET_ACTIONS("getActions"),
    GET_HASH_CODE_ACTIONS("getHashCodeActions"),
    GET_TASKS("getTasks"),
    GET_HASH_CODE_TASKS("getHashCodeTasks"),
    GET_FREE_TASK_ID("getFreeTaskId"),
    GET_SENSORS("getSensors"),
    GET_HASH_CODE_SENSORS("getHashCodeSensors"),
    GET_REGISTERED_PINS("getRegisteredPins");

    private String typeRequest;

    TypeRequest(String typeRequest) {
        this.typeRequest = typeRequest;
    }


    public static TypeRequest whatTypeRequest(String s) throws TypeRequestException {
        switch (s) {
            case "postAction":
                return POST_ACTION;
            case "postTask":
                return POST_TASK;
            case "removeTask":
                return DELETE_REMOVE_TASK;
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
            case "getFreeTaskId":
                return GET_FREE_TASK_ID;
            case "getRegisteredPins":
                return GET_REGISTERED_PINS;
            case "postRegisteredPin":
                return POST_REGISTERED_PIN;
            case "editRegisteredPin":
                return EDIT_REGISTERED_PIN;
            default:
                throw new TypeRequestException(s);
        }
    }
}
