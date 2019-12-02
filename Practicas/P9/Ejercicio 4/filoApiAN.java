/**
* @author Guillermo Girón García
* @version 1.0
* Class that implements a monitor using high level api to solve the
* philosophers problem
*/

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class filoApiAN
{
  private static int[] forks = {2, 2, 2, 2, 2};
  private ReentrantLock lock = new ReentrantLock();
  private Condition[] condition = new Condition[5];

  public filoApiAN()
  {
    for(int i = 0; i < 5; ++i)
      condition[i] = lock.newCondition();
  }

  void takeFork(int id)
  {
    lock.lock();
    while(forks[(id + 1 ) % 5] < 2)
      try{  condition[id].await(); }
      catch(Exception e)  {}
    --forks[id];
    --forks[(id + 1) % 5];
    System.out.println("Philosopher " + (id + 1) + " is ready to eat");
    lock.unlock();
  }

  void releaseFork(int id)
  {
    lock.lock();
    ++forks[id];
    ++forks[(id + 1) % 5];
    System.out.println("Philosopher " + (id + 1) + " has eaten");
    for(int i = 0; i < 5; ++i)
      condition[i].signalAll();
    lock.unlock();
  }
}
