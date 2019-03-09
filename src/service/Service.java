package service;

public class Service implements Runnable
{
    private Thread thread;

    public Service(){
        thread = new Thread(this);
    }

    public void startService()
    {
        thread.start();
    }

    @Override
    public void run() {
        System.err.println("Service is started!");
    }
}
