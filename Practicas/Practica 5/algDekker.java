package dekker;
public class algDekker {
    /* Number of processes currently in critical section */
    static volatile int inCS = 0;
    /* Process p wants to enter critical section */
    static volatile boolean wantp = false;
    /* Process q wants to enter critical section */    
    static volatile boolean wantq = false;
    static volatile boolean wantr = false;
    /* Which processes turn is it */
    static volatile int turn = 1;

    class P extends Thread {
        int c = 0;
        public void run() {
            //El c<10 es solo para darle fin al algoritmo (que se ejecute 10 veces)
            while (c<10) {
                /* Non-critical section */
                //Primero levanta la bandera (indica que quiere acceder)
                wantp = true;
                //No puede acceder a la sección crítica hasta que los otros dos
                //hayan bajado sus banderas
                while (wantq || wantr) {
                    //Si no es su turno
                    if (turn != 1) {
                        //Si el que tiene el turno no tiene su bandera levantada, se lo asigna a sí mismo
                        if((turn==0 && !wantr) || (turn==2&&!wantq)){
                            turn=1;
                        } else {
                            //Si no, pues tiene que esperarse y bajar su bandera para permitir al otro entrar.
                            wantp = false;      
                            while (turn != 1)
                                //Se pone a esperar hasta que se le asigne el turno
                                Thread.yield();
                            //Una vez gana el turno, sube su bandera.
                            wantp = true;       
                        }
                    }
                    //Nota: aún tiene que esperar a que los otros dos hilos bajen sus banderas ( while (wantq || wantr) )
                    //Como tiene el turno y tiene su bandera levantada, no va a perderlos hasta
                    //acceder a la sección crítica.
                }   
                inCS++;
                //No recuerdo por qué está este yield aquí, pero bueno
                Thread.yield();
                /* Critical section */
                System.out.println("Number of processes in critical section: "
                        + inCS);
                inCS--;
                //Cuando ha pasado por la sección crítica, le pasa el turno a otro y baja su bandera.
                turn = (turn + 1)%3;
                wantp = false;
                c++;
            }
        }
    }
    
    //El comportamiento es el mismo en las tres clases.
    class Q extends Thread {
        public void run() {
            while (true) {
                /* Non-critical section */
                wantq = true;
                while (wantp || wantr) {
                    if (turn != 2) {
                        if((turn==0 && !wantr) || (turn==1 && !wantp)){
                            turn=2;
                        } else {
                            wantq = false;
                            while (turn != 2)
                                Thread.yield();
                            wantq = true;
                        }
                    }
                }
                inCS++;
                Thread.yield();
                /* Critical section */
                System.out.println("Number of processes in critical section: "
                        + inCS);
                inCS--;
                turn = (turn + 1)%3;
                wantq = false;
            }
        }
    }
    
    
    class R extends Thread {
        public void run() {
            while (true) {
                /* Non-critical section */
                wantr = true;           
                while (wantq || wantp) {         
                    if (turn != 0) {                   
                        if((turn==1 && !wantp) || (turn==2&&!wantq)){
                            turn=1;
                        } else {
                            wantr = false;      
                            while (turn != 0)
                                Thread.yield();
                            wantr = true;       
                        }
                    }
                }   
                inCS++;                 
                Thread.yield();
                /* Critical section */
                System.out.println("Number of processes in critical section: "
                        + inCS);
                inCS--;
                turn = (turn + 1)%3;
                wantp = false;
            }
        }
    }

    algDekker() {
        Thread p = new P();
        Thread q = new Q();
        Thread r = new R();
        p.start();
        q.start();
        r.start();
    }

    public static void main(String[] args) {
        new algDekker();
    }
}
