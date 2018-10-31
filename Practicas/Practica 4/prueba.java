import java.util.Arrays;
public class prueba
{
  public static void main(String[] args)
  {
    int[][] v = new int[3][3];
    for(int i = 0; i < 3; ++i)
      for(int j = 0; j < 3; ++j)
        v[i][j] = i;
    System.out.print(Arrays.deepToStringtoString(v));
  }
}
