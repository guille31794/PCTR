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

  private static void printMatrix()
  {
    System.out.println("The square matrix is: ");
    for (int i = 0; i < dim; ++i)
    {
      for (int j = 0; j < dim; ++j)
      {
        System.out.print(M[i][j] + " ");
      }
      System.out.println();
    }
  }

  private static void InsertManually()
  {
    Scanner s = new Scanner(System.in);
    System.out.println("Insert dimension: ");
    dim = s.nextInt();
    v = new int[dim];
    M = new int[dim][dim];
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
    dim = (int)(Math.random()*99+1);
    v = new int[dim];
    M = new int[dim][dim];
    for(int i = 0; i < dim; ++i)
      v[i] = (int)(Math.random()*99+1);
    System.out.println("Dimension is: " + dim);
    System.out.println("Transposed vector is: " + Arrays.toString(v));
  }

  private static void prod()
  {
    Random r = new Random();
    res = new int[dim];
    Arrays.fill(res, 0);

    for(int i = 0; i < dim; ++i)
      for(int j = 0; j < dim; ++j)
        M[i][j] = r.nextInt();

    for(int i = 0; i < dim; ++i)
      for(int j = 0; j < dim; ++j)
        res[i] += M[i][j] * v[j];

    printMatrix();
    System.out.println("The Transposed vector result is: ");
    System.out.println(Arrays.toString(res));
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

  public static void main(String[] args)
  {
    menu();
    prod();
  }
}
