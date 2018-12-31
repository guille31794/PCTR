/*
* @author Guillermo Girón García
* @version 1.0
* Class that implements first part of the factory
*/

public class monitorCadena_1 implements Runnable
{
  public static Buffer buffer;

  public monitorCadena_1(Buffer buffer)
  {this.buffer = buffer;}

  public void produce() throws Exception
  {
    Matrix m = new Matrix();
    buffer.insertar(m);
    System.out.println("New matrix produced");
    Thread.currentThread().sleep(500);
  }

  @Override
  public void run()
  {
    while(true)
      try {produce();} catch (Exception e) {}
  }
}
