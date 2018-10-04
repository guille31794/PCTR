// Seccion de importación

import java.lang.Math;

public class Punto
 {
  double x, y; // Variables de instancia

  public Punto(){}

  public Punto(double x, double y)
  { // El constructor
    this.x= x; this.y= y;
  }
  public void moverEn(double dx, double dy)
  { // Un metodo
    this.x+= dx; this.y+= dy;
  }
  public String toString()
  { // Otro metodo
    return "("+this.x+","+this.y+")";
  }

  public void modificarPunto(double x, double y)
  {
    this.x = x;
    this.y = y;
  }

  //Observadores

  public double getX()
  {
    return x;
  }

  public double getY()
  {
    return y;
  }

  public double distanciaOrigen()
  {
    return (Math.sqrt((x * x) + (y * y)));
  }

  public double distanciaEntrePuntos(Punto p1, Punto p2)
  {
    double coordX = p1.x - p2.x;
    double coordY = p1.y - p2.y;

    return (Math.sqrt((coordX * coordX) + (coordY * coordY)));
  }

  public int cuadrante()
  {
    int cuadrante;

    if( x == 0 || y == 0)
      cuadrante = 0;
    else
      if(x > 0)
        if(y > 0)
          cuadrante = 1;
        else
          cuadrante = 4;
      else
        if(y > 0)
          cuadrante = 2;
        else
          cuadrante = 3;

    return cuadrante;
  }
}
