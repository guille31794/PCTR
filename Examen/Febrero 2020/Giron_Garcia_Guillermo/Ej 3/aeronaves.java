import java.util.Random;

public class aeronaves implements Runnable
{
    private boolean flying;
    private monitorBarajas t;

    public aeronaves(boolean state, monitorBarajas a)
    {
        flying = state;
        t = a;
    }

    @Override
    public void run() 
    {
        Random r = new Random();

        while(true)
        {
            if(flying)
            {
                try
                {
                    t.aterrizar(r.nextInt(2));
                } 
                catch(InterruptedException e){}
                flying = false;
            }
            else
            {
                try
                {
                    t.despegar(r.nextInt(2));
                } 
                catch(InterruptedException e){}
                flying = true;
            }
        }
    }
}