/**
 * @author Guillermo Girón García
 * @version 1.0
 * Class that concurrently implements a matrix convolution
 */

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

public class conVolParalelo implements Runnable
{
    // Matrix declarations
    public static int[][] matrix = new int[10000][10000];
    // & initializations
    public static final int[][] focus = new int[][]{{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}};
    public static final int[][] highlight = new int[][]{{0,0,0}, {-1,1,0}, {0,0,0}};
    public static final int[][] detect = new int[][]{{0,1,0}, {1,-4,1}, {0,1,0}};
    public static final int[][] Sobel = new int[][]{{-1, 0, 1}, {-2,0,2}, {-1,0,1}};
    public static final int[][] sharpen = new int[][]{{1,-2,1}, {-2,5,-2}, {1,-2,1}};

    private int option;
    //private int[][]

    public conVolParalelo(int option, int start, int end)
    {
        this.option = option;
        for(; start < end; ++start)
           for(int j = 1; j < 9998; ++j)
           {
               int[][] neighborhood = new int[][]{{matrix[i-1][j-1], matrix[i-1][j], matrix[i-1][j+1]},
               {matrix[i][j-1], matrix[i][j], matrix[i][j+1]}, {matrix[i+1][j-1], matrix[i+1][j], matrix[i+1][j+1]}};
               
           }
    }

    public static void fill()
    {
        Random r = new Random();

        for(int i = 0; i < 9999; ++i)
           for(int j = 0; j < 9999; ++j)
               matrix[i][j] = r.nextInt(40)-20;
    }

    public static int prod(int[][] n, int[][] con)
    {
       int[] res = new int[]{0,0,0}; 
       for(int i = 0; i < 3; ++i)
           for(int j = 0; j < 3; ++j)
               res[i] += n[i][j]*con[j][i];
       
       int finalRes = 0;        
       for(int i = 0; i < 3; ++i)
           finalRes += res[i];
        
       return finalRes;
    }

    public static void convolution(int[][] con)
    {
        matrix[i][j] = prod(neighborhood, con);
    }

    @Override
    public void run() 
    {
        switch(this.option)
        {
            case 1: 
               convolution(focus);
               break;
            case 2: 
               convolution(highlight);
               break;
            case 3: 
               convolution(detect);
               break;
            case 4: 
               convolution(Sobel);
               break;
            case 5: 
               convolution(sharpen);
               break;
        }
    }

    public static void main(String[] args) 
    {
        fill();

        //Concurrent stuff
        int threads = Integer.parseInt(args);
        int works = 9998/threads;
        ThreadPoolExecutor tpe = (ThreadPoolExecutor)Executors.newFixedThreadPool(threads);
        
        //Menu
        int option = -1;
        Scanner sc = new Scanner(System.in);
        while(option < 0 || option > 5)
        {
           System.out.println("Menu:\n1.- Focus\n2.- Highlight edges\n3.- Detect edges\n4.- Sobel filter");
           System.out.println("5.- Sharpen filter\nSelect option: ");
           option = sc.nextInt();
        } 

        //Algorithm
        long iniTime = System.currentTimeMillis(), totalTime;
        for(int i = 0; i < threads; ++i)
        {
            tpe.execute(new conVolParalelo(option, start, end));
        }
        tpe.shutdown();
        tpe.awaitTermination(10, TimeUnit.SECONDS);
        totalTime = (System.currentTimeMillis() - iniTime)/1000;
        System.out.println("Needed time was: " +  totalTime + "s");
    }
}