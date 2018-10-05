/**
* @author Guillermo Girón García
* @version 1.0
* Clase que simula un pentagono
*/

public class Pentagono extends Poligono
{
  private double apothem;
  public Pentagono() {super(1.0, 5); apothem = 1.0;}
  public Pentagono(double eS, double a)  {super(eS, 5); apothem = a;}
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
