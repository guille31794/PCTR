
public class tareaHilo extends Thread
{
  static int cont = 0;

  @Override
  public void run()
  {
    int nIter = 1000000;
    for(int i = 0; i < nIter; ++i)
      cont++;
  }

  public static void main(String[] args) throws Exception
  {
    int N = 2000;
    tareaHilo[] t = new tareaHilo[10000];
    long init, endT, diff;
    for(int i = 0; i < N; ++i)
      t[i] = new tareaHilo();
    /*Start co-work */
    init = System.currentTimeMillis();
    for(int i = 0; i < N; ++i)
      t[i].start();
    for(int i = 0; i < N; ++i)
      t[i].join();
    endT = System.currentTimeMillis();
    diff = (endT - init); // if needed /10000;
    System.out.println("Execution time was: " + diff + " and counter value: " +
    cont);
  }
}
