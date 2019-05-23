package components.sensors;

import com.google.gson.JsonObject;

public class Temperature
{
    public static final String TEMPERATURE_PARAM = "temperature";
    private int temperature;

    public Temperature(int temperature) {
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }

    public JsonObject toJsonObject()
    {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(SensorExtra.SENSOR.toString(),TEMPERATURE_PARAM);
        jsonObject.addProperty(SensorExtra.VALUE.toString(),temperature);
        return jsonObject;
    }
}
