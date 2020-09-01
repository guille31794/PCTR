import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.Scanner;
import java.util.Vector;

public class intRectangulo implements Callable
{
    private float a, b;

    public intRectangulo(float a, float b)
    {
        this.a = a;
        this.b = b;
    }

    @Override
    public Float call()
    {
        return (b-a)*((3*a*a) + 2);
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int nThreads = Runtime.getRuntime().availableProcessors();

        System.out.println("Introduzca a y b: ");
        int a = sc.nextInt(), b = sc.nextInt();
        sc.close();

        if(nThreads%2 != 0)
            ++nThreads;

        float frame = (float)Math.abs(a-b)/nThreads;

        System.out.println(frame);
    
        ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(nThreads);
        Vector<Future<Float>> intervalos = new Vector<Future<Float>>();

        float start = 0f, end = frame;
        long iniTime = System.currentTimeMillis(),
        endTime;

        for(int i = 0; i < nThreads; ++i)
        {
            intervalos.addElement(tpe.submit(new intRectangulo(start, end)));
            start = end;
            end += frame;
        }

        tpe.shutdown();
        while(!tpe.isTerminated());

        float integral = 0;
        try {
            for(int i = 0; i < nThreads; ++i)
            {
                integral += intervalos.elementAt(i).get().floatValue();    
            }
        } catch (InterruptedException e) {}
        catch(ExecutionException e){}
        
        endTime = System.currentTimeMillis() - iniTime;

        System.out.println("El resultado de la aproximación a la integral es: "
        + integral + " y el tiempo necesario para calcularla " + endTime + " ms.");

        /*
            El speedUp no puede ser mayor que uno, ya que una unica llamada
            en secuencial se ejecutará más rapido que varias concurrentes con numeros
            más pequeños
        */
    }
}