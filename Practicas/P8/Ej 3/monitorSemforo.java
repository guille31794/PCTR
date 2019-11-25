/*
    @author Guillermo Girón García
    @version 1.0
    Práctica 8: ejercicio 3 -> Versión incorrecta.
    No sigue el protocolo de escritura de monitores
    en java y no provee acceso en exclusión mutua

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

    public semaforoMonitor(int n)
    {
        s = n;
    }

    public synchronized void Wait() throws InterruptedException
    {
        while(s == 0)
            this.wait();
        
        --s;
    }

    public synchronized void Signal() throws InterruptedException
    {
        ++s;
        notify();
    }
}