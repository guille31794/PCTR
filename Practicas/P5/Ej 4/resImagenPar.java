/*
*   @author Guillermo Girón
*   @version 1.0 
*   Práctica 5: Version secuencial del ejercicio 4
*/

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
//import java.util.java.util.concurrent.*;

public class resImagenPar implements Runnable
{
    private int start, end;
    public static int imagen[][], dim;

    public resImagenPar(int s, int e)
    {
        start = s;
        end = e;
    }

    @Override
    public void run() 
    {
        for (int i = start; i != end; ++i)
            for (int j = 0; j != dim; ++j) 
            {
                int x1, x2, x3, x4;

                if (i + 1 == dim)
                    x1 = 0;
                else
                    x1 = imagen[i + 1][j];

                if (j + 1 == dim)
                    x2 = 0;
                else
                    x2 = imagen[i][j + 1];

                if (i - 1 < 0)
                    x3 = 0;
                else
                    x3 = imagen[i - 1][j];

                if (j - 1 < 0)
                    x4 = 0;
                else
                    x4 = imagen[i][j - 1];

                imagen[i][j] = (4 * imagen[i][j] - x1 - x2 - x3 - x4) / 8;

                if (imagen[i][j] < 0)
                    imagen[i][j] = 0;
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        dim = Integer.parseInt(args[0]);
        Random r = new Random();
        imagen = new int[dim][dim];
        for (int i = 0; i != dim; ++i)
            for (int j = 0; j != dim; ++j)
                imagen[i][j] = r.nextInt(255);
        
        int nThreads = 2, s = 0, frame = dim/nThreads, e = frame;
        ThreadPoolExecutor ex = 
        (ThreadPoolExecutor)Executors.newFixedThreadPool(nThreads);
        long endt, init = System.currentTimeMillis();

        for(int i = 0; i < nThreads; ++i)
        {
            ex.submit(new resImagenPar(s, e));
            s = e + 1;
            e += frame;
        }

        ex.shutdown();
        ex.awaitTermination(10, TimeUnit.SECONDS);
        endt = System.currentTimeMillis() - init;
        System.out.println("Ha tardado: " + endt + " ms");
    }
}