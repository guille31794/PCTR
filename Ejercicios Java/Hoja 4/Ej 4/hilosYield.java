
public class hilosYield extends Thread
{
  private int id;
  private static int trial = 0;

  public hilosYield(int id)
  {
    this.id = id;
  }

  @Override
  public void run()
  {
    if(id%2==0)
    {
      Thread.yield();
      ++trial;
      System.out.println("Hi! I\'m thread " + id + " and my value is " + trial);
    }
    else
    {
      Thread.yield();
      --trial;
      System.out.println("Hi! I\'m thread " + id + " and my value is " + trial);
    }
  }

  public static void main(String[] args) throws Exception
  {
    hilosYield[] t = new hilosYield[10000];

    for(int i = 0; i < 10000; ++i)
    {
      t[i] = new hilosYield(i);
      t[i].start();
      t[i].join();
    }
  }
}
