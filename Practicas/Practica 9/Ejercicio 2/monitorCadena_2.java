/*
* @author Guillermo Girón García
* @version 1.0
* Class that implements second part of the factory
*/

public class monitorCadena_2 implements Runnable
{
  public static Buffer buffer1, buffer2;

  public monitorCadena_2(Buffer buffer1, Buffer buffer2)
  {
    this.buffer1 = buffer1;
    this.buffer2 = buffer2;
  }

  public void consume() throws Exception
  {
    Matrix m = buffer1.extraer();
    m.transponse();
    buffer2.insertar(m);
    System.out.println("transponse done!");
    Thread.currentThread().sleep(1000);
  }

  @Override
  public void run()
  {
    while(true)
      try {consume();} catch (Exception e) {}
  }
}
