import java.lang.Math;

public class aleatorios
{
  private double v[];
  private int longitud;

  public aleatorios(int n)
  {
    v = new double[n];
    longitud = n;

    for (int i = 0; i < n; ++i)
    {
      v[i] = Math.random();
    }
  }

  public static void main(String[] args)
  {
    aleatorios serie = new aleatorios(Integer.parseInt(args[0]));
    for(int i = 0; i < serie.longitud; ++i)
    {
      System.out.println(serie.v[i]);
    }
  }
}
