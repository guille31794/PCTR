/**
* @author Guillermo Girón García
* @version 1.0
* Class that implements a RMI server with a few options to administrate a DB
*/

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.net.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

public class sLibros extends UnicastRemoteObject implements iLibros
{
  List<Libro> DB = new ArrayList<Libro>();

  public sLibros()  { System.out.println("Library created."); }
  public sLibros(Libro book)
  {
    System.out.println("Library created.");
    DB.add(book);
    System.out.println("Book loaded: " + book.toString());
  }
  public sLibros(List<Libro> DB)
  {
    System.out.println("Library created.");
    this.DB = DB;
    System.out.println("Library loaded");
  }

  private void menu(int index)
  {
    int option;
    Scanner sc = new Scanner(System.in);
    
  }

  public void queryAll() throws RemoteException
  {
    System.out.println("Book collection available is: ");
    for(Libro i:DB)
      System.out.prinln(i.toString());
  }

  public void query(Libro book) throws RemoteException
  {
    int index;
    if(DB.contains(book))
    {
      index = DB.indexOf(book);
      menu(index);
    }
    else
      System.out.println("This book doesn't exist yet. Consider to upload it");
  }

  public void upload(Libro book) throws RemoteException
  {
    if(!DB.contains(book))
    {
      DB.add(book);
      System.out.println("Book uploaded!");
    }
  }

  public static void main(String[] args) throws Exception
  {
    iLibros server = new sLibros();
    Naming.bind("server", server);
    System.out.println("Remote server ready");
  }
}
