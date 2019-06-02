package components.action;

public enum ActionAPI {
    FOR_DEVICE("for_device"),
    PIN_TYPE("pin_type"),
    PIN_ID("pin_id"),
    PIN_STATUS("pin_status"),
    PIN_VALUE("pin_value");

    private String jsonExtra;

    ActionAPI(String jsonExtra) {
        this.jsonExtra = jsonExtra;
    }

    public String getJsonExtra() {
        return jsonExtra;
    }

    public static ActionAPI getPostExtra(String jsonExtra) {
        for (ActionAPI a : ActionAPI.class.getEnumConstants())
            if (a.jsonExtra.equals(jsonExtra))
                return a;
        return null;
    }
}
