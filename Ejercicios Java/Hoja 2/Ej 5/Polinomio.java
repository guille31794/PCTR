import java.lang.Math;

public class Polinomio
{
  private int p[];
  private int degree;

  public Polinomio()  {degree = 1; p = new int[degree];}
  public Polinomio(int n) {degree = n; p = new int[degree];}

	public int getPx(int x) {
		if(x < 0 || x > degree)
			throw new IllegalArgumentException("Degree out of range");
		return p[x];
	}

	public void setPx(int x, int e) {
		if(x < 0 || x > degree)
			throw new IllegalArgumentException("Degree out of range");
		p[x] = e;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
		int aux[] = p;
		p = new int[degree];
		p = aux;
	}

	public Polinomio add(Polinomio p)
	{
		Polinomio toReturn = new Polinomio();
		int mDegree = Math.max(this.getDegree(), p.getDegree());
		toReturn.setDegree(mDegree);
		mDegree = Math.min(this.getDegree(), p.getDegree());
		for(int i = mDegree-1; i >= 0; --i)
		{
			toReturn.setPx(i, (this.getPx(i)+p.getPx(i)));
		}
		return toReturn;
	}

	public Polinomio sub(Polinomio p)
	{
		Polinomio toReturn = new Polinomio();
		int mDegree = Math.max(this.getDegree(), p.getDegree());
		toReturn.setDegree(mDegree);
		mDegree = Math.min(this.getDegree(), p.getDegree());
		for(int i = mDegree-1; i >= 0; --i)
		{
			toReturn.setPx(i, (this.getPx(i)-p.getPx(i)));
		}
		return toReturn;
	}

	public Polinomio prod(int k)
	{
		Polinomio toReturn = new Polinomio(getDegree());
		for(int i = getDegree()-1; i >= 0; --i)
		{
			toReturn.setPx(i, (this.getPx(i)*k));
		}
		return toReturn;
	}

	public Polinomio div(int k)
	{
		Polinomio toReturn = new Polinomio(getDegree());
		for(int i = getDegree()-1; i >= 0; --i)
		{
			toReturn.setPx(i, (this.getPx(i)/k));
		}
		return toReturn;
	}

	public String toString()
	{
		String p = new String();
		for(int i = getDegree()-1; i >= 0 ;--i)
			p += (Integer.toString(getPx(i)) + "x^" + i + " ");
		return p;
	}

	public void setRandomP()
	{
		for(int i = 0; i < getDegree(); ++i)
		{
			setPx(i, (int)(Math.random()*10));
		}
	}
}
