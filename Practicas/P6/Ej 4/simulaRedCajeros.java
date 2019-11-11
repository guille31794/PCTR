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
    public abstract void ingresar(double i);
}

public class simulaRedCajeros implements Runnable
{
    private cuentaCorrienteSegura c;
    private Random r;

    public simulaRedCajeros(cuentaCorrienteSegura cc)
    {
        c = cc;
        r = new Random(System.currentTimeMillis());
    }
    
    @Override
    public void run() 
    {
        int i = 0;
        do
        {
            switch(r.nextInt(2))
            {
                case 0: sacar s =
                (int r) -> {c.reintegro(r);};
                s.reintegro(r.nextInt(1000));
                break;
                case 1: meter m = 
                (double i) -> {c.ingreso(i);};
                m.ingresar(r.nextDouble(1000.0));
                break;
                default:
                break;
            }

            ++i;
        }
        while(i < 10000);
    }

    public static void main(String[] args) 
    {
        cuentaCorrienteSegura c = new cuentaCorrienteSegura(7500.00);
        int nThreads = 4;
        ThreadPoolExecutor ex =
        (ThreadPoolExecutor)Executors.newFixedThreadPool(nThreads);

        for(int i = 0; i < nThreads; ++i)
        {
            ex.submit(new simulaRedCajeros());
        }

        ex.shutdown();
        ex.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("El saldo final es: " + c.saldo());
    }
}