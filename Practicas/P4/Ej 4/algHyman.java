import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
* @author Guillermo Girón 
* @version 1.0
* Practica 4: ejercicio 4
*/

interface incLambda
{
    public void inc(int recurso);
}

interface decLambda
{
    public void dec(int recurso);
}

public class algHyman 
{
    private volatile static boolean c0, c1;
    private volatile static int turno = 1, recurso;
    protected int it = 10000;

    public algHyman() throws InterruptedException
    {
        ExecutorService ex = Executors.newFixedThreadPool(2);
        ex.submit(new P1());
        ex.submit(new P2());
        ex.shutdown();
        ex.awaitTermination(10, TimeUnit.SECONDS);
    }
    
    public class P1 implements Runnable
    {
        public P1(){}

        @Override
        public void run() 
        {
            int i = 0;
            while(i < it)
            {
                c0 = true;
                while (turno != 0) 
                {
                    while (c1 == true)
                        //Thread.yield();
                        continue;

                    turno = 0;
                    incLambda inc = 
                    (int n) -> 
                    {++n;};
                    inc.inc(recurso);
                    System.out.println("P1 resource value: " + recurso);
                    c0 = false;
                }
                ++i;
            }       
        }
    }

    public class P2 implements Runnable
    {
        public P2(){}

        @Override
        public void run() 
        {
            int i = 0;
            while(i < it)
            {
                c1 = true;
                while (turno != 1) 
                {
                    while (c0 == true)
                        //Thread.yield();
                        continue;

                    turno = 1;
                    decLambda dec = 
                    (int n) -> 
                    {++n;};
                    dec.dec(recurso);
                    System.out.println("P2 resource value: " + recurso);
                    c1 = false;
                }
                ++i;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        new algHyman();
    }
}