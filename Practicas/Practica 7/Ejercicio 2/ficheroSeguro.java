/*
* @author Guillermo Girón García
* @version 1.0
* Class that implements a concurrent writing in a file though RandomAccesFile
*/

import java.util.concurrent.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class ficheroSeguro implements Runnable
{
  public static String s = new String("The rain in Seville is wonderful and I'm extremely bored in Barbate");
  private int start, end;
  public ficheroSeguro(int start, int end)
  {
    this.start = start;
    this.end = end;
  }

  @Override
  public void run()
  {
    byte[] b = s.getBytes(StandardCharsets.UTF_8);
    try
    {
      RandomAccessFile f = new RandomAccessFile("test.txt", "rw");
      f.seek(this.start);
      f.write(b, this.start, (this.end-this.start));
      f.close();
    } catch(IOException e)  {}

  }

  public static void main(String[] args) throws Exception
  {
    ThreadPoolExecutor tpe = (ThreadPoolExecutor)Executors.newCachedThreadPool();
    int nThreads = Runtime.getRuntime().availableProcessors(),
    frameSize = s.length()/nThreads, start = 0, end = frameSize, i = 0;
    System.out.println(s.length());
    while (i < nThreads)
    {
      tpe.execute(new ficheroSeguro(start, end));
      start = end;
      end += frameSize;
      ++i;
    }
    end = start + (s.length()-start);
    tpe.execute(new ficheroSeguro(start, end));
    tpe.shutdown();
    tpe.awaitTermination(10, TimeUnit.SECONDS);
  }
}
