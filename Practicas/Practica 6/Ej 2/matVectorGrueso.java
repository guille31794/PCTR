/*
* @author Guillermo Girón García
* @version 1.0
* Class that implements matrix * vector product, using coarse-grained
* paralelism
*/

import java.util.Random;
import java.util.concurrent.*;
import java.util.Scanner;
import java.util.Arrays;

public class matVectorGrueso implements Runnable
{
  private static int[][] M;
  private static int dim;
  private static int[] v;
  private static int[] res;
  private int init, offset;

  public static int getDim()  {return dim;}

  public matVectorGrueso(int init, int offset)
  {
    this.init = init;
    this.offset = offset;
  }

  private static void printMatrix()
  {
    System.out.println("The square matrix is: ");
    for (int i = 0; i < dim; ++i)
    {
      System.out.print("[ ");
      for (int j = 0; j < dim; ++j)
      {
        System.out.print(M[i][j] + " ");
      }
      System.out.println(" ]");
    }
  }

  private static void InsertManually()
  {
    Scanner s = new Scanner(System.in);
    System.out.println("Insert dimension: ");
    dim = s.nextInt();
    System.out.println("Dimension is " + dim);
    v = new int[dim];
    M = new int[dim][dim];
    res = new int[dim];
    Arrays.fill(res, 0);
    for(int i = 0; i < dim; ++i)
    {
      System.out.println("Insert vector element " + (i+1));
      v[i] = s.nextInt();
    }
    s.close();
    System.out.println("Dimension is: " + dim);
    System.out.println("Transposed vector is: " + Arrays.toString(v));
  }

  private static void FillRandomly()
  {
    dim = 20000;//(int)(Math.random()*9999+1);
    res = new int[dim];
    Arrays.fill(res, 0);
    v = new int[dim];
    M = new int[dim][dim];
    for(int i = 0; i < dim; ++i)
      v[i] = (int)(Math.random()*9999+1);
    //System.out.println("Dimension is: " + dim);
    //System.out.println("Transposed vector is: " + Arrays.toString(v));
  }

  @Override
  public void run()
  {
    Random r = new Random(System.currentTimeMillis());

    for(int i = this.init; i < this.offset; ++i)
      for(int j = 0; j < dim; ++j)
        M[i][j] = r.nextInt(100);

    for(int i = this.init; i < this.offset; ++i)
      for(int j = 0; j < dim; ++j)
        res[i] += M[i][j] * v[j];
  }

  public static void menu()
  {
    Scanner s = new Scanner(System.in);
    int option;
    System.out.println("Menu: ");
    System.out.println("1.- Insert dimension and vector manually");
    System.out.println("2.- Fill both randomly");
    option = s.nextInt();
    switch (option)
    {
      case 1: InsertManually();
      break;
      case 2: FillRandomly();
      break;
      default: System.out.println("Error. Invalid input");
      System.exit(-1);
      break;
    }
    s.close();
  }

  public static void main(String[] args) throws Exception
  {
    menu();
    int  nTareas     = Runtime.getRuntime().availableProcessors();
    long tVentana    = dim/nTareas;
    long linf        = 0;
    long lsup        = tVentana;
    ExecutorService ept = Executors.newFixedThreadPool(nTareas);
    long inicTiempo = System.nanoTime();
    ept.execute(new matVectorGrueso((int)linf, (int)lsup));

    for(int i = 0; i < nTareas-1; ++i)
    {
      linf = lsup + 1;
      lsup += tVentana;
      ept.execute(new matVectorGrueso((int)linf, (int)lsup));
    }

    ept.shutdown();
    if(!ept.awaitTermination(2500,TimeUnit.MILLISECONDS))//while(!ept.isTerminated())  {}
      System.err.println("Threads didn't finish in 2 second!");
    double tiempoTotal = (System.nanoTime()-inicTiempo)/1.0e9;
    System.out.println("Dimension: " + getDim() + " and time: " + tiempoTotal + "s");
 }
}
