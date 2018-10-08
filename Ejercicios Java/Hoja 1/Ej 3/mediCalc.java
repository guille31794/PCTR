/**
* @author Guillermo Girón García
* @version 1.0
* Clase que simula una calculadora del nivel de aclaramiento renal y el IMC
*/

import java.io.*;

public class mediCalc
{
  protected float weight, height, creatinine;
  protected int age;

  public static void main(String[] args)
  {
    int option;
    //BuferRea
    System.out.println("Menu:");
    System.out.println("1.- Clearing");
    System.out.println("2.- BMI");
    System.out.print("Select: ");

    switch(option)
    {
      case 1: System.out.println("Insert age: ");
              System.out.println("Insert weight: ");
              System.out.println("Insert creatinine level: ");
      break;
      case 2: System.out.println("Insert age: ");
              System.out.println("Insert height: ");
      break;
      default: break;
    }
  }
}
