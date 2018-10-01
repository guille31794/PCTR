/**
* @author Guillermo Girón García
* @version 1.0
* Calcula la función de ackerman para 2 valores enteros
*/

public class Ack
{
  public static long function(long m, long n)
  {
    if(m == 0){
      return n+1;}
    else if(m > 0 && n == 0)
      {return function(m-1, 1);}
    else
      {return function(m-1, function(m, n-1));}
  }

  public static void main(String[] args)
  {
    long result;
    result = function(Long.parseLong(args[0]), Long.parseLong(args[1]));
    System.out.println(result);
  }
}
