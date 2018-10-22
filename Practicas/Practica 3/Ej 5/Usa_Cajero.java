/**
* @author Guillermo Girón García
* @version 1.0
* Clase que crea una situación de concurso en un cajero automatico
* (algo indeseable y catastrófico salvo que amanezcas con dinero extra)
*/

public class Usa_Cajero implements Runnable
{
  Cajero c;

  @Override
  public void run()
  {
    if( c.op % 2 == 0)
      Cajero.account.Deposito(20.0);
    else
      Cajero.account.Reintegro(20.0);
  }

  public Usa_Cajero(int op)
  {
    c = new Cajero(op);
  }

  public static void main(String[] args)
  {

    Runnable process1 = new Usa_Cajero(2);
    Runnable process2 = new Usa_Cajero(1);
    Runnable process3 = new Usa_Cajero(1);
    Runnable process4 = new Usa_Cajero(2);

    for(int i = 0; i < 10000; ++i)
    {
      new Thread(process1).start();
      new Thread(process2).start();
      new Thread(process3).start();
      new Thread(process4).start();
    }

    System.out.println(Cajero.account.Saldo());
  }
}
