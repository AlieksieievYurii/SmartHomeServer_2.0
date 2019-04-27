package task;

import action.Action;
import com.google.gson.JsonObject;
import exceptions.*;
import utils.time.Date;
import utils.time.Time;
import utils.json.JsonUtils;

import java.util.List;

public class TimerJob
{
    private static final String API_EXTRA_TIMER_JOB_DATA = "data";
    private static final String API_EXTRA_TIMER_JOB_TIME = "time";
    private static final String API_EXTRA_TIMER_JOB_ACTIONS = "actions";

    private Date date;//Can be null, it means every day
    private Time time;
    private List<Action> actions;

    private TimerJob(Date date, Time time, List<Action> actions) {
        this.date = date;
        this.time = time;
        this.actions = actions;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public List<Action> getActions() {
        return actions;
    }

    @Override
    public String toString() {
        return "TimerJob{" +
                "date=" + date +
                ", time=" + time +
                ", actions=" + actions +
                '}';
    }

    public static TimerJob getTimerJobByJson(JsonObject jsonObject) throws DateException, TimerException, ActionException {
        Date date = Date.getDateByJson(jsonObject.get(API_EXTRA_TIMER_JOB_DATA).getAsString());
        Time time = Time.getTimeByJson(jsonObject.get(API_EXTRA_TIMER_JOB_TIME).getAsString());
        List<Action> actions = JsonUtils.toListApiActions(jsonObject.getAsJsonArray(API_EXTRA_TIMER_JOB_ACTIONS));

        return new TimerJob(date,time,actions);
    }

    public static JsonObject getTimerJobJsonObject(TimerJob timerJob)
    {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(API_EXTRA_TIMER_JOB_DATA,Date.getAsJon(timerJob.getDate()));
        jsonObject.addProperty(API_EXTRA_TIMER_JOB_TIME,Time.getTimeAsJSon(timerJob.getTime()));
        jsonObject.add(API_EXTRA_TIMER_JOB_ACTIONS,JsonUtils.toJsonArrayApi(timerJob.getActions()));

        return jsonObject;
    }
}
