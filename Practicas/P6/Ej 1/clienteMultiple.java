/*Ejemplo de cliente de sockets
*@Antonio Tomeu
*@version 1.0
*/

import java.util.concurrent.*;
import java.net.*;
import java.io.*;

public class clienteMultiple implements Runnable
{
    private int i;

    public clienteMultiple()
    {i = (int)(Math.random()*10);}

    @Override
    public void run()
    {
        try{
            System.out.println("Realizando conexion...");
            Socket cable = new Socket("localhost", 2001);
            System.out.println("Realizada conexion a "+cable);
            PrintWriter salida= new PrintWriter(
                                    new BufferedWriter(
                                        new OutputStreamWriter(
            cable.getOutputStream())));
            salida.println(this.i);
            salida.flush();
            System.out.println("Cerrando conexion...");
            cable.close();

            }//try
                catch (Exception e)
        {System.out.println("Error en sockets...");}
    }

    public static void main (String[] args)
    {
        int i = 0;
        ThreadPoolExecutor tpe = (ThreadPoolExecutor)Executors.newFixedThreadPool(2);
        while (i < 100)
        {
            tpe.submit(new clienteMultiple());
            try
            {
                TimeUnit.MILLISECONDS.sleep(500);
            }catch (Exception e) {}
            ++i;
        }
    }//main
}//Cliente_Hilos
