/*
* @author Guillermo Girón García
* Práctica 10: Ejercicio 1 resuelto con implementación
* paso de mensajes
*/

import mpi.*;

public class primosMPJ 
{
    protected static boolean esPrimo(int n)
    {
        if(n <= 1)
            return false;

        int raizN = (int)Math.sqrt(n);

        for(int i = 2; i <= raizN; ++i)
            if(n % i == 0)
                return false;

        return true;
    }

    public static void main(String[] args) throws MPIException
    {
        // Inicialización y parametros necesarios
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank(),
        size = MPI.COMM_WORLD.Size(),
        tag = 100,
        // Se puede eliminar
        emisor = 0,
        // División inicial de las tareas
        np = Integer.parseInt(args[0]),
        frame = (int)Math.pow(10.0, 8.0) / np,
        primos[] = new int[np];        

        long t,tIni = System.currentTimeMillis();
        // Un único proceso para controlarlos a todos
        if(rank == emisor)
        {
            int start[] = new int[1],
            nPrimos = 0;
            start[0] = 2;
            

            for(int i = 1; i != np+1; ++i)
            {
                MPI.COMM_WORLD.Ssend(start, 0, 1, MPI.INT, i, tag);
                start[0] += frame + 1;
            }

            for(int i = 1; i != np + 1; ++i)
            {
                MPI.COMM_WORLD.Recv(primos, i-1, 1, MPI.INT, i, tag);
                nPrimos += primos[i-1];
            }

            t = System.currentTimeMillis() - tIni;
            System.out.println(nPrimos + "primos encontrados en " + 
            t/1000 + "s.");
        }
        // Procesos slaves
        else
        {
            int ini[] = new int[1],
            limit;

            MPI.COMM_WORLD.Recv(ini, 0, 1, MPI.INT, emisor, tag);

            limit = ini[0] + frame;

            for(int i = ini[0]; i != limit; ++i)
            {
                if(esPrimo(i))
                    ++primos[0];
            }

            MPI.COMM_WORLD.Send(primos, 0, 1, MPI.INT, emisor, tag);
        }

        MPI.Finalize();
    }
}