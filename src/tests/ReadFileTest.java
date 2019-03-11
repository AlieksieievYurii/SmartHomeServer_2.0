package tests;


import controllers.tcodtask.iConverter;
import org.junit.Test;
import task.PortStatus;
import task.PortType;
import task.Task;
import utils.converter.ConvertTasksTCOD;

import java.util.ArrayList;
import java.util.List;

public class ReadFileTest
{
    @Test
    public void test()
    {
        List<Task> list = new ArrayList<>();
        list.add(new Task(PortType.DIGITAL,16, PortStatus.HIGH));
        list.add(new Task(PortType.ANALOG,2, 100));
        list.add(new Task(PortType.DIGITAL,16, PortStatus.LOW));

        iConverter converter = new ConvertTasksTCOD(null);
        System.out.println(converter.convert(list));

    }
}
