/**
* @author Guillermo Girón García
* @version 1.0
* Clase que realiza el escalado de un vector de 10^8 componentes utilizando
* concurrencia
*/

public class escalaVectorParalelo extends Thread
{
  private static int n = 10000000;
  private static double[] vector = new double[n];
  private final int init, offset;
  private double v[] = new double[n/8];

  public escalaVectorParalelo(int i, int o) {init = i; offset = o;}

  public void escale()
  {
    int j = 0;
    for(int i = this.init; i < this.offset; ++i)
    {
        v[j] = vector[init];
        ++j;
    }
  }

  @Override
  public void run()
  {
    escale();
  }

  public static void main(String[] args)
  {
    int init = 0, offset = 0;

    for(int i = 0; i < n; ++i)
    {
      vector[i] = Math.random()*10000000;
    }

    escalaVectorParalelo e[] = new escalaVectorParalelo[8];
    for(int i = 0; i < 8; ++i)
    {
      init = offset;
      offset += n/8;
      e[i] = new escalaVectorParalelo(init, offset);
      e[i].start();
    }
  }
}
