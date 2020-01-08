/**
* @author Guillermo Girón García
* @version 1.0
* Class that implements a Bonoloto server with RMI
*/

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.net.*;
import java.util.Random;

// To compile: rmic -vcompat Bonoloto  

public class sBonoloto extends UnicastRemoteObject implements iBonoLoto
{
  private int[] bet = new int[6];

  public void resetServidor() throws RemoteException
  {
    Random r = new Random();
    for(int i = 0; i < 6; ++i)
    {
      bet[i] = r.nextInt(50);
      for(int j = 0; j < i; ++j)
        while(bet[i] == bet[j])
          bet[i] = r.nextInt(50);
    }

    System.out.println("New bet setted");
  }

  public boolean compApuesta(int[] apuesta) throws RemoteException
  {
    boolean win = true;
    int counter = 0;

    for(int i = 0; i < 6; ++i)
      for(int j = 0; j < 6; ++j)
        if(apuesta[i] == bet[j])
          ++counter;

    if(counter < 6)
      win = false;

    return win;
  }

  public sBonoloto() throws RemoteException
  { System.out.println("Starting server"); resetServidor();  }

  public static void main(String[] args) throws Exception
  {
    iBonoLoto server = new sBonoloto();
    Naming.bind("server", server);
    System.out.println("Remote server ready");
  }
}
