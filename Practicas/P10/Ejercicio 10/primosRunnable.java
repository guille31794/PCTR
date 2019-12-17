/*
* @author Guillermo Girón García
* Práctica 10: Ejercicio 1 resuelto con implementación
* de la interfaz Runnable y ejecutor de tamaño variable
*/

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class primosRunnable implements Runnable
{
    protected AtomicInteger primos = new AtomicInteger(0);
    private int start, end, encontrados;

    public primosRunnable(int s, int e)
    {
        start = s;
        end = e;
        encontrados = 0;
    }

    protected boolean esPrimo(int n)
    {
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

    public static void main(String[] args) 
    {
        int nThreads = 2,
        range = Math.pow(10.0, 8.0);

        ThreadPoolExecutor tpe = (ThreadPoolExecutor)Executors.newCachedThreadPool();
    }
}