/*
* @author Guillermo Girón
* Práctica 9: ejericio 5.
* Encapsulado del monitor y sus recursos
*/

import java.util.concurrent.*;
import java.util.concurrent.ConcurrentLinkedQueue;

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
    private ConcurrentLinkedQueue<paquete> q = 
    new ConcurrentLinkedQueue<paquete>();
    private Semaphore s;

    public prodCons()
    {
        s = new Semaphore(0);
    }

    public void produce(int i) throws InterruptedException
    {
        System.out.println("Producing package " + i + "...");
        q.add(new paquete(i));
        Thread.currentThread().sleep(1000);
        s.release();
    }

    public void consume() throws InterruptedException
    {
        s.acquire();
        System.out.println("Consuming package...");
        Thread.currentThread().sleep(1000);
        paquete p = q.poll();
        System.out.println("Package " + p.getId() + " consumed");
    }
}