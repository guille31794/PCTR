/**
* @author Guillermo Girón Garcíab
* @version 1.0
* Clase que modela numeros Complejoss junto con sus operaciones mas habituales.
* El grado de la parte imaginaria siempre será 1.
*/

import java.lang.Math;

public class Complejos
{
  private double r_, i_;
  public Complejos()  { r_ = 0; i_ = 0;}
  public Complejos(double r, double i)  {if(r >= 0) r_ = r; else r_ = 0; if(i >= 0)
     i_ = i; else i_ = 0;}
  public double getReal()  { return r_;}
  public double getImaginary() { return i_;}
  public Complejos Sum(Complejos C)
  {
    Complejos returned = new Complejos();
    returned.r_ += this.r_; returned.r_ += C.r_;
    returned.i_ += this.i_; returned.i_ += C.i_;
    return returned;
  }
  public Complejos Subs(Complejos C)
  {
    Complejos returned = new Complejos();
    returned.r_ -= this.r_; returned.r_ -= C.r_;
    returned.i_ -= this.i_; returned.i_ -= C.i_;
    return returned;
  }
  public double Mod()
  {
    double z = 0;
    z += (this.getReal() * this.getReal());
    z += (this.getImaginary() * this.getImaginary());
    z = Math.sqrt(z);
    return z;
  }
  public Complejos Prod(Complejos C)
  {
    Complejos returned = new Complejos();
    returned.r_ += (this.getReal()* C.getReal());
    returned.i_ += (this.getReal()* C.getImaginary());
    returned.i_ += (this.getImaginary() * C.getReal());
    returned.r_ += (-1) * (this.getImaginary() * C.getImaginary());
    return returned;
  }
  public Complejos Quotient(Complejos C)
  {
    Complejos returned = new Complejos();
    Complejos conjugate = C;
    double conjugado;

    if(C.i_ >= 0)
      conjugado = (C.getReal()*C.getReal()) - (C.getImaginary()*C.getImaginary());
    else
      conjugado = (C.getReal()*C.getReal()) + (C.getImaginary()*C.getImaginary());

    conjugate.i_ *= (-1);
    returned = this.Prod(conjugate);
    returned.r_ /= conjugado;
    returned.i_ /= conjugado;

    return returned;
  }
}
