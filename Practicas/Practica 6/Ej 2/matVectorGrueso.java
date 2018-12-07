
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 *
 * @author Cristóbal José Velo Huerta
 */
public class matVectorGrueso implements Runnable {

    static Scanner S;
    private int suma;
    private static int[][] matriz;
    private static int[] vector;
    private static int[] vector_r;
    private static int TAM;
    private static int nucleos;
    private int inicio, fin;

    public matVectorGrueso(int inicio, int fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    public void run() {
        suma = 0;
        for (int i = inicio; i < fin; ++i) {
            for (int j = 0; j < vector.length; ++j) {
                suma += matriz[i][j] * vector[j];
            }
            vector_r[i] = suma;
        }

    }

    public static void main(String[] args) throws Exception {
        Random r;
        Runtime runtime = Runtime.getRuntime();
        nucleos = runtime.availableProcessors();
        int fil = 0, col = 0;
        S = new Scanner(System.in);
        r = new Random();

        //System.out.println("Introduce el tamaño de la matriz: ");
        TAM = 20000;//S.nextInt();
        fil = TAM;
        col = TAM;
        matriz = new int[fil][col];
        vector = new int[col];
        vector_r = new int[fil];

        //System.out.println("Inicializando Matriz: ");
        for (int i = 0; i < fil; ++i) {
            for (int j = 0; j < col; ++j) {
                matriz[i][j] = r.nextInt(10);
            }

        }
        //System.out.println("MATRIZ INICIALIZADA: ");

        //System.out.println("Inicializando Vector: ");
        for (int i = 0; i < col; ++i) {
            vector[i] = r.nextInt(10);
        }
        //System.out.println("VECTOR INICIALIZADO: ");

        int inicio = 0;//La fila con la que comienza un hilo
        int rango = fil / nucleos;//La cantidad de filas que recorre un hilo
        int fin = rango;//Hasta que fila recorre

        /*matVectorGrueso[] elem = new matVectorGrueso[nucleos];
        Thread[] hilos = new Thread[nucleos];

        for(int i=0;i<nucleos-1;++i){ //Se lanzan todos los hilos menos el ultimo
            elem[i] = new matVectorGrueso(inicio, fin);
            inicio=fin;
            fin+=rango;
            hilos[i]=new Thread(elem[i]);
            hilos[i].start();
        }

        elem[nucleos-1]=new matVectorGrueso(inicio, nucleos);
        hilos[nucleos-1]=new Thread(elem[nucleos-1]);
        hilos[nucleos-1].start(); //lanzamos el ultimo hilo. Este se ejecuta hasta el final de la matriz

        try{
            for(int i=0;i<nucleos;++i){
                hilos[i].join();
            }
        }catch(InterruptedException e){}*/

        ExecutorService e = Executors.newFixedThreadPool(nucleos);
        double tiempo = System.nanoTime();
        for(int i = 0; i < nucleos; ++i)
        {
            e.execute(new matVectorGrueso(inicio, fin));
            inicio = fin+1;
            fin += rango;
        }

        e.shutdown();
        if(!e.awaitTermination(2500,TimeUnit.MILLISECONDS))//while(!ept.isTerminated())  {}
            System.err.println("Threads didn't finish in 2.5 second!");

        tiempo=(System.nanoTime() - tiempo)/10e9;

        System.out.println("FIN tiempo: "+tiempo);

    }

}
