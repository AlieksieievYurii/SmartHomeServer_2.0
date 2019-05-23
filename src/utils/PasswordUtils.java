package utils;

import main.Manifest;

import javax.servlet.http.HttpServletRequest;

public class PasswordUtils
{
    private static final String PASSWORD_PARAM = "p";

    public static boolean isApiPasswordCorrect(HttpServletRequest request)
    {
        String password = request.getParameter(PASSWORD_PARAM);
        if(password == null)
            return false;
        else
            return password.equals(Manifest.API_PASSWORD);
    }
}
