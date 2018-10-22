/**
* @author Guillermo Girón García
* @version 1.0
* Clase que simula un cajero automatico
*/

public class Cajero
{
  public int op;
  public static Cuenta_Banca account = new Cuenta_Banca(1, 3500);

  public Cajero(int id)
  {
    op = id;
  }
}
