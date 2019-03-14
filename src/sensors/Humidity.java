package sensors;

import com.google.gson.JsonObject;

public class Humidity
{
    public static final String HUMIDITY_PARAM = "humidity";
    private int humidity;

    public Humidity(int humidity) {
        this.humidity = humidity;
    }

    public int getHumidity() {
        return humidity;
    }

    public JsonObject toJsonObject()
    {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(SensorExtra.SENSOR.toString(), HUMIDITY_PARAM);
        jsonObject.addProperty(SensorExtra.VALUE.toString(),humidity);

        return jsonObject;
    }
}
