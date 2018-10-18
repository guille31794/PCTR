
public class ParImpar extends Thread
{
  private int pI, it;

  ParImpar(int pI, int it){ this.pI = pI; this.it = it;}

  public void iter()
  {
    int random;
    if(pI % 2 == 0)
      for(int i = 0; i < it; ++i)
      {
        random = (int)(Math.random()*9999);
        if(random % 2 == 0)
          System.out.println(random);
        else
          System.out.println("Aquí vendría un par");
      }
    else
      for(int i = 0; i < it; ++i)
      {
        random = (int)(Math.random()*9999);
        if(random % 2 == 1)
          System.out.println(random);
        else
          System.out.println("Aqui vendría un impar");
      }
  }

  @Override
  public void run()
  {
    iter();
  }
}
