import java.util.Random;

public class ParImparRunnable implements Runnable
{
  private boolean par;
  public static int it;

  ParImparRunnable(boolean par)
  {
    this.par = par;
  }

  @Override
  public void run()
  {
    Random r = new Random(System.currentTimeMillis());

    for (int i = 0; i < it; ++i)
    {
      int number = r.nextInt();
      if(par)
      {
        while (!(number%2==0))
        {
          number = r.nextInt();
        }
        System.out.println(number);
      }
      else
      {
        while (number%2==0)
        {
          number = r.nextInt();
        }
        System.out.println(number);
      }
      try
      {
        if(i%10 == 9)
        {
          System.out.println("Waiting...");
          Thread.sleep(1000);
        }
      } catch (InterruptedException ex)
      {
			Thread.currentThread().interrupt();
		  }
    }
  }

  public static void main(String[] args) throws Exception
  {
    Runnable[] r = new Runnable[2];
    Thread[] t = new Thread[2];
    it = 500;

    r[0] = new ParImparRunnable(true);
    r[1] = new ParImparRunnable(false);
    t[0] = new Thread(r[0]);
    t[1] = new Thread(r[1]);
    t[0].start();
    t[1].start();
  }
}
