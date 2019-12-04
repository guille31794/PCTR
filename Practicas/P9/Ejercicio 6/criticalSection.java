import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class criticalSection
{
  public static void main(String[] args) throws 
  InterruptedException
  {
	long i, n=1;
	long limite=10000000;
	Object myObject = new Object();
	long inicTiempo = System.nanoTime();

	for(i=0; i<limite; i++)
		synchronized(myObject)
		{
			n++;
		}
	
		long tiempoTotal = System.nanoTime()-inicTiempo;
	System.out.println("Synchroniced en "+tiempoTotal+" nanosegundos...");
	n=1;

	Semaphore s = new Semaphore(1);
	inicTiempo = System.nanoTime();
	for(i=0; i<limite; i++)
	{
		s.acquire();
		n++;
		s.release(); 
	}

	tiempoTotal = System.nanoTime()-inicTiempo;
	System.out.println("Semaforo en "+tiempoTotal+" nanosegundos...");
	
	ReentrantLock L = new ReentrantLock();
	inicTiempo = System.nanoTime();
	for(i=0; i<limite; i++)
	{
		L.lock();;
		n++;
		L.unlock(); 
	} 

	tiempoTotal = System.nanoTime()-inicTiempo;
	System.out.println("Cerrojo en "+tiempoTotal+" nanosegundos...");
	
	AtomicInteger a = new AtomicInteger((int)n);
	inicTiempo = System.nanoTime();
	for(i=0; i<limite; i++)
		a.incrementAndGet();

	tiempoTotal = System.nanoTime()-inicTiempo;
	System.out.println("Atomic en "+tiempoTotal+" nanosegundos...");
  }
}