/**
* @author Guillermo Girón García
* @version 1.0
* Clase que usa todos los poligonos
*/

public class usaTodo
{
  public static void main(String[] args)
  {
    Poligono p1, p2;
    Triangulo t1, t2;
    Cuadrado c1, c2;
    Pentagono pt1, pt2;
    Hexagono h1, h2;

    t1 = new Triangulo();
    t2 = new Triangulo(4.0, 3.5);
    c1 = new Cuadrado();
    c2 = new Cuadrado(6.0);
    pt1 = new Pentagono();
    pt2 = new Pentagono(2.2, 4.9);
    h1 = new Hexagono();
    h2 = new Hexagono(1.8, 3.2);
    p1 = h1;
    p2 = new Pentagono();

    System.out.println(p1.toString());
    System.out.println(p2.toString());
    System.out.println(t1.toString());
    System.out.println(t2.toString());
    System.out.println(c1.toString());
    System.out.println(c2.toString());
    System.out.println(pt1.toString());
    System.out.println(pt2.toString());
    System.out.println(h1.toString());
    System.out.println(h2.toString());
  }
}
