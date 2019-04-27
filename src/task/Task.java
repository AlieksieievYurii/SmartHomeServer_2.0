package task;

import com.google.gson.JsonObject;
import exceptions.*;

public class Task {
    private static final String API_EXTRA_ID = "id";
    private static final String API_EXTRA_TYPE_TASK = "type_task";
    private static final String API_EXTRA_NAME = "name";
    private static final String API_EXTRA_DESCRIPTION = "description";
    private static final String API_EXTRA_STATUS = "status";
    private static final String API_EXTRA_MODE = "mode";
    private static final String API_EXTRA_JOB = "job";

    private int id;
    private TypeTask typeTask;
    private String name;
    private String description;
    private StatusTask statusTask;
    private TaskMode taskMode;
    private Object task;

    private Task(int id,
                TypeTask typeTask,
                String name,
                String description,
                StatusTask statusTask,
                TaskMode taskMode,
                Object task) {
        this.id = id;
        this.typeTask = typeTask;
        this.name = name;
        this.description = description;
        this.statusTask = statusTask;
        this.taskMode = taskMode;
        this.task = task;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", typeTask=" + typeTask +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", statusTask=" + statusTask +
                ", taskMode=" + taskMode +
                ", task=" + task +
                '}';
    }

    public static Task getTaskByJson(JsonObject jsonObject) throws
            TypeTaskException,
            StatusTaskException,
            TaskModeException,
            DateException,
            TimerException,
            ActionException {
        int id = jsonObject.get(API_EXTRA_ID).getAsInt();
        TypeTask typeTask =
                TypeTask.getTypeTasksByName(jsonObject.get(API_EXTRA_TYPE_TASK).getAsString());
        String name = jsonObject.get(API_EXTRA_NAME).getAsString();
        String description = jsonObject.get(API_EXTRA_DESCRIPTION).getAsString();
        StatusTask statusTask =
                StatusTask.getStatusTasksByName(jsonObject.get(API_EXTRA_STATUS).getAsString());
        TaskMode taskMode = TaskMode.getTaskModeByName(jsonObject.get(API_EXTRA_MODE).getAsString());

        Object task = getJob(typeTask,jsonObject.get(API_EXTRA_JOB).getAsJsonObject());

        return new Task(id,typeTask,name,description,statusTask,taskMode,task);
    }

    private static Object getJob(TypeTask typeTask, JsonObject job) throws ActionException, DateException, TimerException {
        switch (typeTask)
        {
            case Timer:
                return TimerJob.getTimerJobByJson(job);
                default:
                    return null;
        }
    }
}
