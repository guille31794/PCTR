/*
* @author Guillermo Girón García
* @version 1.0
* Class that implements an integer random squared 10x10 Matrix
*/

import java.util.Random;

public class Matrix
{
  private int[][] m = new int[10][10];

	public Matrix()
  {
		Random r = new Random();
    for(int i = 0; i < 10; ++i)
      for(int j = 0; j < 10; ++j)
        m[i][j] = r.nextInt(10);
	}

  public void transponse()
  {
    int aux;
    for(int i = 0; i < 10; ++i)
      for(int j = 0; j < 10; ++j)
      {
        aux = m[i][j];
        m[i][j] = m[j][i];
        m[j][i] = aux;
      }
  }

  public void diag()
  {
    int acum = 0;
    for(int i = 0; i < 10; ++i)
      acum += m[i][i];
    System.out.println(acum);
  }
}
