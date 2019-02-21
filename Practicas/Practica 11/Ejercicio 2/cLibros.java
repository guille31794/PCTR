/**
* @author Guillermo Girón García
* @version 1.0
* Class that represents a client of the library
*/

import java.rmi.*;

public class cLibros
{
  public static void main(String[] args) throws Exception
  {
    iLibros remoteServer = (iLibros)Naming.lookup("//localhost/server");

  }
}
