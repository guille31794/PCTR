/*Ejemplo de servidor de sockets multihilo
 *@Antonio Tomeu
 *@version 1.0
*/


import java.net.*;
import java.io.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.*;

public class ServidorHiloconPool implements Runnable
{
    Socket enchufe;
    public ServidorHiloconPool(Socket s)
    {enchufe = s;}

    @Override
    public void run()
    {
    try
    {
        BufferedReader entrada = new BufferedReader(
                                    new InputStreamReader(
                                        enchufe.getInputStream()));
        String datos = entrada.readLine();
        int j;
        int i = Integer.valueOf(datos).intValue();
        for(j=1; j<=20; j++)
        {
            System.out.println("El hilo "+Thread.currentThread().getName()+" escribiendo el dato "+i);
            TimeUnit.SECONDS.sleep(1);
        }
        enchufe.close();
        System.out.println("El hilo "+Thread.currentThread().getName()+"cierra su conexion...");
    } catch(Exception e) {System.out.println("Error...");}
    }//run

public static void main (String[] args) throws Exception
{
    int i = 0;
    int puerto = 2001;
    ThreadPoolExecutor tpe = (ThreadPoolExecutor)Executors.newCachedThreadPool();
    ServerSocket chuff = new ServerSocket (puerto, 3000);
    double iniTime = System.currentTimeMillis();
    while (i < 100)
    {
        System.out.println("Esperando solicitud de conexion...");
        Socket cable = chuff.accept();
        System.out.println("Recibida solicitud de conexion...");
        tpe.execute(new ServidorHiloconPool(cable));
        ++i;
    }//while
    tpe.shutdown();
    if(!tpe.awaitTermination(3600, TimeUnit.SECONDS))
        System.out.println("Time out");
    double endTime = System.currentTimeMillis();
    System.out.println("Time: " + (endTime-iniTime)/1000 + "s");
}//main

}//Servidor_Hilos
