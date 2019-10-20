/*
* @author Guillermo Girón
* Practice 2: Exercise 5
*/

public class redCajeros
{
    public static void main(String[] args) throws InterruptedException
    {
        cuentaCorriente c = new cuentaCorriente(1000.0);
        cajero c1 = new cajero(c, 1, 750),
        c2 = new cajero(c, 3, 0.0),
        c3 = new cajero(c, 2, 375.0),
        c4 = new cajero(c, 2, 375.0);
        c1.start();
        c2.start();
        c3.start();
        c4.start();
        cajero c5 = new cajero(c, 1, 300);
        c5.start();
        cajero c6 = new cajero(c, 2, 300);
        c5.join();
        c6.start();
        c6.join();
        c4.join();
        c3.join();
        c2.join();
        c1.join();
        
        System.out.println(c.saldo);
    }
}