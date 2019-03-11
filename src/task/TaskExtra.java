package task;

public enum TaskExtra
{
    TYPE_PORT("type_port"),
    PORT("port"),
    STATUS_PORT("status_port"),
    SIGNAL_ON_PORT("signal_on_port");

    private String jsonExtra;

    TaskExtra(String jsonExtra) {
        this.jsonExtra = jsonExtra;
    }

    public String getJsonExtra() {
        return jsonExtra;
    }
}
