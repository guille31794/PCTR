/*
* @author Guillermo Girón
* Practice 2: Exercise 2
*/

public class Usa_tareaRunnable
{
    public static void main(String[] args)
      throws Exception
    {
      tareaRunnable  h1 = new tareaRunnable(true); //ambos hilos comparten el acceso a p
      tareaRunnable  h2 = new tareaRunnable(false); // a traves de la referencia
      Thread t1 = new Thread(h1); 
      Thread t2 = new Thread(h2);
      t1.start();
      t2.start();
      t1.join(); t2.join();
      System.out.println(tareaRunnable.n);
    }
}