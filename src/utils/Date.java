package utils;

import exceptions.DateException;

public class Date
{
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
    public String toString() {
        return "Date{" +
                "dd=" + dd +
                ", mm=" + mm +
                ", yy=" + yy +
                '}';
    }

    public static Date getDateByJson(String json) throws DateException {
        String[] sp = json.split("\\.");

        try{
            byte dd = Byte.parseByte(sp[0]);
            byte mm = Byte.parseByte(sp[1]);
            short yy = Short.parseShort(sp[2]);

            return new Date(dd,mm,yy);
        }catch (Exception e)
        {
            throw new DateException(json);
        }
    }

    public static String getAsJon(Date date)
    {
        return date.getDD()+"."+date.getMM()+"."+date.getYY();
    }
}
