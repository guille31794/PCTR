/*
* @author Guillermo Girón
* Práctica 9: ejericio 5.
* Clase que hace uso del monitor
*/

import java.util.concurrent.*;

class Producer extends Thread
{
    private int id;
    private prodCons monitor;

    public Producer(int i, prodCons m)
    {
        id = i;
        monitor = m;
    }

    /**
     * @return the id
     */
    public long getId() 
    {
        return id;
    }

    @Override
    public void run() 
    {
        int i = 1;
        while(true)
        {
            try
            {
                monitor.produce(i);
            } catch(InterruptedException e){}
            ++i;
        }
    }
}

class Consumer extends Thread
{
    private int id;
    private prodCons monitor;

    public Consumer(int i, prodCons m)
    {
        id = i;
        monitor = m;
    }

    /**
     * @return the id
     */
    public long getId() 
    {
        return id;
    }

    @Override
    public void run() 
    {
        while(true)
            try
            {
                monitor.consume();
            } catch(InterruptedException e){}
    }
}

/**
 * llamada
 */
public class llamada 
{
    public static void main(String[] args) throws InterruptedException
    {
        prodCons m = new prodCons();
        
        Thread prod = new Producer(1, m),
        cons = new Consumer(1, m);

        prod.start();
        cons.start();
    }
}