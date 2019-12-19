/*
* @author Guillermo Girón García
* Práctica 10: Ejercicio 1 resuelto con implementación
* paso de mensajes
*/

import mpi.*;

public class primosMPJ 
{
    public static void main(String[] args) 
    {
        MPI.Init();

        int rank = MPI.COMM_WORLD.Rank(),
        size = MPI.COMM_WORLD.Size(),
        tag = 100,
        emsisor = 0,    
        unitsize = ;        



        MPI.Finalize();
    }
}