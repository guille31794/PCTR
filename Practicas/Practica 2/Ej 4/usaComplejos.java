/**
* @author Guillermo Girón García
* @version 1.0
* Clase que realiza operaciones con numeros Complejoss
*/

import java.util.Scanner;

public class usaComplejos
{
  public static void main(String[] args)
  {
    System.out.println("Menu: ");
    System.out.println("1.- Sum");
    System.out.println("2.- Substraction");
    System.out.println("3.- Prod");
    System.out.println("4.- Module");
    System.out.println("5.- Quotient");
    int option;
    Complejos C1, C2, C3;
    try(Scanner sc = new Scanner(System.in);)
    {
        option = sc.nextInt();
        sc.nextLine();
    }
    double rP = 0, iP = 0;
    switch(option)
    {
      case 1: System.out.println("Insert real part: ");
      try(Scanner sc = new Scanner(System.in);)
      {
        if(sc.hasNextDouble())
          rP = sc.nextDouble();
      }
      System.out.println("Insert imaginary part:");
      try(Scanner sc = new Scanner(System.in);)
      {
        if(sc.hasNextDouble())
          iP = sc.nextDouble();
      }
      C1 = new Complejos(rP, iP);
      System.out.println("Insert real part: ");
      try(Scanner sc = new Scanner(System.in);)
      {
        if(sc.hasNextDouble())
          rP = sc.nextDouble();
      }
      System.out.println("Insert imaginary part:");
      try(Scanner sc = new Scanner(System.in);)
      {
        if(sc.hasNextDouble())
          iP = sc.nextDouble();
      }
      C2 = new Complejos(rP, iP);
      C3 = C1.Sum(C2);
      System.out.println(C1.getReal()+"+("+C1.getImaginary()+")i + "+
      C2.getReal()+"+("+C2.getImaginary()+")i = "+C3.getReal()+"+("+
      C3.getImaginary()+")i" );
      break;
      case 2: System.out.println("Insert real part: ");
      try(Scanner sc = new Scanner(System.in);)
      {
        rP = sc.nextDouble();
      }
      System.out.println("Insert imaginary part:");
      try(Scanner sc = new Scanner(System.in);)
      {
        iP = sc.nextDouble();
      }
      C1 = new Complejos(rP, iP);
      System.out.println("Insert real part: ");
      try(Scanner sc = new Scanner(System.in);)
      {
        rP = sc.nextDouble();
      }
      System.out.println("Insert imaginary part:");
      try(Scanner sc = new Scanner(System.in);)
      {
        iP = sc.nextDouble();
      }
      C2 = new Complejos(rP, iP);
      C3 = C1.Subs(C2);
      System.out.println(C1.getReal()+"+("+C1.getImaginary()+")i - "+
      C2.getReal()+"+("+C2.getImaginary()+")i = "+C3.getReal()+"+("+
      C3.getImaginary()+")i" );
      break;
      case 3: System.out.println("Insert real part: ");
      try(Scanner sc = new Scanner(System.in);)
      {
        rP = sc.nextDouble();
      }
      System.out.println("Insert imaginary part:");
      try(Scanner sc = new Scanner(System.in);)
      {
        iP = sc.nextDouble();
      }
      C1 = new Complejos(rP, iP);
      System.out.println("Insert real part: ");
      try(Scanner sc = new Scanner(System.in);)
      {
        rP = sc.nextDouble();
      }
      System.out.println("Insert imaginary part:");
      try(Scanner sc = new Scanner(System.in);)
      {
        iP = sc.nextDouble();
      }
      C2 = new Complejos(rP, iP);
      C3 = C1.Prod(C2);
      System.out.println(C1.getReal()+"+("+C1.getImaginary()+")i * "+
      C2.getReal()+"+("+C2.getImaginary()+")i = "+C3.getReal()+"+("+
      C3.getImaginary()+")i" );
      break;
      case 4: System.out.println("Insert real part: ");
      try(Scanner sc = new Scanner(System.in);)
      {
        rP = sc.nextDouble();
      }
      System.out.println("Insert imaginary part:");
      try(Scanner sc = new Scanner(System.in);)
      {
        iP = sc.nextDouble();
      }
      C1 = new Complejos(rP, iP);
      double mod = C1.Mod();
      System.out.println('|'+C1.getReal()+"+("+C1.getImaginary()+")i| = " +
      mod);
      break;
      case 5: System.out.println("Insert real part: ");
      try(Scanner sc = new Scanner(System.in);)
      {
        rP = sc.nextDouble();
      }
      System.out.println("Insert imaginary part:");
      try(Scanner sc = new Scanner(System.in);)
      {
        iP = sc.nextDouble();
      }
      C1 = new Complejos(rP, iP);
      System.out.println("Insert real part: ");
      try(Scanner sc = new Scanner(System.in);)
      {
        rP = sc.nextDouble();
      }
      System.out.println("Insert imaginary part:");
      try(Scanner sc = new Scanner(System.in);)
      {
        iP = sc.nextDouble();
      }
      C2 = new Complejos(rP, iP);
      C3 = C1.Quotient(C2);
      System.out.println(C1.getReal()+"+("+C1.getImaginary()+")i / "+
      C2.getReal()+"+("+C2.getImaginary()+")i = "+C3.getReal()+"+("+
      C3.getImaginary()+")i" );
      break;
      default: break;
    }
  }
}
