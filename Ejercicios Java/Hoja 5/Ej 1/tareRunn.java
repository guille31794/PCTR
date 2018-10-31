import java.util.concurrent.*;

public class tareRunn implements Runnable
{
  static int cont = 0;
  @Override
  public void run()
  {
    int nIter = 100000;
    for(int i = 0; i < nIter; ++i)
      cont++;
  }

  public static void main(String[] args)
  {
    int N = 500000;
    long init, endt, diff;
    /* Thread pool */
    ExecutorService executor = Executors.newCachedThreadPool();
    init = System.currentTimeMillis();
    for(int i = 0; i < N; ++i)
      /* smart execution */
      executor.execute(new tareRunn());
    /* Important to close the executor when done */
    executor.shutdown();
    while(!executor.isTerminated()) {}
    endt = System.currentTimeMillis();
    diff = endt - init;
    System.out.println("Execution time was: " + diff + " and counter value: " +
    cont);
  }
}
