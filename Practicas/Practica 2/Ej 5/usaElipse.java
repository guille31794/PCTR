/**
* @author Guillermo Girón García
* @version 1.0
* Clase que hace uso de la clase Elipse
*/

public class usaElipse
{
  public static void main(String[] args)
  {
    Elipse e = new Elipse();
    Elipse e1 = new Elipse(2, 3);
    Elipse e2= new Elipse(8.9, 5.6);

    System.out.println(e1.toString());
    System.out.println(e2.toString());

    e1.Coordinate(3, 1.5);
    e2.Coordinate(3, -1.5);
    e2.Coordinate(1, 2);
  }
}
