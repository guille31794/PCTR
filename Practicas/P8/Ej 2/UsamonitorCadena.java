/*
* @author Guillermo Girón García
* @version 1.0
* Class that makes an example of the factory
*/

import java.util.concurrent.*;

public class UsamonitorCadena
{
  public static void main(String[] args)
  {
    Buffer buffer1 = new Buffer(100);
    Buffer buffer2 = new Buffer(50);

    ThreadPoolExecutor tpe = (ThreadPoolExecutor)Executors.newFixedThreadPool(3);
    tpe.execute(new monitorCadena_1(buffer1));
    tpe.execute(new monitorCadena_2(buffer1, buffer2));
    tpe.execute(new monitorCadena_3(buffer2));
    tpe.shutdown();
  }
}
