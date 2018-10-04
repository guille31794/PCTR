/**
* @author Guillermo Girón García
* @version 1.0
* Clase que simula el comportamiento de una elipse situada siempre en el origen
* de coordenadas
*/

import java.lang.Math;

public class Elipse
{
  private double ejeMayor, ejeMenor;
  public Elipse() {ejeMayor = 0.0; ejeMenor = 0.0;}
  public Elipse(double rx, double ry)  {if(rx >= 0.0) ejeMayor = rx; else ejeMayor = 0.0;
  if(ry >= 0.0) ejeMenor = ry; else ejeMenor = 0;}

	/**
	* Create string representation of Elipse for printing
	* @return cadena con los parametros de la elipse
	*/
	@Override
	public String toString() {
		return "Elipse [ejeMayor=" + ejeMayor + ", ejeMenor=" + ejeMenor + "]";
	}

	/**
	* Returns value of ejeMayor
	* @return radio de x
	*/
	public double getejeMayor() {
		return ejeMayor;
	}

	/**
	* Sets new value of ejeMayor
	* @param radio de x
	*/
	public void setejeMayor(double ejeMayor) {
		this.ejeMayor = ejeMayor;
	}

	/**
	* Returns value of ejeMenor
	* @return radio de y
	*/
	public double getejeMenor() {
		return ejeMenor;
	}

	/**
	* Sets new value of ejeMenor
	* @param radio de y
	*/
	public void setejeMenor(double ejeMenor) {
		this.ejeMenor = ejeMenor;
	}

	public void Coordinate(double x, double y)
	{
		if(((x*x)/(getejeMayor()*getejeMayor()))+((y * y)/(getejeMenor()*getejeMenor())) < 1.0)
		{
				System.out.println("The point is inside the elipse");
		}
		else
			System.out.println("The point is not inside the elipse");
	}
}
