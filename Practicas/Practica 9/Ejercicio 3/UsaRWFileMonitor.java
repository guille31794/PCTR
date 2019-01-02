/**
 * @author Guillermo Girón García
 * @version 1.0
 * Class that implements the readers and writers problem solved with a given And
 * slightly modified monitor
 */

import java.util.concurrent.*;
import java.io.RandomAccessFile;
import java.util.Random;

 public class UsaRWFileMonitor
 {
   public static void main(String[] args) throws Exception
   {
      RWFileMonitor monitor = new RWFileMonitor();
      ThreadPoolExecutor tpe = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);
      tpe.execute(new writer(monitor));
      tpe.execute(new writer(monitor));
      tpe.execute(new reader(monitor));
      tpe.execute(new reader(monitor));
      //tpe.execute(new reader(monitor));
      tpe.shutdown();
   }
 }

 class reader implements Runnable
 {
    private RandomAccessFile file;
    private RWFileMonitor monitor;

    public reader(RWFileMonitor monitor) throws Exception
    {
      this.monitor = monitor;
      file = new RandomAccessFile("datos.dat", "r");
   }

   @Override
   public void run()
   {
      while(true)
      {
         monitor.StartRead();
         try
         {  System.out.println(file.readInt()); } catch(Exception e) {}
         monitor.EndRead();
      }
   }
 }

 class writer implements Runnable
 {
    private RandomAccessFile file;
    private Random r;
    private RWFileMonitor monitor;

    public writer(RWFileMonitor monitor) throws Exception
    {
      this.monitor = monitor;
      r = new Random();
      file = new RandomAccessFile("datos.dat", "rwd");
   }

   @Override
   public void run()
   {
      while(true)
      {
         monitor.StartWrite();
         try
         {  file.writeInt(r.nextInt()); } catch (Exception e) {}

         monitor.EndWrite();
      }
   }
 }
