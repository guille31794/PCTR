/* Adaptado de M. Ben-Ari por Manuel Francisco */

/* Algoritmo de la panaderia para dos procesos */
class algoLamport implements Runnable
{
    /* Iteraciones que dara cada hilo */
    static final int iteraciones = 2000, N = 4;
    /* Recurso compartido */
    static volatile int enteroCompartido = 0, it = 0;
    /* Numero para los procesos */
    static volatile int[] n = new int[N];
    /* A flag for others threads while taking turn */
    static volatile boolean[] choosing = new boolean[N];
    /* Identifier for every thread */
    private int id;

    /* Calculate maximum element of an array */
    private int max(int[] n)
    {
        int max = 0;

        for(int i =0; i < N; ++i)
            if(n[i] > max)
                max = n[i];

        return max;
    }

    public void run()
    {
        while(it < iteraciones)
        {
            /* Calculate the turn number */
            choosing[id] = true;
            n[id] = (1 + max(n))%N;
            choosing[id] = false;

            /* Compare with other threads */
            for(int j = 0; j < N; ++j)
            {
                /* if another thread is calculating its turn, wait until it finish*/
                while(choosing[j])
                    Thread.yield();
                /* if another thread has more priority, wait until will be zero */
                while(n[j] != 0 && n[j] <= n[id] && j < id)
                    Thread.yield();
            }

            /* Critic section */
            ++enteroCompartido; --enteroCompartido;
            System.out.println("Resource value is: " + enteroCompartido +
            " thread(" + id + ")");
            /* End of critic section */

            n[id] = 0;
            ++it;
        }
    }

    algoLamport(int id)
    {
        this.id = id;
        n[id] = 0;
    }

    public static void main(String[] args) throws Exception
    {
        Runnable[] r = new Runnable[N];
        Thread[] t = new Thread[N];

        for(int i = 0; i < N; ++i)
        {
            r[i] = new algoLamport(i);
            t[i] = new Thread(r[i]);
        }

        for(int i = 0; i < N; ++i)
        {
            t[i].start();
            //t[i].join();
        }
        System.out.println(enteroCompartido);
    }
}
