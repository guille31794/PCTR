/**
* @author Guillermo Girón García
* @version 1.0
* Class that implementes a triatlon simulation with cyclic barriers
*/

import java.util.*;
import java.util.concurrent.*;

public class triatlonBarreras
{
  private List<List<Integer>> markList = Collections.synchronizedList(new ArrayList<>());
  void createBarrier()  { barrier = new CyclicBarrier(100); }
  private Runnable reUse = () -> createBarrier();
  private CyclicBarrier barrier = new CyclicBarrier(100, reUse);

  class runners implements Runnable
  {
    private List<Integer> relay = new ArrayList<>();
    private Random r = new Random();

    public void swim()
    {
      int time = r.nextInt(10000);
      relay.add(time);
      System.out.println(Thread.currentThread().getName() + " is swimming");
      try { Thread.currentThread().sleep(time); } catch(InterruptedException e) {}
      try { barrier.await(); } catch(InterruptedException ie)
      {}  catch(BrokenBarrierException e) {}
    }

    public void cycling()
    {
      int time = r.nextInt(10000);
      relay.add(time);
      System.out.println(Thread.currentThread().getName() + " is cycling");
      try { Thread.currentThread().sleep(time); } catch(InterruptedException e) {}
      try { barrier.await(); } catch(InterruptedException ie)
      {}  catch(BrokenBarrierException e) {}
    }

    public void running()
    {
      int time = r.nextInt(10000);
      relay.add(time);
      System.out.println(Thread.currentThread().getName() + " is running");
      try { Thread.currentThread().sleep(time); } catch(InterruptedException e) {}
    }

    @Override
    public void run()
    {
      swim();
      cycling();
      running();
      markList.add(relay);
      System.out.println(Thread.currentThread().getName() + " is finished");
    }
  }

  void Race()
  {
    ThreadPoolExecutor tpe = (ThreadPoolExecutor)Executors.newFixedThreadPool(100);
    for(int i = 0; i < 100; ++i)
      tpe.execute(new runners());

    tpe.shutdown();
    int acum, champion = 99999999;
    while(!tpe.isTerminated())  {}

    for(List<Integer> relays:markList)
    {
      acum = 0;
      for(Integer mark:relays)
        acum += mark;
      if(acum < champion)
        champion = acum;
    }

    System.out.println("The time needed for the champion was: " + champion);
  }

  public static void main(String[] args)
  {
    triatlonBarreras triatlon = new triatlonBarreras();
    triatlon.Race();
  }
}
