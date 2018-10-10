/**
* @author Guillermo Girón García
* @version 1.0
* Clase que imprime un rectangulo de las dimensiones dadas por pantalla
*/

public class Rectangulo
{
  private static int base, height;

	public static void main(String[] args)
	{
		base = Integer.parseInt(args[0]);
		height = Integer.parseInt(args[1]);
		for(int i = 0; i < height; ++i)
		{
			for(int j = 0; j < base; ++j)
        System.out.print("*");
			System.out.println();
		}
	}
}
