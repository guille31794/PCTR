import java.lang.Math;

public class NewtonRaphson
{
  private double x_, fx, fdx;
  private int it_;
  public NewtonRaphson(double x, int it)
  {
    x_ = x;
    it_ = it;
    if(x_ < 1.5)
    {
      fx = Math.cos(x_) - Math.pow(x_, 3);
      fdx = -Math.sin(x_) - 3.0 * (x_ * x_);
    }
    else
    {
      fx = (x_*x_) - 5.0;
      fdx = 2 * x_;
    }
  }

  private double f(double x_)
  {
    if(x_ < 1.5)
      return  Math.cos(x_) - Math.pow(x_, 3);
    else
      return (x_*x_) - 5.0;
  }

  private double fd(double x_)
  {
    if(x_ < 1.5)
      return -Math.sin(x_) - 3.0 * (x_ * x_);
    else
      return 2 * x_;
  }

  public void aprox()
  {
    for(int i = 0; i < it_; ++i)
    {
      System.out.println(x_);
      x_ = x_ - (fx/fdx);
      fx = f(x_);
      fdx = fd(x_);
    }
  }

public static void main(String[] args)
{
  NewtonRaphson N = new NewtonRaphson(Double.parseDouble(args[0]), Integer.parseInt(args[1]));
  N.aprox();
}
}
