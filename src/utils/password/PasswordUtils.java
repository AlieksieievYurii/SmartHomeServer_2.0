package utils.password;

import main.Manifest;

import javax.servlet.http.HttpServletRequest;

public class PasswordUtils
{
    private static final String PASSWORD_PARAM = "p";

    public static boolean isPasswordPostActionCorrect(HttpServletRequest request)
    {
        String password = request.getParameter(PASSWORD_PARAM);
        if(password == null)
            return false;
        else
            return password.equals(Manifest.PASSWORD_POST_ACTION);
    }


    public static boolean isPasswordPostTaskCorrect(HttpServletRequest request)
    {
        String password = request.getParameter(PASSWORD_PARAM);
        if(password == null)
            return false;
        else
            return password.equals(Manifest.PASSWORD_POST_TASK);
    }

    public static boolean isPasswordManagerDevicesCorrect(HttpServletRequest request)
    {
        String password = request.getParameter(PASSWORD_PARAM);
        if(password == null)
            return false;
        else
            return password.equals(Manifest.PASSWORD_MANAGER_DEVICE);
    }
}
