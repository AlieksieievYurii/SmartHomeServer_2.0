package utils.time;

import exceptions.TimerException;

public class Time
{
    private byte hh;
    private byte mm;

    public Time(byte hh, byte mm) {
        this.hh = hh;
        this.mm = mm;
    }

    public byte getHH() {
        return hh;
    }

    public byte getMM() {
        return mm;
    }

    @Override
    public String toString() {
        return "Time{" +
                "hh=" + hh +
                ", mm=" + mm +
                '}';
    }

    public static Time getTimeByJson(String json) throws TimerException {
        try {
            String[] sp = json.split(":");
            byte hh = Byte.parseByte(sp[0]);
            byte mm = Byte.parseByte(sp[1]);

            return new Time(hh,mm);
        }catch (Exception e)
        {
            throw new TimerException(json);
        }
    }

    public static String getTimeAsJSon(Time time)
    {
        return time.getHH()+":"+time.getMM();
    }
}
