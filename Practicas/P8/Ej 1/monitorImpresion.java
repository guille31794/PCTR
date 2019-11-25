/*
* @author Guillermo Girón García
* @version 1.0
* Class that implements a monitor to control printers
*/

public class monitorImpresion
{
  private static int N = 3;
  private static boolean[] free = new boolean[N];
  private static int printers = N;

  public void init()
  {
    for(int i = 0; i < N; ++i)
      free[i] = true;
  }

  public synchronized int usePrinter() throws InterruptedException
  {
    while(printers == 0)
      wait();

    int available = 0;
    while(!free[available] && available < N)
      ++available;

    free[available] = false;
    --printers;

    return available;
  }

  public synchronized void freePrinter(int toFree)
  {
    free[toFree] = true;
    ++printers;
    notifyAll();
  }
}
