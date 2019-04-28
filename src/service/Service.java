package service;

import javax.servlet.ServletContext;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Service implements Runnable
{
    private ScheduledExecutorService exec;
    private Worker worker;

    public Service(ServletContext context){
        exec = Executors.newSingleThreadScheduledExecutor();
        worker = Worker.build(context);
    }

    public void startService()
    {
        exec.scheduleAtFixedRate(this,0,1, TimeUnit.MINUTES);
    }

    @Override
    public void run() {
        System.err.println("Service is started!");
        worker.execute();
    }
}
