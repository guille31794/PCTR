public class hDaemon extends Thread
{
  private int id;

  public hDaemon(int id)
  {
    this.id = id;
  }

  @Override
  public void run()
  {
    if(this.isDaemon())
      System.out.println("Hi! I\'m thread " + id + " and I\'m a daemon thread");
    else
      System.out.println("Hi! I\'m thread " + id + " and I\'m a user thread");
  }

  public static void main(String[] args)
  {
    hDaemon[] t = new hDaemon[10];
    for(int i = 0; i < 10; ++i)
    {
      t[i] = new hDaemon(i);
      if(i%2==0)
        t[i].setDaemon(true);
      t[i].start();
    }
  }
}
