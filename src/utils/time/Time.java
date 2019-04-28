package utils.time;

import exceptions.TimerException;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return hh == time.hh &&
                mm == time.mm;
    }


    @Override
    public String toString() {
        return "Time{" +
                "hh=" + hh +
                ", mm=" + mm +
                '}';
    }

    public static Time getTimeByJson(String json) throws TimerException {

        if(json.equals("none"))
            return null;

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

    public static Time getTimeNow()
    {
        final String time = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        try {
            return getTimeByJson(time);
        } catch (TimerException e) {
            e.printStackTrace();
            return null;
        }
    }
}
