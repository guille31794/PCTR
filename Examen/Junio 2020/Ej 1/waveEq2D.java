import java.util.concurrent.*;
import java.util.Scanner;
import java.util.Random;

public class waveEq2D implements Runnable
{
    private static int m, n, t; 
    private final int start, end;
    private static float c;
    private static int[][] A;
    private static boolean manual;

    public waveEq2D(int s, int e)
    {
        start = s;
        end = e;
    }

    public static void randomIni()
    {
        manual = false;
        c = 0.25f;
        m = n = 1000;
        A = new int[m][n];
        Random r = new Random();

        for(int i = 0; i < m; ++i)
            for(int j = 0; j < n; ++j)
                A[i][j] = r.nextInt(100) - r.nextInt(100);
    }

    public static void manualIni()
    {
        manual = true;
        c = 0.25f;
        System.out.println("Introduzca numero de filas: ");
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        System.out.println("Introduzca numero de columnas: ");
        n = sc.nextInt();
        A = new int[m][n];

        System.out.println("Introduzca datos: ");

        for(int i = 0; i < m; ++i)
            for(int j = 0; j < n; ++j)
                A[i][j] = sc.nextInt();

        sc.close();
    }

    public static int getSize()
    {
        return m;
    }

    public static void set_t(int t_)
    {
        t = t_;
    }

    public void print_status()
    {
        for(int i = 0; i < m; ++i)
        {
            for(int j = 0 ; j < n; ++j)
            {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void run() 
    {
        for(int k = 0; k < t; ++k)
        {
            for(int i = start; i < end; ++i)
                for(int j = 0; j < n; ++j)
                {
                    if(i - 1 < 0 && j - 1 >= 0 && j + 1 < n)
                    {
                        A[i][j] = Math.round((c*c) * (A[i+1][j] + 
                        0 + A[i][j+1] + A[i][j-1] - 4 * A[i][j]) + 
                        (2 * A[i][j]));
                    }
                    else if(i + 1 >= m  && j - 1 >= 0 && j + 1 < n)
                    {
                        A[i][j] = Math.round((c*c) * (0 + 
                        A[i-1][j] + A[i][j+1] + A[i][j-1] - 4 * A[i][j]) + 
                        (2 * A[i][j]));
                    }
                    else if(i - 1 >= 0 && i + 1 < m && j - 1 < 0)
                    {
                        A[i][j] = Math.round((c*c) * (A[i+1][j] + 
                        A[i-1][j] + A[i][j+1] + 0 - 4 * A[i][j]) + 
                        (2 * A[i][j]));
                    }
                    else if(i - 1 >= 0 && i + 1 < m && j + 1 >= n)
                    {
                        A[i][j] = Math.round((c*c) * (A[i+1][j] + 
                        A[i-1][j] + 0 + A[i][j-1] - 4 * A[i][j]) + 
                        (2 * A[i][j]));
                    }
                    else if(i - 1 < 0 && j - 1 < 0)
                    {
                        A[i][j] = Math.round((c*c) * (A[i+1][j] + 
                        0 + A[i][j+1] + 0 - 4 * A[i][j]) + 
                        (2 * A[i][j]));
                    }
                    else if(i - 1 < 0 && j + 1 >= n)
                    {
                        A[i][j] = Math.round((c*c) * (A[i+1][j] + 
                        0 + 0 + A[i][j-1] - 4 * A[i][j]) + 
                        (2 * A[i][j]));
                    }
                    else if(i + 1 >= m && j - 1 < 0)
                    {
                        A[i][j] = Math.round((c*c) * (0 + 
                        A[i - 1][j] + A[i][j+1] + 0 - 4 * A[i][j]) + 
                        (2 * A[i][j]));
                    }
                    else if(i + 1 >= m && j + 1 >= n)
                    {
                        A[i][j] = Math.round((c * c) * (0 + A[i - 1][j] + 0 + A[i][j-1] - 4 * A[i][j]) + (2 * A[i][j]));
                    }
                }

                if(manual)
                    print_status();
            }
    }

    public static void main(String[] args) throws Exception
    {
        int n_threads = Runtime.getRuntime().availableProcessors(); 

        int bound = 0, option = 0;
        double Cb = 0.0;

        ThreadPoolExecutor tpe =
        (ThreadPoolExecutor)Executors.newFixedThreadPool(n_threads);

        System.out.print("Menu:\n1.- Random.\n2.- Manual.\nSelect: ");
        Scanner sc = new Scanner(System.in);
        option = sc.nextInt();

        System.out.print("Introduzca periodo: ");
        int t = sc.nextInt();
        set_t(t);

        sc.close();

        long t_secuencial = 0;

        switch(option)
        {
            case 1: randomIni();
                    long t_start = System.currentTimeMillis();
                    Thread h = new Thread(new waveEq2D(0, getSize()));
                    h.run();
                    h.join();
                    t_secuencial = System.currentTimeMillis() - t_start;
            break;
            case 2: manualIni();
            break;
            default:
            System.out.println("Opción incorrecta. Saliendo.");
            System.exit(-1);
        }

        int start = 0, frame = getSize() / n_threads, end = frame;

        long startTime = System.currentTimeMillis();

        for(int i = 0; i < n_threads; ++i)
        {
            tpe.execute(new waveEq2D(start, end));
            start = end;
            end += frame;
        }

        tpe.execute(new waveEq2D(start, getSize()));

        tpe.shutdown();

        while(!tpe.isShutdown())
        {
            tpe.awaitTermination(10, TimeUnit.SECONDS);
        }

        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("El tiempo total de ejecución fue: " + totalTime + " s.");
        
        if(option == 1)
        {
            System.out.println("El tiempo secuencial de ejecución fue: " + t_secuencial + " s.");
            float speedUp = t_secuencial / totalTime;
            System.out.println("El speedUp ha sido de: " + speedUp);
        }
    }    
}