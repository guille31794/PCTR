/**
* @author Guillermo Girón García
* @version 1.0
* Clase que realiza el escalado de un vector de 10^8 componentes utilizando
* concurrencia
*/

public class escalaVectorParalelo extends Thread
{
  private static int n = 10000;
  private static double[] vector = new double[n];

  public static void escale(int x)
  {
    for(int i = 0; i < n; ++i)
      vector[i] *= x;
  }

  @Override
  public void run()
  {
    escale(3200);
  }

  public static void main(String[] args)
  {
    for(int i = 0; i < n; ++i)
    {
      vector[i] = Math.random()*4000;
    }

    escalaVectorParalelo es = new escalaVectorParalelo();
    es.start();
  }
}
