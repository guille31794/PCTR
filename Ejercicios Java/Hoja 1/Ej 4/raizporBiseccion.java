/**
* @author Guillermo Girón García
* @version 1.0
* Clase que calcula la raiz por biseccion de las funciones cos(x)−x3 en [0,1] y
* x2 −5 en [2,3].
*/

public class raizporBiseccion
{
  private static int iter;
  private static double upperLimit, lowerLimit, root;

  static double error(double x)
  {
    return (x * x) - 5;
  }

  public static void main(String[] args)
  {
    iter = Integer.parseInt(args[0]);
    upperLimit = 0.9;
    lowerLimit = 0.4;

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
}
