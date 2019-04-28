package tests;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import exceptions.*;
import org.junit.Test;
import service.Worker;
import task.Task;
import task.TimerJob;
import utils.time.Date;
import utils.time.Time;

import java.util.Objects;

public class TasksTask
{
    public class TaskTimer
    {
        private String name;
        private int num;

        public TaskTimer(String name, int num) {
            this.name = name;
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public int getNum() {
            return num;
        }

        @Override
        public String toString() {
            return "TaskTimer{" +
                    "name='" + name + '\'' +
                    ", num=" + num +
                    '}';
        }
    }

    @Test
    public void test()
    {
        Object o  = new TaskTimer("lol",12);

        TaskTimer k = (TaskTimer) o;

        System.out.println(k.toString());

        System.out.println(Integer.parseInt("04"));
    }

    @Test
    public void test_time_data()
    {
        String js = "{\n" +
                "  \"data\":\"27.04.2019\",\n" +
                "  \"time\": \"12:00\",\n" +
                "  \"actions\":[\n" +
                "    {\n" +
                "      \"port_id\": 24,\n" +
                "      \"port_type\": \"digital\",\n" +
                "      \"port_status\": \"HIGH\",\n" +
                "      \"for_device\": \"tcod\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"port_id\": 14,\n" +
                "      \"port_type\": \"analog\",\n" +
                "      \"port_value\": 255,\n" +
                "      \"for_device\": \"tcod\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        JsonObject jsonObject = new JsonParser().parse(js).getAsJsonObject();

        try {
            TimerJob task = TimerJob.getTimerJobByJson(jsonObject);
            System.out.println(task.toString());
        }  catch ( ActionException | DateException | TimerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_task()
    {
        String t = "{\n" +
                "  \"id\": 1111,\n" +
                "  \"type_task\":\"timer\",\n" +
                "  \"name\":\"task_one\",\n" +
                "  \"description\": \"just test\",\n" +
                "  \"status\": \"enable\",\n" +
                "  \"mode\": \"always\",\n" +
                "  \"job\": {\n" +
                "    \"data\":\"27.4.2019\",\n" +
                "    \"time\": \"12:0\",\n" +
                "    \"actions\":[\n" +
                "      {\n" +
                "        \"port_id\": 24,\n" +
                "        \"port_type\": \"digital\",\n" +
                "        \"port_status\": \"HIGH\",\n" +
                "        \"for_device\": \"tcod\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"port_id\": 14,\n" +
                "        \"port_type\": \"analog\",\n" +
                "        \"port_value\": 255,\n" +
                "        \"for_device\": \"tcod\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";

        JsonObject jsonObject = new JsonParser().parse(t).getAsJsonObject();
        final Task task;
        try {
            task = Task.getTaskByJson(jsonObject);
            System.out.println(task.toString());
            System.out.println(Task.toJsonObject(task).equals(jsonObject));
            System.out.println(Task.toJsonObject(task).toString());
        } catch (TaskException e) {
            e.printStackTrace();
        }
    }
}
