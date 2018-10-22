
public class aCelularParalelo extends Thread
{
  public static int cellN;
  private final int init;
  private final int offset;
  public static int generations; // La variable para de ser privada y de objeto a publica y estatica
                                 // Es decir, compartida entre todos los hilos o hebras creados

  private static int[] primitive; // Variable estatica, tiene to la pinta de ser un recurso común
  private static int[] evolved; // Variable estatica, tiene to la pinta de ser un recurso común

  public aCelularParalelo(int init, int offset) // El offset se aplica sobre el rango del bucle que va a hacer cada hilo
  {
    this.init = init;
    this.offset = offset;
  }

  public void evolution()
  {
    // Cada hebra solo hacer su parte del trabajo sobre un recurso comun
    for(int j = this.init; j < this.offset; ++j)
    {
      evolved[j] = (primitive[j-1]+primitive[j]+primitive[j+1]) % n;
      primitive[j] = evolved[j];      
    }
  }

  @Override
  public void run()
  {
    evolution();
  }

  public static void main(String[] args) throws Exception // Asi se pone para cualquier excepcion
  {
    //Inicializo el automata
    cellN = 10000000;
    generations = 10;
    primitive = new int[cellN];
    evolved = new int[cellN];
    for(int i = 0; i < cellN; ++i)
      primitive[i] = Math.random()*100;

    // Mirese el metodo evolution para entender este init y offset
    int numeroTotalHebras = 4, init = 0, offset = 0;
    // Array de hebras *_* <3
    aCelularParalelo[] arrayHebras = new aCelularParalelo[numeroTotalHebras];
    // Iteraciones hasta 1 menos, por eso el (-1).....
    for (int i = 0; i < numeroTotalHebras - 1; i++)
    {
        init = offset;
        offset += aCelularParalelo.cellN / numeroTotalHebras;

        // Una cosa es crear las hebras, y otra cosa distintas es hacer que empiecen a hacer su trabajo.
        arrayHebras[i] =  new aCelularParalelo(init, offset);
    }

    // El (-1) que faltaba....
    arrayHebras[numeroTotalHebras - 1] = new aCelularParalelo(init,
    aCelularParalelo.generations - offset);

    for (int i = 0; i < numeroTotalHebras; i++)
    {
        // Todas las hebras empiezan a trabajar.
        arrayHebras[i].start();
    }

    for (int i = 0; i < numeroTotalHebras; i++)
    {
        // Si te consideras un ser especial y lleno de luz
        // Puedes forzar a que todas las hebras empiecen a trabajar cuando tu quieras
        // No cuando java lo diga
        arrayHebras[i].join();
    }
  }
}
