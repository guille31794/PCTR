/*
* @author Guillermo Girón
* Practice 2: Exercise 5
*/

public class cajero extends Thread
{
    private cuentaCorriente c;
    private int op;
    private double can;

    public cajero(cuentaCorriente c, int o, double cantidad)
    {
        op = o;
        this.c = c;
        can = cantidad;
    }

    void reintegro()
    {
        if(c.saldo > can)
            c.saldo -= can;
    }

    void ingreso()
    {
        c.saldo += can;
    }

    double saldo()
    {return c.saldo;}

    @Override
    public void run() 
    {
        switch(op)
        {
            case 1: reintegro();
            break;
            case 2: ingreso();
            break;
            case 3: System.out.println(saldo()); 
            break;
            default: break;
        }
    }
}