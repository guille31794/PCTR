/**
 * @author Guillermo Girón García
 * @version 1.0
 * Class that implements a secuential version of 
 * Monte Carlo method for pi calculation
 */

import java.util.Random;

 public class piSecuencial
 {
    public static double monteCarlo(int points)
    {
        Random ran = new Random();
        double x, y, r = 5.0;
        int pInside = 0;
        
        for(int i = 0; i < points; ++i)
        {
            x = ran.nextDouble();
            y = ran.nextDouble();

            if(x*x + y*y <= r*r)
                ++pInside;
        }

        double pi = 4 * (pInside/points);

        return pi;
    }

     public static void main(String[] args) 
     {
         double pi;
         for(int i = 0; i < 9999999; i+=10000)
         {
            pi = monteCarlo(i);
            System.out.println("Pi -> " + pi);
         }
     }
 }