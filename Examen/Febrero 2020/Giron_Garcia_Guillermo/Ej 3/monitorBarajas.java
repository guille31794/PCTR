import java.util.concurrent.locks.*;

public class monitorBarajas
{
    private boolean[] pA = new boolean[2];
    private boolean[] pD = new boolean[2];
    private ReentrantLock L = new ReentrantLock();
    private Condition wait_landing = L.newCondition(), 
    wait_flying = L.newCondition(); 

    public monitorBarajas()
    {
        for(int i = 0; i < 2; ++i)
        {
            pA[i] = true;
            pD[i] = true;
        }
    }

    public void aterrizar(int pista) throws
    InterruptedException
    {
        L.lock();

        while(!pA[pista])
            wait_landing.await();
        
        System.out.println("Avion " + Thread.currentThread().getId() + " aterrizando");
        pA[pista] = false;

        L.unlock();

        Thread.sleep(1000);

        L.lock();

        pA[pista] = true;
        System.out.println("Avion " + Thread.currentThread().getId() + " libera pista");

        wait_landing.signalAll();
        L.unlock();
    }

    public void despegar(int pista) throws
    InterruptedException
    {
        L.lock();

        while(!pD[pista])
            wait_flying.await();
        
        System.out.println("Avion " + Thread.currentThread().getId() + " despegando");
        pD[pista] = false;

        L.unlock();

        Thread.sleep(1000);

        L.lock();

        pD[pista] = true;
        System.out.println("Avion " + Thread.currentThread().getId() + " libera pista");

        wait_flying.signalAll();
        L.unlock();
    }
}