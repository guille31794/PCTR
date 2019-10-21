/*
* @author Guillermo Girón
* Prática 3: ejercicio 2
*/

import java.util.Random;

public class matVector
{
    public static int A[][], b[], y[];
    public static void main(String[] args) 
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
        double start = System.currentTimeMillis();
        for(int i = 0; i < 10000; ++i)
            for(int j = 0; j < 10000; ++j)
                y[i] += A[i][j]* b[j];
        
        double end = System.currentTimeMillis() - start;
        System.out.println("El tiempo es: " + end + "ms");
    }
}