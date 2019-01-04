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
import java.util.*;

public class sLibros extends UnicastRemoteObject implements iLibros
{
  private List<Libro> DB = new ArrayList<Libro>();

  public sLibros() throws RemoteException
  { System.out.println("Library created."); }
  public sLibros(Libro book) throws RemoteException
  {
    System.out.println("Library created.");
    DB.add(book);
    System.out.println("Book loaded: " + book.toString());
  }
  public sLibros(List<Libro> DB)  throws RemoteException
  {
    System.out.println("Library created.");
    this.DB = DB;
    System.out.println("Library loaded");
  }

  private void menu(int index)
  {
    int option;
    Scanner sc = new Scanner(System.in);
    System.out.println("Select Option: ");
    System.out.println("1.- Get Ttitle.\n" + "2.- Get author" + "3.- Set Title\n"
    + "4.- Set Author\n" + "5.- Get downloads\n" + "6.- Download book\n" +
    "Other.- Exit");
    option = sc.nextInt();
    sc.nextLine();
    switch(option)
    {
      case 1: System.out.println(DB.get(index).getTitle()); break;
      case 2: System.out.println(DB.get(index).getAuthor()); break;
      case 3: DB.get(index).setTitle(sc.nextLine()); break;
      case 4: DB.get(index).setAuthor(sc.nextLine()); break;
      case 5: System.out.println(DB.get(index).getDownloads() + "downloads"); break;
      case 6: System.out.println("Downlading.."); DB.get(index).download(); break;
      default:  break;
    }
    sc.close();
  }

  public void queryAll() throws RemoteException
  {
    System.out.println("Book collection available is: ");
    for(Libro i:DB)
      System.out.println(i.toString());
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
