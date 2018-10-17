/**
* @author Guillermo Girón García
* @version 1.0
* Clase que simula una condicion de concurso sobre un numero n
*/

public class usaHilo
{
  public static void main(String[] args)
  {
    for(int i = 0; i < 500; ++i)
    {
      Hilo h1 = new Hilo(i);
      Hilo h2 = new Hilo(i+1);
      h1.start();
      h2.start();
      System.out.println(Hilo.n);
    }

  }
}
