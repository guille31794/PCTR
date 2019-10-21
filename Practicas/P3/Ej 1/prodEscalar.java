/*
* @author Guillermo Girón
* Práactica 3: Ejercicio 1
*/

import java.util.Random;

public class prodEscalar
{
    public static double v1[];
    public static double v2[];

    public static void main(String[] args) 
    {
        //Inicialización
        v1 = new double[1000000];
        v2 = new double[1000000];
        Random r = new Random();
        double resultado = 0;

        for(int i = 0; i < 1000000; ++i)
        {
            v1[i] = r.nextDouble()*100;
            v2[i] = r.nextDouble()*100; 
        }

        //operacion
        double timeStart = System.currentTimeMillis();

        for(int i = 0; i < 1000000; ++i)
        {
            resultado += v1[i] * v2[i];
        }

        double timeEnd = System.currentTimeMillis() - timeStart;

        System.out.println("El resultado es: " + resultado
        + " y el tiempo empleado: " + timeEnd);
    }
}