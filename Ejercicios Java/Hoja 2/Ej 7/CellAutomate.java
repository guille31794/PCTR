import java.lang.Math;

public class CellAutomate
{
  private final int cellN;
  private final int n;
  private final int generations;
  private int[] primitive;
  private int[] evolved;

  public CellAutomate(int n, int size, int g)
  {
    cellN = size;
    this.n = n;
    primitive = new int[cellN];
    evolved = new int[cellN];
    generations = g;

    for(int i = 0; i < cellN; ++i)
      primitive[i] = Math.random()*100;
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

  public static void main(String[] args)
  {
    CellAutomate ca = new CellAutomate(Integer.parseInt(args[0]),
    Integer.parseInt(args[1]), Integer.parseInt(args[2]));
    ca.evolution();
  }
}
