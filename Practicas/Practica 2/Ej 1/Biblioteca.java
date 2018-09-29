/**
* @author Guillermo Girón García
* @version 1.0
* @return Clase que simula la consulta de un medico con un conjunto de pacientes
*/

import java.util.ArrayList;
import java.util.Scanner;

public class Biblioteca
{
  /** @param Vector de pacientes */
  private ArrayList<Paciente> colection = new ArrayList<Paciente>();

  /** Builder */
  public Biblioteca() {}

  /** Modifiers */
  public void insert(Paciente p)
  {
    colection.add(p);
  }

  public void delete(Paciente p)
  {
    colection.remove(p);
  }

  /** Consulters */
  public Paciente consultName(String patron)
  {
    if(!colection.isEmpty())
    {
      for (int i = 0; i < colection.size(); ++i)
      {
        if(colection.get(i).getName() == patron)
          return colection.get(i);
      }
    }

    return new Paciente();
  }

  public Paciente consultDni(String patron)
  {
    if(!colection.isEmpty())
    {
      for (int i = 0; i < colection.size(); ++i)
      {
        if(colection.get(i).getDni() == patron)
          return colection.get(i);
      }
    }

    return new Paciente();
  }

  public Paciente consultTele(int patron)
  {
    if(!colection.isEmpty())
    {
      for (int i = 0; i < colection.size(); ++i)
      {
        if(colection.get(i).getTelephoneN() == patron)
          return colection.get(i);
      }
    }

    return new Paciente();
  }

  /** Menus to satisfy options */
  private void menuInsert()
  {
    Paciente p;
    String n, d, a, ic;
    int t;
    Scanner sc = new Scanner(System.in);
    char c;

    System.out.println("Insert patient's name: ");
    n = sc.nextLine();
    System.out.println("Insert IDN: ");
    d = sc.nextLine();
    System.out.println("Insert telephone number: ");
    t = sc.nextInt();
    System.out.println("Insert adress: ");
    a = sc.nextLine();
    System.out.println("Insert insurance company: ");
    ic = sc.nextLine();
    System.out.println("New user: ");
    System.out.println(n + " " + d);
    System.out.println(t + " " + ic);
    System.out.println(a);
    System.out.println("Is correct?");
    c = sc.next().trim().charAt(0);
    if( c == 'S' || c == 's')
    {
      p = new Paciente(n, d, a, t, ic);
      insert(p);
    }

  }

  private void menuErase()
  {

  }

  private void menuQuery()
  {

  }

  public void menu()
  {
    Biblioteca query = new Biblioteca();
    int option;
    Scanner sc = new Scanner(System.in);

    do
    {
      System.out.println("Welcome to your query management");
      System.out.println("Select the option to perform");
      System.out.println("1.- Insert patient");
      System.out.println("2.- Erase patient");
      System.out.println("3.- Consult patient");
      System.out.println("0.- Exit program");
      option = sc.nextInt();

      switch(option)
      {
        case 1: menuInsert();
        break;
        case 2: menuErase();
        break;
        case 3: menuQuery();
        break;
        case 0: System.out.println("Skipping...");
        System.exit(-1);
      }

    } while(option != 0);
  }

  public static void main(String[] args)
  {
    Biblioteca library = new Biblioteca();
    library.menu();
  }

}
