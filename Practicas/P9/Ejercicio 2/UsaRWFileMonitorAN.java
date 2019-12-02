/**
 * @author Guillermo Girón García
 * @version 1.0
 * Class that implements the readers and writers problem solved with a given and
 * slightly modified monitor, using locks
 */

import java.util.concurrent.*;
import java.io.RandomAccessFile;
import java.util.Random;

 public class UsaRWFileMonitorAN
 {
   public static void main(String[] args) throws Exception
   {
      RWFileMonitorAN monitor = new RWFileMonitorAN();
      ThreadPoolExecutor tpe = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);
      tpe.execute(new writer(monitor));
      tpe.execute(new writer(monitor));
      tpe.execute(new reader(monitor));
      tpe.execute(new reader(monitor));
      tpe.shutdown();
   }
 }

 class reader implements Runnable
 {
    private RandomAccessFile file;
    private RWFileMonitorAN monitor;

    public reader(RWFileMonitorAN monitor) throws Exception
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
    private RWFileMonitorAN monitor;

    public writer(RWFileMonitorAN monitor) throws Exception
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
