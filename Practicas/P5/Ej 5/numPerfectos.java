import java.util.concurrent.Callable;
import java.util.ArrayList;

/*
* @author Guillermo Girón García
* @versio 1.0
* Práctica 5: Ejercicio 5
*/

import java.util.concurrent.*;

public class numPerfectos implements Callable
{
    private int start, end;

    public numPerfectos(int s, int e)
    {
        start = s;
        end = e;
    }

    @Override
    public Integer call() throws Exception 
    {   
        int cont = 0, 
        sum,
        j;

        for(int i = start; i <= end; ++i)
        {
            j = 1;
            sum = 0;
            do
            {
                if(i % j == 0)   
                    sum += j;

                if(sum == i)
                    ++cont;
                
                ++j;
            }
            while((i/2) >= j);            
        }

        return cont;
    }

    public static void main(String[] args) throws InterruptedException
    {
        int ini = Integer.parseInt(args[0]), 
        fin = Integer.parseInt(args[1]),
        nThreads = 2, 
        frame = (fin - ini) / nThreads, 
        s = ini, 
        e = frame;
        Integer perfectos = 0;
        ArrayList<Future<Integer>> arL = new ArrayList<Future<Integer>>();      

        ThreadPoolExecutor ex = 
        (ThreadPoolExecutor)Executors.newFixedThreadPool(nThreads);

        for(int i = 0; i != nThreads; ++i)
        {
            arL.add(ex.submit(new numPerfectos(s, e)));
            s = e + 1;
            e += frame;
        }

        for(Future<Integer> it : arL)
            try
            {
                perfectos += it.get();
            } catch(CancellationException c){}
            catch(ExecutionException ee){}
            catch(InterruptedException ie){}

        ex.shutdown();
        ex.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println(perfectos.toString() + " encontrados");
    }
}