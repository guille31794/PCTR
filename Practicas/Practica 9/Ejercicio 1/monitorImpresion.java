/*
* @author Guillermo Girón García
* @version 1.0
* Class that implements a monitor to control printers
*/

public class monitorImpresion
{
  private static int N = 3;
  private static boolean[] free = new boolean[N];
  // No needed, but code easier to read
  private static int printers = N;

  public void init()
  {
    for(int i = 0; i < N; ++i)
      free[i] = true;
  }

  public synchronized int usePrinter() throws Exception
  {
    while(printers == 0 /*!free[0] && !free[1] && !free[2]*/) //No needed case
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
    notify();
  }
}
