/**
* @author Guillermo Girón García
* @version 1.0
* Clase que simula un triangulo
*/

public class Triangulo extends Poligono
{
  private double b_, h_;
  public Triangulo(){ super(1.0, 3); b_ = 1.0; h_ = 0.867;}
  public Triangulo(double b, double h)  { super(b, 3); b_ = b; h_ = h;}
  public double Area()  { return (b_*h_)/2; }

	/**
	* Returns value of b_
	* @return base size
	*/
	public double getB_() {
		return b_;
	}

	/**
	* Sets new value of b_
	* @param new base size
	*/
	public void setB_(double b_) {
		this.b_ = b_;
	}

	/**
	* Returns value of h_
	* @return height size
	*/
	public double getH_() {
		return h_;
	}

	/**
	* Sets new value of h_
	* @param new height size
	*/
	public void setH_(double h_) {
		this.h_ = h_;
	}

	/**
	* Create string representation of Triangulo for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Triangulo [b_=" + b_ + ", h_=" + h_ + ", Area=" + Area() +
		", Diameter=" + super.Perimeter() + ", Diagonals=" + super.Diagonals() +"]";
	}
}
