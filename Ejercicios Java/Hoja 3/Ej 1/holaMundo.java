
public class holaMundo extends Thread
{
  @Override
  public void run()
  {
    System.out.println("Hola mundo");
  }

  public static void main(String[] args)
  {
    holaMundo h = new holaMundo();
    h.start();
  }
}
