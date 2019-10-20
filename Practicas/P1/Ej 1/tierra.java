/*
 * @author Guillermo Girón 
 * Practice 1: Exercise 1
 */

 public class tierra extends cuerpoPl
 {
     public tierra(int ro, int d)
     {
         super(ro, d);
     }

     @Override
     public String toString() 
     {
         return "Es el planeta donde vivimos. Gira alrededor del sol y mide "
         + diametro() + " km's y tiene un radio orbital de " + radio_orbita()
         + "km's";
     }
 }