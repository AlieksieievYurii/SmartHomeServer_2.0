package tests;


import controllers.tcodtask.get.interfaises.iConverter;
import device.Device;
import org.junit.Test;
import action.PortStatus;
import action.PortType;
import action.Action;
import utils.converter.ConvertTasksTCOD;
import utils.json.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class ReadFileTest
{
    @Test
    public void test()
    {

    }

    @Test
    public void test2()
    {
        List<Action> actions = new ArrayList<>();

        actions.add(new Action(Device.TCOD,PortType.DIGITAL,23,PortStatus.HIGH));
        actions.add(new Action(Device.TCOD,PortType.DIGITAL,13,PortStatus.HIGH));
        actions.add(new Action(Device.WCOD,PortType.DIGITAL,15,PortStatus.HIGH));
        actions.add(new Action(Device.TCOD,PortType.ANALOG,15,200));
        actions.add(new Action(Device.WCOD,PortType.ANALOG,11,255));

        List<Action> list = JsonUtils.selectForDevice(Device.WCOD,actions);

        for(Action a : list)
            System.out.println(a.toString());
    }
}
