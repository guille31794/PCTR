/*
 * @author Guillermo Girón 
 * Practice 1: Exercise 1
 */

public class luna extends satelite
{
    public luna(boolean b, int d)
    {
        super(b, d);
    }

    @Override
    public String toString()
    {
        return "La luna es el satelite terrestre. " + luminoso() + 
        " Mide " + diametro() + " km's";
    }
}