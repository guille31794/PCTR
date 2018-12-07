/**
* @author Guillermo Girón García
* @version 1.0
* Clase que realiza el producto de matriz por un vector sin concurrencia
*/

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class matVectorConcurrente implements Runnable
{
  private static int[][] M;
  private static int dim;
  private static int[] v;
  private static int[] res;
  private int init, offset;

  public static int getDim()  {return dim;}

  public matVectorConcurrente(int init, int offset)
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
    Random r = new Random(System.currentTimeMillis());

    for(int i = 0; i < dim; ++i)
      for(int j = 0; j < dim; ++j)
        M[i][j] = r.nextInt(100);
  }

  @Override
  public void run()
  {
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
    int nThreads = 2000, init = 0, offset = 0;
    Runnable[] core = new Runnable[nThreads];
    Thread[] t = new Thread[nThreads];

    for(int i = 0; i < nThreads; ++i)
    {
      init = offset+1;
      offset += dim/nThreads;
      core[i] = new matVectorConcurrente(init, offset);
    }

    long initTime = System.nanoTime();
    for(int i = 0; i < nThreads; ++i)
    {
      t[i] = new Thread(core[i]);
      t[i].start();
    }
    for(int i = nThreads-1; i >= 0; --i)
      t[i].join();

    double totalT = (System.nanoTime()-initTime)/10e9;
    System.out.println("Dimension: " + getDim() + " and time: " + totalT);
    //printMatrix();
    //System.out.println("The Transposed vector result is: ");
    //System.out.println(Arrays.toString(res));
  }
}
