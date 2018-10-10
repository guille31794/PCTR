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
                if(clearance >= 90.0)
                  System.out.println("Normal Function");
                if(clearance < 90.0 && clearance >=70.0)
                  System.out.println("Mild kidney damage");
                if(clearance >= 30.0 && clearance < 60.0)
                  System.out.println("Moderate kidney damage");
                if(clearance >= 15.0 && clearance < 30.0)
                  System.out.println("Severe kidney damage");
                if(clearance < 15.0)
                  System.out.println("Kidney failure");
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
                if(bmi < 16.0)
                  System.out.println("Severe thinnes");
                if(bmi >= 16.0 && bmi < 17.0)
                  System.out.println("Moderate thinnes");
                if(bmi >= 17.0 && bmi < 18.5)
                  System.out.println("Acceptable thinnes");
                if(bmi >= 18.5 && bmi < 25.0)
                  System.out.println("Normal weight");
                if(bmi >= 25.0 && bmi < 30.0)
                  System.out.println("Overweight");
                if(bmi >= 30.0 && bmi < 35.0)
                  System.out.println("Obesity type I");
                if(bmi >= 35.0 && bmi < 40.0)
                  System.out.println("Obesity type II");
                if(bmi >= 40.0)
                  System.out.println("Obesity type III");
        break;
        default: break;
      }
    } catch(Exception e){}
    sc.close();
  }
}
