package action;

import exceptions.DeviceException;

import javax.servlet.http.HttpServletRequest;

public class DeviceUtils
{
    private static final String DEVICE_PARAM = "device";

    public static Device whatDevice(HttpServletRequest request) throws DeviceException {
        String device = request.getParameter(DEVICE_PARAM);

        return Device.whatDevice(device);
    }
}
