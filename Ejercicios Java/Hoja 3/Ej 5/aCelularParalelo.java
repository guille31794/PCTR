
public class aCelularParalelo extends Thread
{
  private final int cellN;
  private final int n;
  private final int generations;
  private int[] primitive;
  private int[] evolved;

  public aCelularParalelo(int n, int size, int g)
  {
    cellN = size;
    this.n = n;
    primitive = new int[cellN];
    evolved = new int[cellN];
    generations = g;

    for(int i = 0; i < cellN; ++i)
      primitive[i] = (int)(Math.random()*100);
  }

  public void evolution()
  {
    for(int j = 0; j < generations; ++j)
    {
      for(int i = 1; i < cellN-1; ++i)
        evolved[i] = (primitive[i-1]+primitive[i]+primitive[i+1]) % n;

      primitive = evolved;
    }
  }

  @Override
  public void run()
  {
    evolution();
  }

  public static void main(String[] args)
  {
    aCelularParalelo ca = new aCelularParalelo(Integer.parseInt(args[0]),
    Integer.parseInt(args[1]), Integer.parseInt(args[2]));
    ca.start();
  }
}
