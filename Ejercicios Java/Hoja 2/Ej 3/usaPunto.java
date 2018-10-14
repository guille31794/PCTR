
public class usaPunto
{
  public static void main(String[] args)
  {
    Punto p = new Punto(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
    System.out.println(p.cuadrant() + " cuadrant");
  }
}
