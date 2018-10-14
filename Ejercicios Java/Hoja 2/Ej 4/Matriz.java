import java.lang.Math;

public class Matriz
{
  private double M[][];
  private int dimX, dimY;

	/**
	* Default empty Matriz constructor
	*/
	public Matriz()
  {
		dimX = 1;
    dimY = 1;
		M = new double[1][1];
	}

	/**
	* Default Matriz constructor
	*/
	public Matriz(int dimX, int dimY)
  {
		this.dimX = dimX;
		this.dimY = dimY;
		M = new double[dimX][dimY];
	}

	/**
	* Returns value of M
	* @return
	*/
	public double getM(int x, int y) {
		if(x < 0 && y < 0 && x > dimX && y > dimY)
			throw new IllegalArgumentException("Dimension out of range");
		return M[x][y];
	}

	/**
	* Sets new value of M
	* @param
	*/
	public void setElement(int x, int y, double e)
	{
		if(x < 0 || y < 0 || x > dimX || y > dimY)
			throw new IllegalArgumentException("Dimension out of range");
		M[x][y] = e;
	}

	/**
	* Returns value of dimX
	* @return
	*/
	public int getDimX() {
		return dimX;
	}

	/**
	* Sets new value of dimX
	* @param
	*/
	public void setDimX(int dimX) {
		this.dimX = dimX;
	}

	/**
	* Returns value of dimY
	* @return
	*/
	public int getDimY() {
		return dimY;
	}

	/**
	* Sets new value of dimY
	* @param
	*/
	public void setDimY(int dimY) {
		this.dimY = dimY;
	}

	public Matriz add(Matriz M)
	{
		if(M.getDimY() != M.getDimY() || getDimX() != M.getDimX())
			throw new IllegalArgumentException();

		Matriz toReturn = new Matriz(getDimX(), getDimY());

		for(int i = 0; i < getDimX(); ++i)
			for(int j = 0; j < getDimY(); ++j)
				toReturn.setElement(i, j, (M.getM(i, j) + this.getM(i, j)));

		return toReturn;
	}

	public Matriz sub(Matriz M)
	{
		if(M.getDimY() != M.getDimY() || getDimX() != M.getDimX())
			throw new IllegalArgumentException();

		Matriz toReturn = new Matriz(getDimX(), getDimY());

		for(int i = 0; i < getDimX(); ++i)
			for(int j = 0; j < getDimY(); ++j)
				toReturn.setElement(i, j, (M.getM(i, j) - this.getM(i, j)));

		return toReturn;
	}

	public Matriz prod(int k)
	{
		Matriz toReturn = new Matriz(getDimX(), getDimY());

		for(int i = 0; i < getDimX(); ++i)
			for(int j = 0; j < getDimY(); ++j)
				toReturn.setElement(i, j, (this.getM(i, j) * k));

		return toReturn;
	}

	public void print()
	{
		for(int i = 0; i < getDimX(); ++i)
		{
			System.out.print("[ ");
			for(int j = 0; j < getDimY(); ++j)
			{
				System.out.print(" " + getM(i, j) + " ");
			}
			System.out.println("]");
		}
	}

	public void randomSetMatrix()
	{
		for (int i = 0; i < getDimX(); ++i)
			for(int j = 0; j < getDimY(); ++j)
				setElement(i, j, (Math.random()*100));
	}
}
