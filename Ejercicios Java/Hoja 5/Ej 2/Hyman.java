/*
* The proposed solution seems to be incorrect, due to the issue that if a
* process take the turn and stop, may be other process jump the loop having it's
* turn and provoking a double access to the critic section of the program
*/

public class Hyman
{
  static volatile int turn = 1, c1 = 1, c2 = 1, resource = 0;

  class A extends Thread
  {
    public void run()
    {
      c1 = 0;
      while(turn != 1)
        while(c2 == 0)
          Thread.yield();
      turn = 0;
      ++resource;
    }
  }

  class B extends Thread
  {
    public void run()
    {
      c2 = 0;
      while(turn != 0)
        while(c1 == 0)
          Thread.yield();
      turn = 1;
      --resource;
    }
  }

  public Hyman()
  {
    new A().start();
    new B().start();
  }

  public static void main(String[] args)
  {
    for(int i = 0; i < 500; ++i)
      new Hyman();

    Thread.currentThread().yield();

    System.out.println("Resource value is: " + resource);
  }
}
