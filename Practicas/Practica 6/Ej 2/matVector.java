/**
* @author Guillermo Girón García
* @version 1.0
* Clase que realiza el producto de matriz por un vector sin concurrencia
*/

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class matVector
{
  private static int[][] M;
  private static int dim;
  private static int[] v;
  private static int[] res;

  public static int getDim()  {return dim;}

  private static void FillRandomly()
  {
    dim = 20000;//(int)(Math.random()*9999+1);
    v = new int[dim];
    M = new int[dim][dim];
    for(int i = 0; i < dim; ++i)
      v[i] = (int)(Math.random()*9999+1);

    res = new int[dim];
    Arrays.fill(res, 0);

    for(int i = 0; i < dim; ++i)
      for(int j = 0; j < dim; ++j)
        M[i][j] = (int)(Math.random()*9999+1);
  }

  private static void prod()
  {
    for(int i = 0; i < dim; ++i)
      for(int j = 0; j < dim; ++j)
        res[i] += M[i][j] * v[j];
  }


  public static void main(String[] args)
  {
    FillRandomly();
    long init = System.nanoTime();
    prod();
    double totalT = (System.nanoTime()-init)/10e9;
    System.out.println("Dimension: " + getDim() + " and time: " + totalT + "s");
  }
}
