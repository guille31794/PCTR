/*
    @author Guillermo Girón
    @version 1.0
    Práctica 5: Version secuencial del ejercicio 4
*/

import java.util.Random;

public class resImagen
{
    public static int imagen[][];

    public static void resize(int dim)
    {
        for (int i = 0; i != dim; ++i)
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

    public static void main(String[] args) 
    {
        int dim = Integer.parseInt(args[0]);
        Random r = new Random();
        imagen = new int[dim][dim];
        for (int i = 0; i != dim; ++i)
            for (int j = 0; j != dim; ++j)
                imagen[i][j] = r.nextInt(255);

        long endt, init = System.currentTimeMillis();
        resize(dim);
        endt = System.currentTimeMillis() - init;
        System.out.println("El tiempo ha sido de: " + endt);
    }
}