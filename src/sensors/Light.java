package sensors;

import com.google.gson.JsonObject;

public class Light
{
    public static final String LIGHT_PARAM = "light";
    private int light;

    public Light(int light) {
        this.light = light;
    }

    public int getLight() {
        return light;
    }

    public JsonObject toJsonObject()
    {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(SensorExtra.SENSOR.toString(), LIGHT_PARAM);
        jsonObject.addProperty(SensorExtra.VALUE.toString(),light);

        return jsonObject;
    }
}
