/**
 * @(#)tareaRunnable.java
 * @author Guillermo Girón
 * @version 1.00 
 * Practice 2: Exercise 2
 */

public class tareaRunnable
  implements Runnable
{   
  public static int n = 0;
    private boolean op;

    public tareaRunnable(Boolean op)
    {this.op = op;}

    public void inc(){++n;}
    public void dec(){--n;}

    public void run()
    {
      if(op)
        for(int i=0; i<10000; i++) inc();
      else
        for(int i=0; i<10000; i++) dec();
    }
}
