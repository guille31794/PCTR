/**
* @author Guillermo Girón García
* @version 1.0
* Clase que calcula la raiz por biseccion de las funciones cos(x)−x3 en [0,1] y
* x2 −5 en [2,3].
*
* Work scheme:
* {[c, b] f(a)*f(b) > 0
* {[a, c] f(a)*f(c) < 0
* {c f(a)*f(c) = e
* c = valor medio entre los limites superior e inferior
*/

import java.util.Scanner;

public class raizporBiseccion
{
  private static int iter;
  private static double upperLimit, lowerLimit, root, error;

  static double error(double x)
  {
    return (x * x) - 5;
  }

  public static void polin()
  {
    for(int i = 0; i < iter; ++i)
    {
      root = (upperLimit + lowerLimit) /2;
      if(error(root) > 0)
      {
        System.out.println("La raiz es " + root);
      } else if (error(upperLimit)  * error(root) > 0)
      {
          upperLimit = root;
      } else  { lowerLimit = root;  }
    }

    System.out.println("La raiz es aproximadamente: " +
    ((upperLimit + lowerLimit)/2));
  }

  public static void cos()
  {

  }

  public static void main(String[] args)
  {
    Scanner sc = new Scanner(System.in);
    iter = Integer.parseInt(args[0]);
    System.out.print("Insert upper limit: ");
    upperLimit = Double.parseDouble(sc.nextLine());
    System.out.print("Insert lower limit: ");
    lowerLimit = Double.parseDouble(sc.nextLine());

    polin();
  }
}
