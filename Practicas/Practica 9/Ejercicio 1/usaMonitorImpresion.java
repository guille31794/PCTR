/*
* @author Guillermo Girón García
* @version 1.0
* Class that implements a monitor to control printers
*/

import java.util.concurrent.*;

public class usaMonitorImpresion
{
  public static void main(String[] args) throws Exception
  {
    monitorImpresion monitor = new monitorImpresion();
    monitor.init();
    double Cb = 0.5;
    int Nc = Runtime.getRuntime().availableProcessors();
    int Nt = (int)(Nc/(1-Cb));
    ThreadPoolExecutor tpe = (ThreadPoolExecutor)Executors.newFixedThreadPool(Nt);
    for(int i = 0; i < Nt; ++i)
      tpe.execute(new worker(monitor));
    tpe.shutdown();
    if(!tpe.awaitTermination(30, TimeUnit.SECONDS))
      System.err.println("Time over");
  }
}

class worker implements Runnable
{
  private monitorImpresion monitor;

  public worker(monitorImpresion monitor)
  {
    this.monitor = monitor;
  }

  void print(int printer) throws Exception
  {
    System.out.println("Taking my time to print something important...");
    Thread.currentThread().sleep(1000);
    System.out.println(Thread.currentThread().getName() +
    " salute you from printer " + printer);
    Thread.currentThread().sleep(1000);
    System.out.println("Closing...");
    Thread.currentThread().sleep(500);
  }

  @Override
  public void run()
  {
    try
    {
      int available = this.monitor.usePrinter();
      this.print(available);
      this.monitor.freePrinter(available);
    } catch(Exception e)  {}

  }
}
