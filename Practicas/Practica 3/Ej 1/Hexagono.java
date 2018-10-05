/**
* @author Guillermo Girón García
* @version 1.0
* Clase que simula un hegono
*/

public class Hexagono extends Poligono
{
  private double apothem;
  public Hexagono() {super(1.0, 6); apothem = 1.0;}
  public Hexagono(double eS, double a)  {super(eS, 6); apothem = a;}
  public double Area() {return (super.Perimeter()*apothem)/2;}

	/**
	* Create string representation of Hexagono for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Hexagono [apothem=" + apothem + super.toString() + ", Area[" + Area()
		+ "]";
	}

	/**
	* Returns value of apothem
	* @return
	*/
	public double getApothem() {
		return apothem;
	}

	/**
	* Sets new value of apothem
	* @param
	*/
	public void setApothem(double apothem) {
		this.apothem = apothem;
	}
}
