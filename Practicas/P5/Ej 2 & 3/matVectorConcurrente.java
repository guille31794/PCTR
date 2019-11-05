/*
* @author Guillermo Girón
* Prática 3: ejercicio 2
*/

import java.util.Random;
import java.util.concurrent.*;

public class matVectorConcurrente implements Runnable
{
    public static int A[][], b[], y[];
    private int start, end;

    public matVectorConcurrente(int s, int e)
    {
        start = s;
        end = e;
    }

    @Override
    public void run() 
    {
        for(int i = start; i < end; ++i)
            for(int j = 0; j < 10000; ++j)
                y[i] += A[i][j]* b[j];

    }
    public static void main(String[] args) throws
    InterruptedException
    {
        Random r = new Random();
        A = new int[10000][10000];
        b = new int[10000];
        y = new int[10000];

        for(int i = 0; i < 10000; ++i)
        {
            b[i] = r.nextInt(10);
            y[i] = 0;
            for(int j = 0; j < 10000; ++j)
                A[i][j] = r.nextInt(10);
        }

        int nHilos = 16, start = 0, porcion = 10000/nHilos, 
        end = porcion;
        matVectorConcurrente h[] = new matVectorConcurrente[nHilos];
        Thread t[] = new Thread[nHilos];
        double timeSt = System.currentTimeMillis();

        for(int i = 0; i < nHilos; ++i)
        {
            h[i] = new matVectorConcurrente(start, end);
            start = end + 1;
            end += porcion;
            t[i] = new Thread(h[i]);
            t[i].start();
        }

        for(int i = 0; i < nHilos; ++i)
            t[i].join();
        
        double timEnd = System.currentTimeMillis() - timeSt;
        System.out.println("El tiempo es: " + timEnd + "ms");
    }
}