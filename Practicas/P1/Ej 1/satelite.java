/*
 * @author Guillermo Girón 
 * Practice 1: Exercise 1
 */

 public class satelite extends cuerpoPl
 {
     private boolean luminoso;

     public satelite(boolean l, int d)
     {
        super(d);
        luminoso = l;
     }

     public  String luminoso()
     {
         if(luminoso)
            return "Es luminoso";
        else
            return "No es luminoso";
     }
 }