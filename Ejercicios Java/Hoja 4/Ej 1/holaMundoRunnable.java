
public class holaMundoRunnable implements Runnable
{
  private final int id;

  public holaMundoRunnable(int id)
  {
    this.id = id;
  }

  @Override
  public void run()
  {
    System.out.println("Thread " + id + /*" with " + this.getPriority() +*/
    " says: Hello world");
  }

  public static void main(String[] args)
  {
    Runnable[] r = new Runnable[10];
    Thread[] t = new Thread[10];

    for (int i = 0; i < 10 ; ++i)
    {
      r[i] = new holaMundoRunnable(i);
      t[i] = new Thread(r[i]);
      t[i].setPriority(i+1);
    }
    for(int i = 0; i < 10; ++i)
      t[i].start();
  }
}
