/**
* @author Guillermo Girón García
* @version 1.0
* Clase que simula una condicion de concurso sobre un numero n
*/

class Hilo extends Thread
{
  public static int n = 0;
  private int id;

  public Hilo(int i) {id = i;}

  public void subs() {  --n;}
  public void add() {++n;}
  @Override
  public void run()
  {
    if (id % 2 == 0)
      add();
    else
      subs();

    //System.out.println("Hi. I'm thread " + id);
  }
}
