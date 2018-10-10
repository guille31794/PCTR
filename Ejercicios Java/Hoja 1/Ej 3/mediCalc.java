/**
* @author Guillermo Girón García
* @version 1.0
* Clase que simula una calculadora del nivel de aclaramiento renal y el IMC
*/

import java.util.Scanner;

public class mediCalc
{
  public static float weight, height, creatinine, bmi, clearance;
  public static int age;
  public static boolean man;

  public static void main(String[] args)
  {
    int option;
    Scanner sc = new Scanner(System.in);
    System.out.println("Menu:");
    System.out.println("1.- Clearing");
    System.out.println("2.- BMI");
    System.out.println("Other.- Exit");
    System.out.print("Select: ");
    try
    {
      option = sc.nextInt();
      sc.nextLine();
      switch(option)
      {
        case 1: System.out.println("Insert age: ");
                try
                {
                  age = sc.nextInt();
                  sc.nextLine();
                } catch(Exception e){}
                System.out.println("Insert weight: ");
                try
                {
                  weight = Float.parseFloat(sc.nextLine());
                } catch(Exception e)  {}
                System.out.println("Insert creatinine level: ");
                try
                {
                  creatinine = Float.parseFloat(sc.nextLine());
                } catch (Exception e) {}
                System.out.println("Are you a woman? y/n");
                if(sc.nextLine() == "y")
                  man = false;
                else
                  man = true;
                if(man)
                {
                  clearance = (((140-age)*weight))/(72*creatinine);
                  System.out.println("Clearance lvl = " + clearance);
                }
                else
                {
                  clearance = ((((140-age)*weight))/(72*creatinine))*(float)0.85;
                  System.out.println("Clearance lvl = " + clearance);

                }
        break;
        case 2: System.out.println("Insert weight: ");
                try
                {
                  weight = Float.parseFloat(sc.nextLine());
                } catch (Exception e) {}
                System.out.println("Insert height: ");
                try
                {
                  height = Float.parseFloat(sc.nextLine());
                } catch(Exception e)  {}
                bmi = weight/(height*height);
                System.out.println("BMI = " + bmi);
        break;
        default: break;
      }
    } catch(Exception e){}
  }
}
