package task;

import com.google.gson.JsonObject;
import exceptions.*;
import utils.json.JsonUtils;

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

    public int getId() {
        return id;
    }

    public TypeTask getTypeTask() {
        return typeTask;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public StatusTask getStatusTask() {
        return statusTask;
    }

    public TaskMode getTaskMode() {
        return taskMode;
    }

    public Object getTask() {
        return task;
    }

    public void setStatusTask(StatusTask statusTask) {
        this.statusTask = statusTask;
    }

    public boolean equals(Task task) {
        return this.id == task.getId();
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

    public static Task getTaskByJson(JsonObject jsonObject) throws TaskException {


        int id;
        final TypeTask typeTask;
        final StatusTask statusTask;
        final TaskMode taskMode;
        final Object task;
        final String name;
        final String description;
        try
        {
            id = jsonObject.get(API_EXTRA_ID).getAsInt();
            typeTask = TypeTask.getTypeTasksByName(jsonObject.get(API_EXTRA_TYPE_TASK).getAsString());
            name = jsonObject.get(API_EXTRA_NAME).getAsString();
            description = jsonObject.get(API_EXTRA_DESCRIPTION).getAsString();
            statusTask = StatusTask.getStatusTasksByName(jsonObject.get(API_EXTRA_STATUS).getAsString());
            taskMode = TaskMode.getTaskModeByName(jsonObject.get(API_EXTRA_MODE).getAsString());
            task = getJob(typeTask, jsonObject.get(API_EXTRA_JOB).getAsJsonObject());


        }catch (NullPointerException | ActionException | DateException | StatusTaskException | TimerException | TaskModeException | TypeTaskException e) {
            throw new TaskException(e.getMessage());
        }
        return new Task(id, typeTask, name, description, statusTask, taskMode, task);
    }

    public static JsonObject toJsonObject(Task task) {
        final JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty(API_EXTRA_ID, task.getId());
        jsonObject.addProperty(API_EXTRA_TYPE_TASK, task.getTypeTask().getInJson());
        jsonObject.addProperty(API_EXTRA_NAME, task.getName());
        jsonObject.addProperty(API_EXTRA_DESCRIPTION, task.getDescription());
        jsonObject.addProperty(API_EXTRA_STATUS, task.getStatusTask().getInJson());
        jsonObject.addProperty(API_EXTRA_MODE, task.getTaskMode().getInJson());
        jsonObject.add(API_EXTRA_JOB, JsonUtils.getJobJson(task));

        return jsonObject;
    }

    private static Object getJob(TypeTask typeTask, JsonObject job) throws ActionException, DateException, TimerException {
        switch (typeTask) {
            case Timer:
                return TimerJob.getTimerJobByJson(job);
            default:
                return null;
        }
    }
}
