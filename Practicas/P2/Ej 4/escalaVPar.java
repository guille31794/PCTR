import java.util.Random;
import java.util.Scanner;

public class escalaVPar extends Thread
{
    private static int v[] = new int[100000000];
    private int start, end, k;
    escalaVPar(int s, int e, int k)
    {
        start = s;
        end = e;
        this.k = k;
    }

    @Override
    public void run() 
    {
        for(int i = start; i < end; ++i)
            v[i] *= k;
    }

    public static void main(String[] args) throws InterruptedException
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca factor de escala: ");
        int k = sc.nextInt();
        int start = 0,
        works = 100000000/4,
        end = works;
        for(int i = 0; i < 4; ++i)
        {   
            escalaVPar h1 = new escalaVPar(start, end,k);
            start = end + 1;
            end += works;
            h1.start();
            h1.join(); 
        }
    }
}