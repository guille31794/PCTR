/* Adaptado de M. Ben-Ari por Manuel Francisco */

/* Algoritmo de la panaderia para dos procesos */
class BakeryTwo {
    /* Iteraciones que dara cada hilo */
    static final int iteraciones = 2000000;
    /* Recurso compartido */
    static volatile int enteroCompartido = 0;
    /* Numero para el proceso p */
    static volatile int np = 0;
    /* Numero para el proceso q */
    static volatile int nq = 0;

    class P extends Thread {
        public void run() {
            for (int i=0; i<iteraciones; ++i) {
                /* Seccion no critica */
                np = nq + 1;
                /* esperar nq = 0 o np <= nq */
                while(nq != 0 && np > nq)
                    Thread.yield();
               
                Thread.yield();
                /* Seccion critica */
                ++enteroCompartido;
                /* Fin Seccion critica */
               
                np = 0;
            }
        }
    }
    
    class Q extends Thread {
        public void run() {
            for (int i=0; i<iteraciones; ++i) {
                /* Seccion no critica */
                nq = np + 1;
                while(np != 0 && nq > np)
                    Thread.yield();
                
                Thread.yield();
                /* Seccion critica */
                --enteroCompartido;
                /* Fin Seccion critica */
                
                nq = 0;
            }
        }
    }

    BakeryTwo() {
        Thread p = new P();
        Thread q = new Q();
        p.start();
        q.start();
        
        try {
            p.join();
            q.join();
            System.out.println("El valor del recurso compartido es " + enteroCompartido);
            System.out.println("Deberia ser 0.");
        }
        catch (InterruptedException e) {}
    }

    public static void main(String[] args) {
        new BakeryTwo();
    }
}
