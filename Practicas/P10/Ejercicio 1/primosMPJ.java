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
        tag = 100,
        // División inicial de las tareas
        np = Integer.parseInt(args[0]),
        frame = (int)Math.pow(10.0, 8.0) / np,
        primos[] = new int[np],
        start = rank*frame+1;

        if(rank == 0)
        {
            long t,
            tIni = System.currentTimeMillis();
        }

        for(int i = start; i < start+frame; ++i)
            if(esPrimo(i))
                ++primos[rank];

        // Un único proceso para controlarlos a todos
        if(rank == 0)
        {
            for(int i = 1; i != np; ++i)
                MPI.COMM_WORLD.Recv(primos, i, 1, MPI.INT, i, tag);

            int nPrimos = 0;
            for(int i = 0; i < np; ++i)
                nPrimos += primos[i];

            t = System.currentTimeMillis() - tIni;
            System.out.println(nPrimos + "primos encontrados en " + 
            t/1000 + "s.");
        }
        // Procesos slaves
        else
            MPI.COMM_WORLD.Send(primos, rank, 1, MPI.INT, 0, tag);

        MPI.Finalize();
    }
}