/*
* @author Guillermo Girón García
* @version 1.0
* Class that manage drivers
*/

import java.util.ArrayList;

public class usaConductor
{
  protected static ArrayList<Conductor> drivers = new ArrayList<Conductor>();

  public static void main(String[] args)
  {
    Thread t;
    Conductor Pepe = new Conductor("Pepe", "Viyuela", "12345R", "su casa",
    "123CA", 956258843, 42);
    drivers.add(Pepe);
    Conductor Guille = new Conductor("Guille", "Lopez", "09876A", "Janluca",
    "456CA", 856172415, 21);
    drivers.add(Guille);
    Conductor Lalo = new Conductor("Lalo", "Ulibarri", "13579B", "La Isla",
    "789CA", 956285971, 23);
    drivers.add(Lalo);
  
      for(Conductor driver:drivers)
        {t = new Thread(driver); t.start();}
  }
}
