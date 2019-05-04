package service;

import main.Manifest;

import javax.servlet.ServletContext;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Service implements Runnable
{
    private ScheduledExecutorService exec;
    private Worker worker;
    private ScheduledFuture scheduledFuture;

    public Service(ServletContext context){
        exec = Executors.newSingleThreadScheduledExecutor();
        worker = Worker.build(context);
    }

    public void startService()
    {
        scheduledFuture = exec.scheduleAtFixedRate(this,0, Manifest.TIME_WORKER, TimeUnit.SECONDS);
    }

    @Override
    public void run() {
        worker.execute();
    }

    public void cancel()
    {
        if(scheduledFuture != null) {
            scheduledFuture.cancel(false);
            scheduledFuture = null;
            exec = null;
        }
    }
}
