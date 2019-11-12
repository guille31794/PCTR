/*
* @author Guillermo Girón García
* @version 1.0 
* Práctica 6: Ejercicio 4
*/

import java.util.concurrent.*;
import java.util.Random;

interface sacar
{
    public abstract void reintegro(int r);
}

interface meter
{
    public abstract void ingresar(double in);
}

public class simulaRedCajeros implements Runnable
{
    private cuentaCorrienteSegura c;
    private Random r;
    private int option;

    public simulaRedCajeros(cuentaCorrienteSegura cc, int o)
    {
        c = cc;
        r = new Random(System.currentTimeMillis());
        option = o;
    }
    
    @Override
    public void run() 
    {
        int i = 0;
        do
        {
            switch(option)//r.nextInt(2))
            {
                case 0: sacar s =
                (int r) -> {c.reintegro(r);};
                s.reintegro(1000);//r.nextInt(1000));
                break;
                case 1: meter m = 
                (double in) -> {c.ingreso(in);};
                m.ingresar(1000.0);//r.nextDouble()*1000.0);
                break;
                default:
                break;
            }

            ++i;
        }
        while(i < 10000);
    }

    public static void main(String[] args) throws InterruptedException
    {
        cuentaCorrienteSegura c = new cuentaCorrienteSegura(7500.00);
        int nThreads = 2;
        ThreadPoolExecutor ex =
        (ThreadPoolExecutor)Executors.newFixedThreadPool(nThreads);

        for(int i = 0; i < nThreads; ++i)
        {
            ex.submit(new simulaRedCajeros(c,i));
        }

        ex.shutdown();
        ex.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("El saldo final es: " + c.saldo());
    }
}