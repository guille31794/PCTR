/**
* @author Guillermo Girón García
* @version 1.0
* Class that implements Eisseber-McGuire algorithm for 2 processes.
*/

public enum State { IDLE, WAITING, RUNNING}

public class algEisenbergMcGuire implements Runnable
{
  public volatile static int turn = 0;
  public volatile static int resource = 0;
  private volatile State[] s = new State[3];

  public algEisenbergMcGuire()
  {
    s[0] = State.IDLE;
    s[1] = State.WAITING;
    s[2] = State.RUNNING;
  }

  @Override
  public void run()
  {

  }

  public static void main(String[] args)
  {
    
  }
}
