/**
* @author Guillermo Girón García
* @version 1.0
* Class that implements a client to play Bonoloto
*/

import java.rmi.*;
import java.rmi.registry.*;
import java.util.Scanner;

public class cBonoloto
{
  public static void main(String[] args) throws Exception
  {
    Scanner sc = new Scanner(System.in);
    int[] bet = new int[6];

    System.out.println("Set your bet: ");
    for(int i = 0; i < 6; ++i)
      bet[i] = sc.nextInt();

    iBonoLoto remoteServer = (iBonoLoto)Naming.lookup("server");
    if(remoteServer.compApuesta(bet))
      System.out.println("You won! But you still are poor");
    else
      System.out.println("Not prized. Try again.");
  }
}
