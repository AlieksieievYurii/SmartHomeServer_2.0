package utils.password;

import javax.servlet.http.HttpServletRequest;

public class PasswordUtils
{
    private static final String PASSWORD_PARAM = "p";
    private static final String PASSWORD_REQUEST = "123456789";

    private static boolean passwordIsCorrect(String passwordRequest)
    {
        return passwordRequest.equals(PASSWORD_REQUEST);
    }

    public static boolean passwordIsCorrect(HttpServletRequest request) {
        String password = request.getParameter(PASSWORD_PARAM);
        if(password == null)
            return false;

        return passwordIsCorrect(password);
    }
}
