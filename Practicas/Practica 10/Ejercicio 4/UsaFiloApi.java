/**
* @author Guillermo Girón García
* @version 1.0
* Class that implements an example of philosophers problem with five instances
* of philosophers class
*/

import java.util.concurrent.*;

public class UsaFiloApi
{
  public static void main(String[] args)
  {
    filoApiAN monitor = new filoApiAN();
    ThreadPoolExecutor tpe = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);
    for(int i = 0; i < 5; ++i)
      tpe.execute(new philosopher(monitor, i));
    tpe.shutdown();
  }
}

class philosopher implements Runnable
{
  private filoApiAN monitor;
  private int id;

  public philosopher(filoApiAN monitor, int id)
  {this.monitor = monitor; this.id = id;  }

  public void eat()
  {
    System.out.println("Philosopher " + (id + 1) + " is eating...");
    try{  TimeUnit.MILLISECONDS.sleep(1000);  } catch(Exception e){}
  }

  public void think()
  {
    System.out.println("Philosopher " + (id + 1) + " is thinking...");
    try{  TimeUnit.MILLISECONDS.sleep(750);  } catch(Exception e){}
  }

  @Override
  public void run()
  {
    do
    {
      think();
      monitor.takeFork(id);
      eat();
      monitor.releaseFork(id);
    } while(true);
  }
}
