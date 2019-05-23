package components.sensors;

public enum SensorExtra
{
    SENSOR("sensor"),VALUE("value");

    private String sensor;

    SensorExtra(String sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return sensor;
    }
}
