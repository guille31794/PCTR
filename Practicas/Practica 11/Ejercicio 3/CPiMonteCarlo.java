/**
* @author Guillermo Girón García
* @version 1.0
* Class that implements a client to solve a part of pi monte carlo aproximation
*/

import java.rmi.*;

public class CPiMonteCarlo
{
  public static void main (String[] args) throws Exception
  {
        iPiMonteCarlo remoteServer = (iPiMonteCarlo) Naming.lookup("//localhost/server");
        remoteServer.morePoints(50000);
        System.out.println("Result => " + remoteServer.getPi());
        remoteServer.reset();
  }
}
