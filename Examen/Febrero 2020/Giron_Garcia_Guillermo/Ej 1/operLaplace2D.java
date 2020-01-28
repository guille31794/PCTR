import java.util.concurrent.*;
import java.util.Scanner;
import java.util.Random;

public class operLaplace2D implements Runnable
{
    private int start, end;
    private static int[][] Ret;

    public operLaplace2D(int s, int e)
    {
        start = s;
        end = e;
    }

    @Override
    public void run() 
    {
        
    }

    public static void main(String[] args) 
    {
        int n_threads = 0; 
        if(args[1] != null)
            n_threads = Integer.parseInt(args[1]);
        else
            System.exit(-1);

        int bound = 0, option = 0;
        double Cb = 0.2;

        ThreadPoolExecutor tpe =
        (ThreadPoolExecutor)Executors.newFixedThreadPool(n_threads);

        System.out.print("Menu:\n1.- Random.\n2.- Manual.\nSelect: ");
        Scanner sc = new Scanner(System.in);
        option = sc.nextInt();
        sc.close();

        switch(option)
        {
            case 1: randomIni();
            break;
            case 2: manualIni();
            break;
            default:
            System.out.println("Opción incorrecta. Saliendo.");
            exit(-1);
        }

        long startTime = System.currentTimeMillis();

        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("El tiempo total de ejecución fue: " + totalTime);

        if(option == 2)
            for(int i = 0; i < Ret.length(); ++i)
            {    
                for(int j = 0; j < Ret.length(); ++j)
                    System.out.print(Ret[i][j] + " ");
                
                    System.out.println();
            }
    }
}