/**
* @author Guillermo Girón García
* @version 1.0
* Class that implements Eisseber-McGuire algorithm for 2 processes.
*/

enum State { IDLE, WAITING, RUNNING}

public class algEisenbergMcGuire implements Runnable
{
  public volatile static int turn = 0, index = 0, it = 200000;
  public volatile static int resource = 0;
  private volatile static State[] flag = new State[3];
  private int flagPos, id;

  public algEisenbergMcGuire(int id)
  {
    this.id = id;
    flagPos = 0;
  }

  @Override
  public void run()
  {
    while(it>0)
    {
      do
      {
        /* Announce we want the resource */
        flag[flagPos] = State.WAITING;


        /* Scan all processes beginning from the one which hast the turn */
        /* Repeat until find all IDLING processes */
        index = turn;
        while(index != flagPos)
        {
          if(flag[index] != State.IDLE)
          {
            index = turn;
            flagPos = (flagPos +1 ) % 2;
          }
          else
            index = (index+1)%2;
        }

        /* Claim the resources temporaly */
        flag[flagPos] = State.RUNNING;

        /* Find first running proces beside us, if exist */
        index = 0;
        while((index < 2) && (index == flagPos) || (flag[turn] == State.IDLE))
          ++index;
        /*
        *  If there aren't any other running processes and we have turn, or
        *  if all others are idling, proceed. Other way, repeat
        */
      } while((index >= 2) && (turn == flagPos) || (flag[turn] == State.IDLE));

      /* Critic section start */

      /* Claim turn and proceed */
      turn = flagPos;

      /* Critic section code. Resource should be 0 */
      ++resource; --resource; --it;
      System.out.println("Resource in CS: " + resource + " in iteration " + it +
      " thread(" + this.id + ")" );


      /* End of critic section */
      /* Find another process not idling. If there aren't any, we'll find ourselves*/
      index = (turn+1)%2;
      while(flag[index] == State.IDLE)
        index = (index+1)%2;

      /* Give the turn or maintain it */
      turn = index;

      /* We're done */
      flag[flagPos] = State.IDLE;
  }
  }

  public static void main(String[] args)
  {
    new Thread(new algEisenbergMcGuire(1)).start();
    new Thread(new algEisenbergMcGuire(2)).start();
  }
}
