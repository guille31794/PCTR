/*
* @author Guillermo Girón García
* Practice 2: Exercise 6
*/

interface Increment
{
    public void inc();
}

interface Decrement
{
    public void dec();
}

public class lambda extends Thread
{
    private boolean b;
    public static int n;

    public lambda(boolean bo)
    {b = bo;}

    @Override
    public void run() 
    {
        if(b)
        {
            Increment in = () -> { ++n;};
            for(int i = 0; i < 10000; ++i)
                in.inc();
        }
        else
        {
            Decrement d = () -> {--n;};
            for(int i = 0; i < 10000; ++i)
                d.dec();
        }
    }

    public static void main(String[] args) throws InterruptedException
    {   
        n = 0;
        lambda l1 = new lambda(true),
        l2 = new lambda(false);
        l1.start();
        l2.start();
        l2.join();
        l1.join();
        System.out.println(n);    
    }
}