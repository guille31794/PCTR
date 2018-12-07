/*Ejemplo de servidor de sockets multihilo
 *@Antonio Tomeu
 *@version 1.0
*/


import java.net.*;
import java.io.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.*;

public class ServidorHilosinPool implements Runnable
{
    Socket enchufe;
    public ServidorHilosinPool(Socket s)
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
    int i = 0, max = 500;
    int puerto = 2001;
    ServerSocket chuff = new ServerSocket (puerto, 3000);
    Thread t[] = new Thread[max];
    double iniTime = System.currentTimeMillis();
    while (i < max)
    {
        System.out.println("Esperando solicitud de conexion...");
        Socket cable = chuff.accept();
        System.out.println("Recibida solicitud de conexion...");
        t[i] = new Thread(new ServidorHiloconPool(cable));
        t[i].start();
        ++i;
    }
    --i;
    while(i >= 0)
    {
        t[i].join();
        --i;
    }
    double endTime = System.currentTimeMillis();
    System.out.println("Time: " + (endTime-iniTime)/1000 + "s");
}//main

}//Servidor_Hilos
