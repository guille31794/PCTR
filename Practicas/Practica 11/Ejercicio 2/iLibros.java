/**
* @author Guillermo Girón García
* @version 1.0
* Interface to represents books on RMI DB project
*/

import java.rmi.*;

public interface iLibros extends Remote
{
  public void queryAll() throws RemoteException;
  public void query(Libro book) throws RemoteException;
  public void upload(Libro book) throws RemoteException;
}
