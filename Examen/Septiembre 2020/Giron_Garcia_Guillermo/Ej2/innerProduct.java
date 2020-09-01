/*
    Comando para ejecutar el programa en Linux:
        javac -cp .:$MPJ_HOME/lib/mpj.jar innerProduct.java
        mpjrun.sh -np 9 innerProduct
*/

import mpi.*;
import java.util.Scanner;

public class innerProduct
{
    public static void main(String[] args) throws MPIException
    {
        // Inicialización y parametros necesarios 
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank(),
        tag = 100,
        // División inicial de las tareas
        // El primer argumento a tomar lo utiliza
        // MPI.Init, el segundo es nuestro parametro
        // En NP se almacenan el número de procesos
        np = Integer.parseInt(args[1]);

        Scanner sc = new Scanner(System.in);
        
        float v1[] = new float[np-1],
        v2[] = new float[np-1],
        v3[] = new float[np-1];

        System.out.println("Introduzca primer vector: ");
        for(int i = 0; i < np-1; ++i)
            v1[i] = sc.nextFloat();

        System.out.println("Introduzca segundo vector: ");
        for(int i = 0; i < np-1; ++i)
            v2[i] = sc.nextFloat();

        long t,
        tIni = System.currentTimeMillis();

        // Un único proceso para controlarlos a todos (MASTER)
        if(rank == 0)
        {
            for(int i = 1; i != np; ++i)
                MPI.COMM_WORLD.Recv(v3, i, 1, MPI.FLOAT, i-1, tag);

            float prod = 0f;

            for(int i = 0; i < np-1; ++i)
                prod += v3[i];

            t = System.currentTimeMillis() - tIni;
            System.out.println("El producto interno de los dos vectores es: " + 
            prod + "y el tiempo para ejecutarlo" + t/1000 + "s.");
        }
        // Procesos slaves
        else
        {
            v3[rank] = v1[rank] * v2[rank];
            MPI.COMM_WORLD.Send(v3, rank, 1, MPI.INT, 0, tag);
        }
            
        MPI.Finalize();
    }
}