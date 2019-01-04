/**
* @author Guillermo Girón García
* @version 1.0
* Class that implements a server to solve a part of pi monte carlo aproximation
*/

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.net.*;
import java.util.Random;

public class SPiMonteCarlo extends UnicastRemoteObject implements iPiMonteCarlo
{
    private static final long serialVersionUID = 1;
    private int points;

    public SPiMonteCarlo() throws RemoteException
    { points = 1000; }

    public void morePoints(int points) { points += points;  }

    public void reset() { points = 0; }

    public double getPi()
    {
        Random r = new Random();
        int hits = 0;
        for (int i = 0; i < this.points; i++)
        {
            double x = r.nextDouble() * 2 - 1;
            double y = r.nextDouble() * 2 - 1;
            double z = x*x + y*y;

            if(z <= 1)
                hits++;
        }

        return (hits * 4) / points;
    }

    public static void main (String[] args) throws Exception {
        iPiMonteCarlo server = new SPiMonteCarlo();
        Naming.bind("server", server);
        System.out.println("Server ready");
}
}
