/*
* @author Guillermo Girón García
* @version 1.0
* Práctica 6: Ejercicio 4
*/

public class cuentaCorrienteSegura
{
    private double saldo;

    public cuentaCorrienteSegura(double s)
    {
        saldo = s;
    }

    public void reintegro(unsigned r)
    {
        System.out.println("El saldo antes del reintegro es: " + saldo);
        synchronized(this)
        {
            if (saldo > r)
                saldo -= r;
            else
                System.out.println("No hay saldo suficiente para completar la transaccion");
        }
        System.out.println("El saldo despues es: " + saldo);
    }

    public void ingreso(double i)
    {
        System.out.println("El saldo previo al ingreso es: " + saldo);
        synchronized(this)
        {
            saldo += i;
        }
        System.out.println("El saldo después del ingreso es: " + saldo);
    }

    public double saldo()
    {
        return saldo;
    }
}