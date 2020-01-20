/**
 * @author Guillermo Girón García
 * @version 1.0
 * Class that secuentially implements a matrix convolution
 */

 import java.util.Random;
 import java.util.Scanner;

 public class conVolSecuencial
 {
     public static int[][] matrix = new int[10000][10000];
     
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
        long iniTime = System.currentTimeMillis(), totalTime;
        for(int i = 1; i < 9998; ++i)
            for(int j = 1; j < 9998; ++j)
            {
                int[][] neighborhood = new int[][]{{matrix[i-1][j-1], matrix[i-1][j], matrix[i-1][j+1]},
                {matrix[i][j-1], matrix[i][j], matrix[i][j+1]}, {matrix[i+1][j-1], matrix[i+1][j], matrix[i+1][j+1]}};
                matrix[i][j] = prod(neighborhood, con);
            }
        
        totalTime = (System.currentTimeMillis() - iniTime)/1000;
        System.out.println("Needed time was: " +  totalTime + "s");
     }

     public static void menu()
     {
         int option = -1;
         Scanner sc = new Scanner(System.in);
         while(option < 0 || option > 5)
         {
            System.out.println("Menu:\n1.- Focus\n2.- Highlight edges\n3.- Detect edges\n4.- Sobel filter");
            System.out.println("5.- Sharpen filter\n0.- Exit\nSelect option: ");
            option = sc.nextInt();
         } 
         
         switch(option)
         {
             case 0: System.out.println("Bye!"); 
                break;
             case 1: int[][] focus = new int[][]{{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}};
                convolution(focus);
                break;
             case 2: int[][] highlight = new int[][]{{0,0,0}, {-1,1,0}, {0,0,0}};
                convolution(highlight);
                break;
             case 3: int[][] detect = new int[][]{{0,1,0}, {1,-4,1}, {0,1,0}};
                convolution(detect);
                break;
             case 4: int[][] Sobel = new int[][]{{-1, 0, 1}, {-2,0,2}, {-1,0,1}};
                convolution(Sobel);
                break;
             case 5: int[][] sharpen = new int[][]{{1,-2,1}, {-2,5,-2}, {1,-2,1}};
                convolution(sharpen);
                break;
         }
     }

     public static void main(String[] args) 
     {
       fill();
       menu();  
     }
 }