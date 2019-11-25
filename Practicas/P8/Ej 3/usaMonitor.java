
public class usaMonitor extends Thread
{
    public static int n = 0;
    private semaforoMonitor s;

    public usaMonitor(semaforoMonitor sem)
    {
        s = sem;
    }

    @Override
    public void run() 
    {
        for(int i = 0; i < 10000; ++i)
            try 
            {
                s.Wait();
                ++n;
                s.Signal(); 
            } catch (InterruptedException e) {}
    }

    public static void main(String[] args) throws InterruptedException
    {
        semaforoMonitor s = new semaforoMonitor(1);

        Thread t1 = new usaMonitor(s);
        Thread t2 = new usaMonitor(s);
        Thread t3 = new usaMonitor(s);
        t1.start();
        t2.start();
        t3.start();
        t3.join();
        t2.join();
        t1.join();

        System.out.println(n);
    }
}