/*
* @author Guillermo Girón García
* @version 1.0
* Class that implements a viking drakkar tripulation trying to eat
*/

import java.util.concurrent.*;

class Viking implements Runnable
{
  private boolean work;
  private drakkarVikingo drak;

  public Viking(boolean work, drakkarVikingo drak)
  { this.work = work; this.drak = drak;}

  public boolean getWork()  { return work;}
  
  @Override
  public void run()
  {
    try
    {
      if (getWork())
        while(true)
          drak.eat();
      else
        while(true)
          drak.refill();
    } catch(Exception e)  {}
  }
}

public class drakkarVikingo
{
  protected static final int refill = 20;
  public static int dishes = refill;

  public synchronized void eat() throws Exception
  {
    while(dishes == 0)
    {
      notifyAll();
      wait();
    }
    System.out.println(Thread.currentThread().getName() + " is eating..");
    --dishes;
    {
    Thread.currentThread().sleep(1000);
    System.out.println("Rations left: " + dishes);
  }

  public synchronized void refill() throws Exception
  {
    while (dishes != 0)
    {
      wait();
    }
    System.out.println("Refilling pot..");
    dishes = refill;
    Thread.currentThread().sleep(1000);
    System.out.println("Meal's ready!");
    notifyAll();
  }

  public static void main(String[] args) throws Exception
  {
    int i = 0, crew = 5;
    drakkarVikingo boat = new drakkarVikingo();
    ThreadPoolExecutor tpe =
    (ThreadPoolExecutor)Executors.newFixedThreadPool(crew+1);
    tpe.execute(new Viking(false, boat)); // Cooker
    while(i < crew)
    {
      tpe.execute(new Viking(true, boat)); // Sailors
      ++i;
    }
    tpe.shutdown();
    if(!tpe.awaitTermination(30,TimeUnit.SECONDS))
      System.err.println("Captain says: \"Meal's over, lets work again!\"");
  }
}
