
public class intMonteCarloParalelo extends Thread
{
  protected int tPoints, pUnderF;
  private boolean id;

  public intMonteCarloParalelo(int pNumber, boolean selectorConstructor)
  {
    double x, y;

    id = selectorConstructor;

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

  public intMonteCarloParalelo(int pNumber)
  {
    double x, y;

    id = true;
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

  @Override
  public void run()
  {
    if(id)
      System.out.println("X function aproximation is: " + aprox());
    else
      System.out.println("Sin function aproximation is: " + aprox());
  }

  public static void main(String[] args)
  {
    intMonteCarloParalelo N, M;
    N = new intMonteCarloParalelo(Integer.parseInt(args[0]), false);
    M = new intMonteCarloParalelo(Integer.parseInt(args[0]));
    N.start();
    M.start();
  }
}
