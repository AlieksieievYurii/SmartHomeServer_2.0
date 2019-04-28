package utils.time;

import exceptions.DateException;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date
{
    private static final String NONE = "none";
    private byte dd;
    private byte mm;
    private short yy;

    public Date(byte dd, byte mm, short yy) {
        this.dd = dd;
        this.mm = mm;
        this.yy = yy;
    }

    public byte getDD() {
        return dd;
    }

    public byte getMM() {
        return mm;
    }

    public short getYY() {
        return yy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return dd == date.dd &&
                mm == date.mm &&
                yy == date.yy;
    }

    @Override
    public String toString() {
        return "Date{" +
                "dd=" + dd +
                ", mm=" + mm +
                ", yy=" + yy +
                '}';
    }

    public static Date getDateByJson(String json) throws DateException {

        if (json.equals(NONE))
            return null;

        String[] sp = json.split("\\.");

        try {
            byte dd = Byte.parseByte(sp[0]);
            byte mm = Byte.parseByte(sp[1]);
            short yy = Short.parseShort(sp[2]);

            return new Date(dd, mm, yy);
        } catch (Exception e) {
            throw new DateException(json);
        }
    }

    public static String getAsJon(Date date) {
        if (date == null)
            return NONE;
        else
            return date.getDD() + "." + date.getMM() + "." + date.getYY();
    }

    public static Date getDateNow() {
        final String time = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
        try {
            return getDateByJson(time);
        } catch (DateException e) {
            e.printStackTrace();
            return null;
        }
    }
}
