/*Ejemplo de servidor de sockets multihilo
 *@Antonio Tomeu
 *@version 1.0
*/


import java.net.*;
import java.io.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;


public class atomicServer implements Runnable
{
    Socket enchufe;
    private static AtomicInteger counter = new AtomicInteger(0);

    public atomicServer(Socket s)
    {enchufe = s; lock = new ReentrantLock();}

    @Override
    public void run()
    {
        int visits = counter.incrementAndGet();
        System.out.println("New visit. Current number of visits: " + visits);
        try
        {
            Time.sleep(1);
            enchufe.close();
        } catch(Exception e) {System.out.println("Error...");}
    }//run

public static void main (String[] args) throws Exception
{
    int i = 0;
    int puerto = 2001;
    ThreadPoolExecutor tpe = (ThreadPoolExecutor)Executors.newCachedThreadPool();
    ServerSocket chuff = new ServerSocket (puerto, 3000);
    double iniTime = System.currentTimeMillis();
    while (i < 500)
    {
        System.out.println("Esperando solicitud de conexion...");
        Socket cable = chuff.accept();
        System.out.println("Recibida solicitud de conexion...");
        tpe.execute(new atomicServer(cable));
        ++i;
    }//while
    tpe.shutdown();
    if(!tpe.awaitTermination(3600, TimeUnit.SECONDS))
        System.out.println("Time out");
    double endTime = System.currentTimeMillis();
    System.out.println("Time: " + (endTime-iniTime)/1000 + "s");
}//main

}//Servidor_Hilos
