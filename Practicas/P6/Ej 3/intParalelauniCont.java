/**
* @author Guillermo Girón Garcíab
* @version 1.0
* Clase que calcula la integral definida de monte Carlo a partir de un argumento
* introducido por consola
*/

import java.lang.Math;
import java.util.concurrent.*;
import java.util.Random;

public class intParalelauniCont implements Runnable
{
  protected static int pUnderF;
  private int start, end;

  public intParalelauniCont(int start, int end)
  {
    this.start = start;
    this.end = end;
  }

  @Override
  public void run()
  {
    Random r = new Random(1);
    double x, y;
    for(int i = this.start; i < this.end; ++i)
    {
      x = r.nextDouble();
      y = r.nextDouble();

      if(y < Math.sin(x))
        synchronized(this) {  ++pUnderF;}
    }

  }

  public static void main(String[] args) throws Exception
  {
    int tPoints = Integer.parseInt(args[0]);
    pUnderF = 0;
    int nThreads = Runtime.getRuntime().availableProcessors();
    int frameSize =  tPoints/nThreads, start = 0, end = frameSize, i = 0;
    ThreadPoolExecutor tpe =
    (ThreadPoolExecutor)Executors.newFixedThreadPool(nThreads);
    double endTime, initTime = System.currentTimeMillis();
    while(i < nThreads)
    {
      tpe.execute(new intParalelauniCont(start, end));
      start = end;
      end += frameSize;
      ++i;
    }
    tpe.shutdown();
    if(!tpe.awaitTermination(10, TimeUnit.SECONDS))
      System.out.println("Error");
    endTime = System.currentTimeMillis();
    System.out.println("Sin function aproximation is: " + (double)pUnderF/tPoints);
    System.out.println("Time: " + (endTime-initTime) + "ms");
  }
}
