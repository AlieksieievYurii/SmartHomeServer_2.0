package tests;


import controllers.tcodtask.get.interfaises.iConverter;
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
        List<Action> list = new ArrayList<>();
        list.add(new Action(PortType.DIGITAL,16, PortStatus.HIGH));
        list.add(new Action(PortType.ANALOG,2, 100));
        list.add(new Action(PortType.DIGITAL,16, PortStatus.LOW));

        iConverter converter = new ConvertTasksTCOD(null);
        System.out.println(converter.convert(list));

    }

    @Test
    public void test2()
    {
        List<Action> actions = new ArrayList<>();

        actions.add(new Action(PortType.DIGITAL,23,PortStatus.HIGH));
        actions.add(new Action(PortType.DIGITAL,13,PortStatus.HIGH));
        actions.add(new Action(PortType.DIGITAL,15,PortStatus.HIGH));
        actions.add(new Action(PortType.ANALOG,15,200));
        actions.add(new Action(PortType.ANALOG,11,255));

        System.out.println(JsonUtils.toJsonArray(actions).toString());
    }
}
