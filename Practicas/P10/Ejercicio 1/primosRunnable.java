/*
* @author Guillermo Girón García
* Práctica 10: Ejercicio 1 resuelto con implementación
* de la interfaz Runnable y ejecutor de tamaño variable
*/

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class primosRunnable implements Runnable
{
    protected static AtomicInteger primos = new AtomicInteger(0);
    private int start, end, encontrados;

    public primosRunnable(int s, int e)
    {
        start = s;
        end = e;
        encontrados = 0;
    }

    protected boolean esPrimo(int n)
    {
        if(n <= 1)
            return false;

        int raizN = (int)Math.sqrt(n);

        for(int i = 2; i <= raizN; ++i)
            if(n % i == 0)
                return false;

        return true;
    }
    
    @Override
    public void run() 
    {
        for(int i = start; i < end; ++i)
            if(esPrimo(i))
                ++encontrados;

        primos.addAndGet(encontrados);
    }

    public static void main(String[] args) throws 
    InterruptedException
    {
        int nThreads = 20,
        range = (int)Math.pow(10.0, 8.0),
        frame = range / nThreads,
        start = 0,
        end = frame;

        ThreadPoolExecutor tpe = (ThreadPoolExecutor)
        Executors.newCachedThreadPool();

        long tIni = System.currentTimeMillis(), t;

        for(int i = 0; i < nThreads; ++i)
        {
            tpe.submit(new primosRunnable(start, end));
            start = end;
            end += frame + 1;
        }
        
        tpe.shutdown();
        tpe.awaitTermination(2, TimeUnit.MINUTES);
        t = System.currentTimeMillis() - tIni;

        System.out.println(primos.toString() + " primos encontrados en " +
        (int)t/1000 + "s");
    }
}