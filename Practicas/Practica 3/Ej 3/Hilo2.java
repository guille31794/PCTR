/**
* @author Guillermo Girón García
* @version 1.0
* Clase que simula una condición de concurso
*/

import java.util.Random;

public class Hilo extends Thread
{
  private int id;

  @Override
  public void run()
  {
    for(int i = 0; i < 10000; ++i)
      Math.random();

    System.out.println("I'm thread: " + id);
  }

  public static void main(String[] args)
  {
    for(int i = 0; i < 10000; ++i)
    {
      Hilo h = new Hilo(i);
      h.start();
    }
  }

	/**
	* Default Hilo constructor
	*/
	public Hilo(int id)
  {
		this.id = id;
	}
}
