import java.util.concurrent.*;

public class criticalSection
{
  public static void main(String[] args) throws 
  InterruptedException
  {
	long i, n=1;
	long limite=100000000;
	Object myObject = new Object();
	long inicTiempo = System.nanoTime();

	for(i=0; i<limite; i++)
		synchronized(myObject)
		{
			n++;
		}
	
		long tiempoTotal = System.nanoTime()-inicTiempo;
	System.out.println("en "+tiempoTotal+" nanosegundos...");
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
 	System.out.println("en "+tiempoTotal+" nanosegundos...");
  }
}