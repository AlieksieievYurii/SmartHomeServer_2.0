package controllers.errors;

import utils.time.Timer;

public class ErrorLogs
{
    public static void errorOfTypeRequest()
    {
        System.err.println(Timer.getTimeNowForLogs()+" :: Wrong type request!");
    }

}
