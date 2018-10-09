/**
* @author Guillermo Girón García
* @version 1.0
* Programa que calcula varias operaciones estadisticas a partir de una secuencia
* de numeros determinada por el usuario como parametro y unos numeros introducidos
* también por el usuario
*/

import java.util.*;
import java.lang.Math;

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

    try
    {
      for(int i = 0; i < size; ++i)
      {
        System.out.println("Introduzca elemento " + (i+1));
        vector[i] = sc.nextInt();
        sc.nextLine();
      }
    } catch(Exception e)  {}
  }

  public double media()
  {
    int sum = 0;
    for(int i = 0; i < size; ++i)
      sum += vector[i];

    return (sum/size);
  }

  public int moda()
  {
    int frecuency = 0, acumulatedFrecuency = 0, moda = 0;

    for(int i = 0; i < size; ++i)
    {
      for(int j = 0; j < size; ++j)
      {
        if(vector[i] == vector[j])
         ++frecuency;
      }
      if(frecuency > acumulatedFrecuency)
        moda = vector[i];
    }

    return moda;
  }

  public double varianza()
  {
    double media = this.media();
    double varianza, sum = 0, aux = 0;

    for(int i = 0; i < size; ++i)
    {
      aux = vector[i] - media;
      aux = Math.pow(aux, 2);
      sum += aux;
    }

    varianza = sum / size;

    return varianza;
  }

  public double desviacion()
  {
    double desviacion = this.varianza();
    desviacion = Math.sqrt(desviacion);

    return desviacion;
  }

  public static void main(String[] args)
  {
    int option;
    Estadistica es = new Estadistica(Integer.parseInt(args[0]));
    es.insertElem();
    Scanner sc = new Scanner(System.in);
    try
    {
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
    } catch(Exception e)  {}
  }
}
