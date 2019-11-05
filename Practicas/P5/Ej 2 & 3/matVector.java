/*
* @author Guillermo Girón
* Prática 3: ejercicio 2
*/

import java.util.Random;

public class matVector
{
    public static int A[][], b[][], y[][], size = 1000;
    public static void main(String[] args) 
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

        double start = System.currentTimeMillis();
        for(int k = 0; k < size; ++k)
            for(int i = 0; i < size; ++i)
                for(int j = 0; j < size; ++j)
                    y[k][i] += A[k][j] * b[j][i];
        
        double end = System.currentTimeMillis() - start;
        System.out.println("El tiempo es: " + end + "ms");
    }
}