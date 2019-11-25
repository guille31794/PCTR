/*
* @author Guillermo Girón García
* @version 1.0
* Class that implements second part of the factory
*/

public class monitorCadena_3 implements Runnable
{
  public static Buffer buffer;

  public monitorCadena_3(Buffer buffer)
  {
    this.buffer = buffer;
  }

  public void consume() throws Exception
  {
    Matrix m = buffer.extraer();
    m.diag();
    System.out.println("Diagonal product complete");
    Thread.currentThread().sleep(1000);
  }

  @Override
  public void run()
  {
    while(true)
      try {consume();} catch (Exception e) {}
  }
}
