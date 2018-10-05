/**
* @author Guillermo Girón García
* @version 1.0
* Clase que simula un poligono regular de manera abstracta a partir de un punto
*/

public class Poligono extends Punto
{
  private double edgeSize;
	private int vertexN;
  public Poligono() {super(0.0, 0.0); edgeSize = 1.0; vertexN = 3;}
  public Poligono(double eS, int vN) { super(0.0, 0.0); edgeSize = eS; vertexN = vN;}
	public double Perimeter()	{return edgeSize*vertexN;}
	public int Diagonals()	{	return (vertexN*(vertexN-3))/2;}

	/**
	* Returns value of edgeSize
	* @return
	*/
	public double getEdgeSize() {
		return edgeSize;
	}

	/**
	* Sets new value of edgeSize
	* @param
	*/
	public void setEdgeSize(double edgeSize) {
		this.edgeSize = edgeSize;
	}

	/**
	* Returns value of vertexN
	* @return
	*/
	public int getVertexN() {
		return vertexN;
	}

	/**
	* Sets new value of vertexN
	* @param
	*/
	public void setVertexN(int vertexN) {
		this.vertexN = vertexN;
	}

	/**
	* Create string representation of Poligono for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Poligono [edgeSize=" + edgeSize + ", vertexN=" + vertexN +
    ", Diameter=" + Diameter() + ", Diagonals=" + Diagonals() + "]";
	}
}
