package utils.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Timer
{
    public static String getTimeNowForLogs()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }
}
