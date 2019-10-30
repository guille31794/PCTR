/* Copyright (C) 2006 M. Ben-Ari. See copyright.txt */
/* Programmed by Panu Pitk�m�ki */

/* Dekker's algorithm */
public class algDekker {
    /* Number of processes currently in critical section */
    static volatile int inCS = 0;
    /* Process p wants to enter critical section */
    static volatile boolean wantp = false;
    /* Process q wants to enter critical section */
    static volatile boolean wantq = false;
    /* Process r wants to enter critical section */
    static volatile boolean wantr = false;
    /* Which processes turn is it */
    static volatile int turn = 1;

    class P extends Thread {
        public void run() {
            int i = 0;
            while (i < 10000) {
                /* Non-critical section */
                wantp = true;
                while (wantq || wantr) {
                    if (turn != 2) {
                        wantp = false;
                        while (turn != 2)
                            Thread.yield();
                        wantp = true;
                    }
                }
                inCS++;
                Thread.yield();
                /* Critical section */
                //System.out.println("Number of processes in critical section: "
                //        + inCS);
                inCS--;
                turn = (turn+1)%3;
                wantp = false;
                ++i;
            }
        }
    }

    class Q extends Thread {
        public void run() {
            int i = 0;
            while (i < 10000) {
                /* Non-critical section */
                wantq = true;
                while (wantp || wantr) {
                    if (turn != 1) {
                        wantq = false;
                        while (turn != 1)
                            Thread.yield();
                        wantq = true;
                    }
                }
                inCS++;
                Thread.yield();
                /* Critical section */
                //System.out.println("Number of processes in critical section: "
                //        + inCS);
                inCS--;
                turn = (turn+1)%3;
                wantq = false;
                ++i;
            }
        }
    }

    class R extends Thread{
      public void run(){
          int i = 0;
        while(i < 10000){
          /*Non-critical section*/
          wantr = true;
          while(wantp || wantq)
          {
              if(turn != 0)
              {
                  wantr = false;
                  while(turn != 0)
                    Thread.yield();
                wantr = true;
              }
          }
          inCS++;
          Thread.yield();
          /* Critical-section */
          //System.out.println("Number of processes in critical seccion: " + inCS);
          //--inCS;
          turn = (turn+1)%3;
          wantr = false;
          ++i;
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
