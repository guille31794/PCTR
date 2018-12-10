/**
* @author Guillermo Girón Garcíab
* @version 1.0
* Clase que calcula la integral definida de monte Carlo a partir de un argumento
* introducido por consola
*/

import java.lang.Math;
import java.util.concurrent.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class piFuture implements Callable
{
  protected static int pUnderF;
  private int start, end;

  public piFuture(int start, int end)
  {
    this.start = start;
    this.end = end;
  }

  @Override
  public Integer call() throws Exception
  {
    Random r = new Random(1);
    double x, y;
    int count = 0;
    for(int i = this.start; i < this.end; ++i)
    {
      x = r.nextDouble();
      y = r.nextDouble();

      if((x*x) + (y*y) <= 1)
        ++count;
    }
    return count;
  }

  public static void main(String[] args) throws Exception
  {
    int tPoints = Integer.parseInt(args[0]);
    pUnderF = 0;
    int nThreads = Runtime.getRuntime().availableProcessors();
    int frameSize =  tPoints/nThreads, start = 0, end = frameSize, i = 0;
    ThreadPoolExecutor tpe =
    (ThreadPoolExecutor)Executors.newFixedThreadPool(nThreads);
    List<Future<Integer>> list = new ArrayList<Future<Integer>>();
    double endTime, initTime = System.currentTimeMillis();
    while(i < nThreads)
    {
      list.add(tpe.submit(new piFuture(start, end)));
      start = end;
      end += frameSize;
      ++i;
    }
    tpe.shutdown();
    if(!tpe.awaitTermination(10, TimeUnit.SECONDS))
      System.out.println("Error");
    for(Future<Integer> future:list)
      pUnderF += future.get();
    endTime = System.currentTimeMillis();
    System.out.println("Pi function aproximation is: " + (double)pUnderF/tPoints);
    System.out.println("Time: " + (endTime-initTime) + "ms");
  }
}
