import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class ficheroseguro implements Runnable
{
    static Scanner sc = new Scanner(System.in);
    static RandomAccessFile fichero = null;
    private Random gcl;

    public ficheroseguro()
    {
        gcl = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() 
    {
        try 
        {
            for(int i=1; i<=10; i++)
              synchronized(fichero)
              {
                  fichero.writeInt(gcl.nextInt());
              }
        } catch (FileNotFoundException ex) 
        {
            System.out.println(ex.getMessage());
        } 
        catch (IOException ex) 
        {
            System.out.println(ex.getMessage());
        } 
        finally 
        {
            try 
            {
                if (fichero != null) 
                    fichero.close();
            } 
            catch (IOException e) 
            {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) 
    {
        try
        {
            fichero = new RandomAccessFile("enteros.dat", "rw");
        }
        catch(FileNotFoundException e)
        {
            System.out.println(ex.getMessage());
        }

        int nThreads = 2;
        ThreadPoolExecutor ex = 
        (ThreadPoolExecutor)Executors.newFixedThreadPool(nThreads);

        for(int i = 0; i < nThreads; ++i)
        {
            ex.submit(new ficheroseguro());
        }

        ex.shutdown();
        ex.awaitTermination(10, TimeUnit.SECONDS);
    }
}