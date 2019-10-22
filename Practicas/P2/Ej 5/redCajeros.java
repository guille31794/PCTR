/*
* @author Guillermo Girón
* Practice 2: Exercise 5
*/

public class redCajeros
{
    public static void main(String[] args) throws InterruptedException
    {
        cuentaCorriente c = new cuentaCorriente(1000.0);
        cajero caj[] = new cajero[9999];
        int op = 1;
        for(int i =  0; i < 9999; ++i)
        {    
            if(op == 3)
                op -= 2;
            caj[i] = new cajero(c,op, 3000);
            ++op;
            caj[i].start();
        }
        
        for(int i = 9998; i > -1; --i)
            caj[i].join();
        
        System.out.println(c.saldo);
    }
}
