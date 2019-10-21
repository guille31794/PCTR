/*
* @author Guillermo Girón
* Práctica 3: Ejercicio 1
*/

import java.util.Random;

public class prodEscalarParalelo extends Thread
{
    public static double v1[], v2[], 
    productoParcial[];

    private int id, start, end;

    public prodEscalarParalelo(int i, int s, int e)
    {
        id = i;
        start = s;
        end = e;
    }

    @Override
    public void run() 
    {
        for(int i = start; i < end; ++i)
        {
            productoParcial[id] += v1[i] * v2[i]; 
        }
    }

    public static void main(String[] args) throws 
    InterruptedException
    {
        //Inicialización
        v1 = new double[1000000];
        v2 = new double[1000000];
        Random r = new Random();
        double resultado = 0;
        int nHilos = 10, start = 0, porcion = 1000000/nHilos,
        end = porcion;

        productoParcial = new double[10];
        
        for(int i = 0; i < 10; ++i)
            productoParcial[i] = 0.0;

        for(int i = 0; i < 1000000; ++i)
        {
            v1[i] = r.nextDouble();
            v2[i] = r.nextDouble(); 
        }

        prodEscalarParalelo h[] = new prodEscalarParalelo[10];
        
        //Operación
        int repeticiones = 100000, in = 0;
        double timeEnd = 0, timeStart = System.currentTimeMillis();

        //Esquema de medida indirecto
        //do
        //{
            for(int i = 0; i < 10; ++i)
            {
                h[i] = 
                new prodEscalarParalelo(i, start, end);
                start = end + 1;
                end += porcion;
                h[i].start();
            }
            
            for(int i = 0; i < 10; ++i)
                h[i].join();

            for(double i:productoParcial)
                resultado += i;

            timeEnd += System.currentTimeMillis() - timeStart;
            ++in;

        //} while(in < repeticiones);

        System.out.println("El resultado es: " + resultado 
        + " y el tiempo necesario es: " + timeEnd/repeticiones);
    }
}