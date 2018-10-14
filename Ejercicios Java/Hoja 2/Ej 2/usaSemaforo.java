import java.util.concurrent.TimeUnit;

public class usaSemaforo
{
  public static void main(String[] args)
  {
    Semaforo s = new Semaforo();
    for(int i = 0; i < 3; ++i)
    {
      try
      {
        s.setColour(i);
        System.out.println("Semaphore " + s.getColour());
        TimeUnit.SECONDS.sleep(1);
    } catch (Exception e) { continue;}
    }
  }
}
