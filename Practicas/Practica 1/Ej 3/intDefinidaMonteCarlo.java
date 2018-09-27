import java.util.Random;
import java.lang.Math;

public class intDefinidaMonteCarlo
{
  protected int tPoints, pUnderF;

  public intDefinidaMonteCarlo(int pNumber, int selector)
  {
    Random random = new Random(System.currentTimeMillis());
    double x, y;

    tPoints = pNumber;
    pUnderF = 0;

    for(int i = 0; i < tPoints; ++i)
    {
      x = Math.random();
      y = Math.random();

      if(y < Math.sin(x))
      {
        ++pUnderF;
      }
    }
  }

  public intDefinidaMonteCarlo(int pNumber)
  {
    Random random = new Random(System.currentTimeMillis());
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
    intDefinidaMonteCarlo N, M;
    N = new intDefinidaMonteCarlo(Integer.parseInt(args[0]), 0);
    M = new intDefinidaMonteCarlo(Integer.parseInt(args[0]));
    System.out.println("Sin function aproximation is: " + N.aprox());
    System.out.println("X function aproximation is: " + M.aprox());
  }
}
