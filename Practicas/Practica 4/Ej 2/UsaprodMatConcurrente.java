/**
* @author Guillermo Girón García
* @version 1.0
* Clase que realiza el producto de matriz por un vector sin concurrencia
*/

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;


public class UsaprodMatConcurrente
{
  public static void main(String[] args) throws Exception
  {
    int nThreads = 2, init = 0, offset = 0;
    prodMatConcurrente[] t = new prodMatConcurrente[nThreads];
    prodMatConcurrente.menu();
    for(int i = 0; i < nThreads; ++i)
    {
      init = offset;
      offset += prodMatConcurrente.dim/nThreads;
      t[i] = new prodMatConcurrente(init, offset);
      t[i].start();
      t[i].join();
    }

    prodMatConcurrente.printMatrix();
    System.out.println("The Transposed vector result is: ");
    System.out.println(Arrays.toString(prodMatConcurrente.res));
  }
}
