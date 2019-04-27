package action;

public enum ActionExtra
{
    /*

        Action extras are fro Action which is written in file: Actions.json for parsing

     */


    FOR_DEVICE("for_device"),
    TYPE_PORT("type_port"),
    PORT("port"),
    STATUS_PORT("status_port"),
    SIGNAL_ON_PORT("signal_on_port");

    private String jsonExtra;

    ActionExtra(String jsonExtra) {
        this.jsonExtra = jsonExtra;
    }

    public String getJsonExtra() {
        return jsonExtra;
    }
}
