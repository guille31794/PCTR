/*Ejemplo de servidor de sockets multihilo
 *@Antonio Tomeu
 *@version 1.0
*/


import java.net.*;
import java.io.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;


public class reentrantServer implements Runnable
{
    Socket enchufe;
    private static int counter = 0;
    private ReentrantLock lock;

    public reentrantServer(Socket s)
    {enchufe = s; lock = new ReentrantLock();}

    @Override
    public void run()
    {
        lock.lock();
        ++counter;
        System.out.println("New visit. Current number of visits: " + counter);
        try
        {
            Time.sleep(1);
            enchufe.close();
        } catch(Exception e) {System.out.println("Error...");}
        lock.unlock();
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
        tpe.execute(new reentrantServer(cable));
        ++i;
    }//while
    tpe.shutdown();
    if(!tpe.awaitTermination(3600, TimeUnit.SECONDS))
        System.out.println("Time out");
    double endTime = System.currentTimeMillis();
    System.out.println("Time: " + (endTime-iniTime)/1000 + "s");
}//main

}//Servidor_Hilos
