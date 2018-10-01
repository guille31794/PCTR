/**
* @author Guillermo Girón García
* @version 1.0
* Programa que calcula varias operaciones estadisticas a partir de una secuencia
* de numeros determinada por el usuario como parametro y unos numeros introducidos
* también por el usuario
*/

import java.util.Scanner;

public class Estadistica
{
  private int[] vector;
  private int size;
  public Estadistica(int nElem)
  {
    size = nElem;
    vector = new int[nElem];
  }

  public void insertElem()
  {
    Scanner sc = new Scanner(System.in);
    for(int i = 0; i < size; ++i)
    {
      System.out.println("Introduzca elemento " + (i+1));
      vector[i] = sc.nextInt();
      //sc.nextLine();
    }
  }

  public double media()
  {

  }

  public double moda()
  {

  }

  public double varianza()
  {

  }

  public double desviacion()
  {
    
  }

  public static void main(String[] args)
  {
    int option;
    Scanner sc = new Scanner(System.in);
    Estadistica es = new Estadistica(Integer.parseInt(args[0]));
    es.insertElem();

    System.out.println("Menu estadistico");
    System.out.println("1.- Media");
    System.out.println("2.- Moda");
    System.out.println("3.- Varianza");
    System.out.println("4.- Desviacion tipica");
    System.out.println("Otra.- Salir");
    option = sc.nextInt();

    switch(option)
    {
      case 1: System.out.println("La media es " + es.media());
      break;
      case 2: System.out.println("La moda es " + es.moda());
      break;
      case 3: System.out.println("La varianza es " + es.varianza());
      break;
      case 4: System.out.println("La desviacion tipica es " + es.desviacion());
      break;
      default: break;
    }
  }
}
