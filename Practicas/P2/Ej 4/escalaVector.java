import java.util.Random;
import java.util.Scanner;

public class escalaVector
{
    private static int v[] = new int[100000000];
    escalaVector()
    {
        Random r = new Random();
        for(int i = 0; i < 100000000; ++i)
            v[i] = r.nextInt(10);
    }

    public static void main(String[] args) 
    {
        new escalaVector();
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca factor de escala: ");
        int k = sc.nextInt();

        for(int i = 0; i <100000000; ++i)
            v[i] *= k;
    }
}