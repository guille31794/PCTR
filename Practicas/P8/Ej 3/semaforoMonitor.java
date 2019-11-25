/*
    @author Guillermo Girón García
    @version 1.1
    Práctica 8: ejercicio 3.

    monitorSemaforo
    {
        var:
            s:integer;
            cola_procesos:condition;
        
        procedure wait()
        begin
            if !s then do:
                wait(cola_procesos);
            else
                --s;
        end

        procedure signal()
        begin
            if non_empty(cola_procesos)
                send(cola_procesos)
            else
                ++s;
        end

        begin:
            s := valor_inicial
    }
*/

public class semaforoMonitor
{
    private int s;
    boolean empty;

    public semaforoMonitor(int n)
    {
        s = n;
        empty = true;
    }

    public synchronized void Wait() throws InterruptedException
    {
        if(s == 0)
        {
            empty = false;
            this.wait();
        }
        else
            --s;
    }

    public synchronized void Signal() throws InterruptedException
    {
        if(empty)
            ++s;
        else
        {
            notifyAll();
            empty = true;
        }
    }
}