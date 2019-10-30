/*
* @author Guillermo Girón 
* @version 1.0
* Practica 4: ejercicio 4
*/

public class incLambda
{
    public int inc(){};
}

public class algHyman implements Runnable
{
    private static boolean b[];
    private static int i, j; 

    @Override
    public void run() 
    {
        while(true)
        {
            b[i] = true;
            while(turn != i)
            {
                while(b[j])
                    continue;
                turn = 1;
            }
            //Critical section
                //Lambda expresion aqui
            b[i] = false;
        }    
    }
}

/*while true do begin
  b[i] := true;
  while not(turn=i) do begin
    while b[j] do begin
      skip; // do nothing
    end
    turn := i;
  end
  // beginning critical region
  b[i] := false;
end*/
