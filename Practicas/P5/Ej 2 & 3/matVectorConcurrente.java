/*
* @author Guillermo Girón
* Prática 3: ejercicio 2
*/

import java.util.Random;
import java.util.concurrent.*;

public class matVectorConcurrente implements Runnable
{
    public static int A[][], b[][], y[][], size = 1000;
    private int start, end;

    public matVectorConcurrente(int s, int e)
    {
        start = s;
        end = e;
    }

    @Override
    public void run() 
    {   
        for(int k = start; k < end; ++k)
            for(int i = 0; i < size; ++i)
                for(int j = 0; j < size; ++j)
                    y[k][i] += A[k][j] * b[j][i];

    }
    public static void main(String[] args) throws
    InterruptedException
    {
        Random r = new Random();
        A = new int[size][size];
        b = new int[size][size];
        y = new int[size][size];

        for(int i = 0; i < size; ++i)
            for(int j = 0; j < size; ++j)
            {
                A[i][j] = r.nextInt(10);
                b[i][j] = r.nextInt(10);
                y[i][j] = 0;
            }

        int nHilos = 2, 
        start = 0, 
        porcion = size/nHilos, 
        end = porcion;
        matVectorConcurrente h[] = new matVectorConcurrente[nHilos];
        ThreadPoolExecutor ex = 
        (ThreadPoolExecutor)Executors.newFixedThreadPool(nHilos);
        double timeSt = System.currentTimeMillis();

        for(int i = 0; i < nHilos; ++i)
        {
            h[i] = new matVectorConcurrente(start, end);
            ex.submit(h[i]);
            start = end + 1;
            end += porcion;
        }
        ex.shutdown();
        ex.awaitTermination(50, TimeUnit.DAYS);

        double timEnd = System.currentTimeMillis() - timeSt;
        System.out.println("El tiempo es: " + timEnd + "ms");
    }
}