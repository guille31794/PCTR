
public class prioridadMain implements Runnable
{
  private int id;

  public prioridadMain(int id)
  {
    this.id = id;
  }

  @Override
  public void run()
  {
    System.out.println("Hi, I\'m thread " + id);
  }

  public static void main(String[] args)
  {
    Runnable[] r = new Runnable[9];
    Thread[] t = new Thread[10];

    Thread.currentThread().setPriority(10);

    for(int i = 0; i < 9; ++i)
    {
      r[i] = new prioridadMain(i);
      t[i] = new Thread(r[i]);
      t[i].start();
    }

  }
}
