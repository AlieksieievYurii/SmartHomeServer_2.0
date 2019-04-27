package service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Service implements Runnable
{
    private ScheduledExecutorService exec;

    public Service(){
        exec = Executors.newSingleThreadScheduledExecutor();
    }

    public void startService()
    {
        exec.scheduleAtFixedRate(this,0,1, TimeUnit.MINUTES);
    }

    @Override
    public void run() {
        System.err.println("Service is started!");
    }
}
