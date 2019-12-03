/*
* @author Guillermo Girón
* Práctica 9: ejericio 5
*/

import java.util.concurrent.*;
import java.util.ArrayDeque;

class paquete
{
    private int id;

    public paquete(int id)
    {
        this.id = id;
    }

    /**
     * @return the id
     */
    public int getId() 
    {
        return id;
    }
}

public class prodCons
{
    private ConcurrentLinkedDeQueue<paquete> q = 
    new ConcurrenteLinkedDeQueue<paquete>();
    private Semaphore s;

    public prodCons()
    {
        s = new Semaphore(0);
    }

    public void produce(int i)
    {
        q.add(new paquete(i));
        s.release();
    }

    public void consume()
    {
        s.acquire();
        q.
    }
}