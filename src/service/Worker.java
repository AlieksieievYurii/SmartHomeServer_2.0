package service;

import action.Action;
import request.post.ActionRecorder;
import request.post.FileEmployer;
import request.post.interfaises.FileWorker;
import request.post.interfaises.iWriteAction;
import task.StatusTask;
import task.Task;
import task.TaskMode;
import task.TimerJob;
import utils.files.FileReaderTasks;
import utils.files.FileWriterTasks;
import utils.files.tools.iFileWriteTasks;
import utils.files.tools.iReaderTasks;
import utils.time.Date;
import utils.time.Time;
import javax.servlet.ServletContext;
import java.util.List;

public class Worker
{
    private iReaderTasks readerTasks;
    private iWriteAction writeAction;
    private iFileWriteTasks fileWriteTasks;

    private Worker(iReaderTasks readerTasks,
                   iWriteAction writeAction,
                   iFileWriteTasks fileWriteTasks) {
        this.readerTasks = readerTasks;
        this.writeAction = writeAction;
        this.fileWriteTasks = fileWriteTasks;
    }

    void execute()
    {
        final List<Task> tasks = readerTasks.getTasks();

        for(final Task t : tasks)
            if(t.getStatusTask() == StatusTask.enable)
                typeTask(t);

       fileWriteTasks.writeTasksToFile(tasks);
    }

    private void typeTask(Task t)
    {
        switch (t.getTypeTask())
        {
            case Timer:
                timer(t);
                break;
        }
    }

    private void timer(final Task t)
    {
        final TimerJob timerJob = (TimerJob) t.getTask();

        if(timerJob.getDate() != null)
            if (!timerJob.getDate().equals(Date.getDateNow()))
                return;

            if(timerJob.getTime().equals(Time.getTimeNow())) {
                executeActions(timerJob);
                if(t.getTaskMode() == TaskMode.once)
                    t.setStatusTask(StatusTask.disable);
            }
    }

    private void executeActions(final TimerJob timerJob)
    {
        System.err.println("Execute!");
        final List<Action> actions = timerJob.getActions();

        for(final Action a : actions)
            writeAction.write(a);
    }

    static Worker build(ServletContext context)
    {
        final iReaderTasks readerTasks = new FileReaderTasks(context);
        final FileWorker fileWorker = new FileEmployer(context);
        final iWriteAction writeAction = new ActionRecorder(fileWorker);
        final iFileWriteTasks fileWriteTasks = new FileWriterTasks(context);

        return new Worker(readerTasks,writeAction,fileWriteTasks);
    }
}
