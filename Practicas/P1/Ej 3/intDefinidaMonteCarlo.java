/**
* @author Guillermo Girón Garcíab
* @version 1.0
* Clase que calcula la integral definida de monte Carlo a partir de un argumento
* introducido por consola
*/

import java.lang.Math;
import java.util.Random;

public class intDefinidaMonteCarlo
{
  protected int tPoints, pUnderF;

  public intDefinidaMonteCarlo(int pNumber, int selectorConstructor)
  {
    double x, y;
    Random r = new Random(1);
    tPoints = pNumber;
    pUnderF = 0;

    for(int i = 0; i < tPoints; ++i)
    {
      x = r.nextDouble();
      y = r.nextDouble();

      if(y < Math.sin(x))
      {
        ++pUnderF;
      }
    }
  }

  public intDefinidaMonteCarlo(int pNumber)
  {
    double x, y;

    tPoints = pNumber;
    pUnderF = 0;

    for(int i = 0; i < tPoints; ++i)
    {
      x = Math.random();
      y = Math.random();

      if(y < x)
      {
        ++pUnderF;
      }
    }
  }

  public double aprox() {return (double)pUnderF/tPoints;}

  public static void main(String[] args)
  {
    intDefinidaMonteCarlo N;
    double endTime, initTime = System.currentTimeMillis();
    N = new intDefinidaMonteCarlo(Integer.parseInt(args[0]), 0);
    /*M = new intDefinidaMonteCarlo(Integer.parseInt(args[0]));*/
    System.out.println("Sin function aproximation is: " + N.aprox());
    endTime = System.currentTimeMillis();
    System.out.println("Time: " + (endTime-initTime) + "s");
    /*System.out.println("X function aproximation is: " + M.aprox());*/
  }
}
