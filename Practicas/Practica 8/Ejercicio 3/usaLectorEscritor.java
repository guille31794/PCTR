/*
* @author Guillermo Girón García
* @version 1.0
* Class that implements an example of use of readers and writers problem
*/

import java.util.concurrent.*;

public class usaLectorEscritor
{
  public static void main(String[] args) throws Exception
  {
    lectorEscritor monitor = new lectorEscritor();
    int i = 0, nThreads = Runtime.getRuntime().availableProcessors();
    ThreadPoolExecutor tpe = (ThreadPoolExecutor)Executors.newFixedThreadPool(nThreads);
    tpe.execute(new Writer(monitor));
    while (i < nThreads-1)
    {
      tpe.execute(new Reader(monitor));
      ++i;
    }
    tpe.shutdown();
    if(!tpe.awaitTermination(10, TimeUnit.MINUTES))
      System.err.println("Library is about to close. Please come again later");
  }
}

class Reader implements Runnable
{
  private lectorEscritor monitor;

  public Reader(lectorEscritor monitor)
  {this.monitor = monitor;}

  @Override
  public void run()
  {
    try
    {
      while(true)
      {
        monitor.read();
        System.out.println("Thinking what to read next..");
        Thread.currentThread().sleep(1000);
      }
    } catch(Exception e)  {}
  }
}

class Writer implements Runnable
{
  private lectorEscritor monitor;

  public Writer(lectorEscritor monitor)
  {this.monitor = monitor;}

  @Override
  public void run()
  {
    try
    {
      while(true)
      {
        monitor.write();
        System.out.println("Thinking what to write next..");
        Thread.currentThread().sleep(1000);
      }
    } catch(Exception e)  {}
  }
}
