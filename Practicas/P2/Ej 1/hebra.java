/** 
* @author Guillermop Girón
* @version 1.0
* Practice 2: Exercise 1
*/

public class hebra extends Thread
{
    public static int n;
    private boolean inc;

    hebra(boolean b)
    {
        inc = b;
    }

    public void incremento()
    {
        ++n;
    }

    public void decremento()
    {
        --n;
    }

    @Override
    public void run() 
    {
        int i = 0;
        if(inc)
            while(i < 10000)
            {
                incremento();
                ++i;
            }
        else
            while(i < 10000)
            {
                decremento();
                ++i;
            }
    }
};