/**
* @author Guillermo Girón García
* @version 1.0
* Clase que imprime un rectangulo de las dimensiones dadas por pantalla
*/

import java.util.String;

public class Rectangulo
{
  private int base, height;

	/**
	* Returns value of base
	* @return
	*/
	public int getBase() {
		return base;
	}

	/**
	* Sets new value of base
	* @param
	*/
	public void setBase(int base) {
		this.base = base;
	}

	/**
	* Returns value of height
	* @return
	*/
	public int getHeight() {
		return height;
	}

	/**
	* Sets new value of height
	* @param
	*/
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	* Default empty Rectangulo constructor
	*/
	public Rectangulo() {}

	/**
	* Default Rectangulo constructor
	*/
	public Rectangulo(int b, int h) {base = b; height = h;}

	/**
	* Create string representation of Rectangulo for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Rectangulo [base=" + base + ", height=" + height + "]";
	}

	public static void main(String[] args)
	{
		Rectangulo R = new Rectangulo(Integer.parseInt(args[0]),
		Integer.parseInt(args[1]));

		for(int i = 0; i < R.getHeight(); ++i)
		{
			if(i == 0 || i == R.getHeight()-1)
			{
				String s = new String();
				for(int j = 0; j < s.length(); ++j)
				{
					//System.out.println(String.format(s, '*', R.getBase(), s));
				}
			}
		}
	}
}
