/*
 * @author Guillermo Girón 
 * Practice 1: Exercise 1
 */

 public class sol extends estrella
 {
     public sol(int e, String n, int d)
     {
         super(e, n, d);
     }
    @Override
    public String toString() 
    {
        return "El sol es el astro rey, y está compuesto por muchos gases.Tiene " 
        + edad() + "años y mide " + diametro() + "km's";
    }
 }