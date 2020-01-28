import java.util.concurrent.*;

public class aeropuerto
{
    public static void main(String[] args)
    throws InterruptedException 
    {
        ThreadPoolExecutor terminal =
        (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        monitorBarajas aeropuerto = new monitorBarajas();

        for(int i = 0; i < 10; ++i)
        {
            if(i < 5)
                terminal.execute(new aeronaves(false, aeropuerto));
            else
                terminal.execute(new aeronaves(true, aeropuerto));
        }

        terminal.shutdown();
        terminal.awaitTermination(30, TimeUnit.SECONDS);
    }
}