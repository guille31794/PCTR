
public class aCelularParalelo extends Thread
{
  private final int cellN;
  private final int n;
  private final int init;
  private final int offset;
  public static int generations; // La variable para de ser privada y de objeto a publica y estatica
                                 // Es decir, compartida entre todos los hilos o hebras creados
  
  private static int[] primitive; // Variable estatica, tiene to la pinta de ser un recurso común
  private static int[] evolved; // Variable estatica, tiene to la pinta de ser un recurso común

  public aCelularParalelo(int n, int size, int init, int offset) // El offset se aplica sobre el rango del bucle que va a hacer cada hilo
  {
    cellN = size;
    this.n = n;
    primitive = new int[cellN];
    evolved = new int[cellN];
    
    this.init = init;
    this.offset = offset;

    for(int i = 0; i < cellN; ++i)
      primitive[i] = (int)(Math.random()*100);
  }

  public void evolution()
  {
    for(int j = this.init; j < this.offset; ++j) // Cada hebra solo hacer su parte del trabajo sobre un recurso comun
    {
        for(int i = 1; i < cellN-1; ++i)
            evolved[i] = (primitive[i-1]+primitive[i]+primitive[i+1]) % n;
        primitive = evolved;
    }
  }

  @Override
  public void run()
  {
    evolution();
  }

  public static void main(String[] args) throws Exception // Asi se pone para cualquier excepcion
  {
    aCelularParalelo.generations = Integer.parseInt(args[2]);
    
    int numeroTotalHebras = 3, init = 0, offset = 0; // Mirese el metodo evolution para entender este init y offset
    
    aCelularParalelo[] arrayHebras = new aCelularParalelo[numeroTotalHebras]; // Array de hebras *_* <3
    
    for (int i = 0; i < numeroTotalHebras - 1; i++) // Iteraciones hasta 1 menos, por eso el (-1).....
    {
        init = offset;
        offset += aCelularParalelo.generations / numeroTotalHebras;
        
        // Una cosa es crear las hebras, y otra cosa distintas es hacer que empiecen a hacer su trabajo.
        arrayHebras[i] =  new aCelularParalelo(Integer.parseInt(args[0]),
                                                   Integer.parseInt(args[1]),
                                                   init, offset);
    }
    
    // El (-1) que faltaba....
    arrayHebras[numeroTotalHebras - 1] = new aCelularParalelo(Integer.parseInt(args[0]),
                                                              Integer.parseInt(args[1]),
                                                              init, aCelularParalelo.generations - offset);
    
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
