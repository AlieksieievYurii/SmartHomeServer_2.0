package tests;

import controllers.tcodtask.iConverter;
import org.junit.Test;
import task.PortType;
import task.Task;
import utils.converter.ConvertTasksTCOD;
import utils.files.FileReader;
import utils.hash.HashCode;
import utils.json.JsonUtils;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class ReadFileTest
{
    @Test
    public void test()
    {
        FileReader fileReader = new FileReader("out/artifacts/SmartHomeServer_2_0_war_exploded/WEB-INF/res/TestFile.json");
        List<Task> tasks = fileReader.read();

        for(Task t : tasks)
            System.out.println(t.toString());
    }

    @Test
    public void testTwo()
    {
        String json = "[{\n" +
                "  \"type_port\":\"digital\",\n" +
                "  \"port\":24,\n" +
                "  \"status_port\":\"low\"\n" +
                "}]";

        System.out.println(JsonUtils.convertTasksToJson(json).get(0).getAsJsonObject().get("type_port").getAsString());
    }

    @Test
    public void testThree()
    {
       String jsonTest = "[{\n" +
               "  \"type_port\":\"digital\",\n" +
               "  \"port\":24,\n" +
               "  \"status_port\":1\n" +
               "},{\n" +
               "  \"type_port\":\"analog\",\n" +
               "  \"port\":14,\n" +
               "  \"status_port\":126\n" +
               "},\n" +
               "  {\n" +
               "    \"type_port\":\"digital\",\n" +
               "    \"port\":24,\n" +
               "    \"status_port\":32\n" +
               "  },{\n" +
               "  \"type_port\":\"analog\",\n" +
               "  \"port\":55,\n" +
               "  \"status_port\":255\n" +
               "}\n" +
               "]";

      List<Task> list =  JsonUtils.convertTaskToList(jsonTest);

      for(Task t : list)
          System.out.println(t.toString());
    }
    @Test
    public void testTaskJson()
    {
        Task task = new Task(PortType.ANALOG,24,255);
        System.out.println(task.toJsonObject().toString());
    }

    @Test
    public void testConverter()
    {
        String jsonTest = "[{\n" +
                "  \"type_port\":\"digital\",\n" +
                "  \"port\":24,\n" +
                "  \"status_port\":1\n" +
                "},{\n" +
                "  \"type_port\":\"analog\",\n" +
                "  \"port\":14,\n" +
                "  \"status_port\":126\n" +
                "},\n" +
                "  {\n" +
                "    \"type_port\":\"digital\",\n" +
                "    \"port\":24,\n" +
                "    \"status_port\":32\n" +
                "  },{\n" +
                "  \"type_port\":\"analog\",\n" +
                "  \"port\":55,\n" +
                "  \"status_port\":255\n" +
                "}\n" +
                "]";

        List<Task> list =  JsonUtils.convertTaskToList(jsonTest);
        iConverter iConverter = new ConvertTasksTCOD(null);
        System.out.println(iConverter.convert(list));
    }

    @Test
    public void testSize()
    {
        String text = "2rfergreughirehf[eorijf[reihgoregregreg";
        String text2 = "2rfergreughirehf[eorojf[reihgoregregreg";

        assertNotSame(HashCode.hash(text),HashCode.hash(text2));
    }
}
