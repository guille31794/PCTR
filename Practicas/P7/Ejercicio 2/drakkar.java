public class drakkar
{
    private int marmita, racion;

    public drakkar(int racion)
    {
        this.racion = racion;
        marmita = racion;
    }

    public synchronized void comer() throws InterruptedException
    {
        while(marmita == 0)
        {
            notifyAll();
            wait();
        }
        --marmita;
    }

    public synchronized void llenar() throws InterruptedException
    {
        while(marmita)
            wait();
        
        marmita = racion;
        notifyAll();
    }
}