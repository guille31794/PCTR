/*
* @author Guillermo Girón García
* @version 1.0
* Class that implements a writers and readers problem
*/

import java.util.concurrent.*;

public class lectorEscritor
{
  public static boolean writing = false;
  public static int reading = 0;

  public synchronized void read() throws Exception
  {
    while(writing == true)
      wait();
    synchronized(this)  {++reading;}
    System.out.println(Thread.currentThread().getName() + " is reading..");
    // It can be replaced by a random access file instance
    Thread.currentThread().sleep(1000);
    synchronized(this)  {--reading;}
    notifyAll();
  }

  public synchronized void write() throws Exception
  {
    synchronized(this) {writing = true;}
    while(reading > 0)
      wait();
    System.out.println(Thread.currentThread().getName() + " is writing..");
    // It can be replaced by a random access file instance
    Thread.currentThread().sleep(1000);
    synchronized(this)  {writing = false;}
    notifyAll();
  }
}
