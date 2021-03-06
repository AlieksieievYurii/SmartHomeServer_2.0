package components.action;

import exceptions.PortStatusException;

public enum PortStatus
{
    HIGH("HIGH"),LOW("LOW");

    private String jsonExtra;

    PortStatus(String jsonExtra) {
        this.jsonExtra = jsonExtra;
    }

    public String getJsonExtra() {
        return jsonExtra;
    }

    public String getShortJsonExtra()
    {
        if(jsonExtra.equals("HIGH"))
            return "H";
        else if(jsonExtra.equals("LOW"))
            return "L";
        else return null;
    }

    public static PortStatus getByName(String s) throws PortStatusException {
        if(s == null)
            return null;

        if(s.equals("HIGH"))
            return PortStatus.HIGH;
        else if(s.equals("LOW"))
            return PortStatus.LOW;
        else
            throw new PortStatusException(s);
    }
}
