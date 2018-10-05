/**
* @author Guillermo Girón García
* @version 1.0
* Clase que simula un cuadrado
*/

public class Cuadrado extends Poligono
{
  public Cuadrado() {super(1.0, 4);}
  public Cuadrado(double eS)  {super(eS, 4);}
  public double Area()  {return this.getEdgeSize()*this.getEdgeSize();}


	/**
	* Create string representation of Cuadrado for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Cuadrado[ " + super.toString() + ", Area=" + Area() + "]";
	}
}
