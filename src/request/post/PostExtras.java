package request.post;

public enum PostExtras {
    PORT_TYPE("port_type"),
    PORT_ID("port_id"),
    PORT_STATUS("port_status"),
    PORT_VALUE("port_value");

    private String jsonExtra;

    PostExtras(String jsonExtra) {
        this.jsonExtra = jsonExtra;
    }

    public String getJsonExtra() {
        return jsonExtra;
    }

    public static PostExtras getPostExtra(String jsonExtra) {
        switch (jsonExtra) {
            case "port_type":
                return PORT_TYPE;
            case "port_id":
                return PORT_ID;
            case "port_status":
                return PORT_STATUS;
            case "port_value":
                return PORT_VALUE;
            default:
                return null;
        }
    }
}
