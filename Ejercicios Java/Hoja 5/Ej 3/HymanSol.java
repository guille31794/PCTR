
public class HymanSol
{
  //started turn here instead inside the loop
  static volatile int turn = 0, resource = 0;

  class A extends Thread
  {
    public void run()
    {
      do
      {
        //turn = 1;
        while(turn == 0)
        {
          ++resource;
          System.out.println(resource);
          turn = 1;
          //good practice
          Thread.yield();
        }
      } while (true);
    }
  }

  class B extends Thread
  {
    public void run()
    {
      do
      {
        //turn = 0;
        while(turn == 1)
        {
          --resource;
          System.out.println(resource);
          turn = 0;
          //good practice
          Thread.yield();
        }
      } while (true);
    }
  }

  public HymanSol()
  {
    new A().start();
    new B().start();
  }

  public static void main(String[] args)
  {
    new HymanSol();
  }
}
