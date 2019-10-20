import java.sql.Time;
import java.util.concurrent.*;

class Protege extends Thread
{
    public static int n;
    private Boolean v;

    public static synchronized void incr()
    {
        ++n;
    }

    public static synchronized void dec()
    {
        --n;
    }

    @Override
    public void run() 
    {
        if(v)
            for(int i=0; i < 9999; ++i)
                incr();
        else
            for(int i = 0; i < 9999; ++i)
                dec();    
    }

    public Protege(Boolean a)
    {
        v = a;
    }

    public static void main(String[] args) throws InterruptedException
    {
        n = 0;
        Protege p1 = new Protege(true);
        Protege p2 = new Protege(false);
      
        p1.start();
        p2.start();
        p1.join();
        p2.join();
        
        System.out.println("El valor de n es: " + n);
    }
};