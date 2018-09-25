import java.util.Scanner;

public class Circulo
{
  public Circulo(double d) { d_ = d;}
  public double diameter() {return d_;}
  public static final double pi = 3.1415;
  protected double d_;
}

class Cono extends Circulo
{
  public Cono(double d, double h)  { super(d); h_ = h;}
  public double area() { return (h_* ((d_/2) * (d_/2)) * pi) / 3;}
  protected double h_;

  public static void main(String[] args)
  {
    Cono C = new Cono(14.2, 20.0);
    System.out.println("The area of the cone is " + C.area());
  }
}
