/**
* @author Guillermo Girón García
* @version 1.0
* Calcula la función de ackerman para 2 valores enteros
*/

import java.math.BigInteger;

public class Ack
{
  public static final BigInteger ZERO = new BigInteger("0");
  public static final BigInteger ONE = new BigInteger("1");
  public static BigInteger function(BigInteger m, BigInteger n)
  {
    if(m.compareTo(ZERO) == 0)
      return n.add(ONE);
    else if((m.compareTo(ZERO) > 0) && (n.compareTo(ZERO) == 0))
      return function(m.subtract(ONE), ONE);
    else
      return function(m.subtract(ONE), function(m, n.subtract(ONE)));
  }

  public static void main(String[] args)
  {
    BigInteger result;
    result = function(new BigInteger(args[0]), new BigInteger(args[1]));
    System.out.println(result);
  }
}
