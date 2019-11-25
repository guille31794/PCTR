//MONITOR PRODUCTOR CONSUMIDOR CON API ESTNDAR
//MODIFICAR PARA TRABAJAR CON UN BUFFER DE MATRICES, SEGN SE PIDE EN EL EJERCICIO 2
//LUEGO, HACER UN DISEO DE HEBRAS

    public class Buffer {
      private int numSlots = 0;
      private Matrix[] buffer = null;
      private int putIn = 0, takeOut = 0;
      private int cont = 0;

      public Buffer(int numSlots) {
        this.numSlots = numSlots;
        buffer = new Matrix[numSlots];
      }

      public synchronized void insertar (Matrix m) {
        while (cont == numSlots)
          try {
            wait();
          } catch (InterruptedException e) {
            System.err.println("wait interrumpido");
          }
        buffer[putIn] = m;
        putIn = (putIn + 1) % numSlots;
        cont++;
        notifyAll();
      }

      public synchronized Matrix extraer () {
        Matrix m;
        while (cont == 0)
          try {
            wait();
          } catch (InterruptedException e) {
            System.err.println("wait interrumpido");
          }
        m = buffer[takeOut];
        takeOut = (takeOut + 1) % numSlots;
        cont--;
        notifyAll();
        return m;
      }
    }//Buffer
