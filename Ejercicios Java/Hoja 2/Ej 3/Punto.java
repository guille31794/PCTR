
public class Punto
{
  private double x, y;

	/**
	* Default empty Punto constructor
	*/
	public Punto() {
		x = 0;
    y = 0;
	}

	/**
	* Default Punto constructor
	*/
	public Punto(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	* Returns value of x
	* @return
	*/
	public double getX() {
		return x;
	}

	/**
	* Sets new value of x
	* @param
	*/
	public void setX(double x) {
		this.x = x;
	}

	/**
	* Returns value of y
	* @return
	*/
	public double getY() {
		return y;
	}

	/**
	* Sets new value of y
	* @param
	*/
	public void setY(double y) {
		this.y = y;
	}

	public String cuadrant()
	{
		String cuadrant = "\0";
		if( x == 0 && y == 0)
			cuadrant =  "Origin";
		else if( x > 0 && y > 0)
			cuadrant = "First";
			else if(x > 0 && y < 0)
				cuadrant = "Fourth";
			else if(x < 0 && y > 0)
				cuadrant = "Second";
			else if(x < 0 && y < 0)
				cuadrant = "Third";
			else if(x == 0 && y > 0)
				cuadrant = "Between first and second";
			else if(x == 0 && y < 0)
				cuadrant = "Between third and fourth";
			else if(x > 0 && y == 0)
				cuadrant = "Between first and fourth";
			else if(x < 0 && y == 0)
				cuadrant = "Between second and third";

			return cuadrant;
	}
}
