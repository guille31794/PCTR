/**
* @author Guillermo Girón García
* @version 1.0
* Programa que devuelve el interés de una cantidad a plazo fijo de seis meses
*/

public class interesTipoFijo
{
  private float interest, quantity;
  public interesTipoFijo(float i, float q) { interest = i; quantity = q;}

	/**
	* Returns value of interes
	* @return
	*/
	public float getInterest() {
		return interest;
	}

	/**
	* Sets new value of interes
	* @param
	*/
	public void setInterest(float interest) {
		this.interest = interest;
	}

	/**
	* Returns value of quantity
	* @return
	*/
	public float getQuantity() {
		return quantity;
	}

  public float toPay()
  {
    return (quantity*(interest/100));
  }

	public static void main(String[] args)
	{
		interesTipoFijo I = new interesTipoFijo(Float.parseFloat(args[0]),
		Float.parseFloat(args[1]));
		System.out.println("The interest from " + I.getQuantity() + " at " +
		I.getInterest() + "% are " + I.toPay() + " over the initial quantity. Total: "
		+ (I.getQuantity()+I.toPay()) + "€");
	}

	/**
	* Sets new value of quantity
	* @param
	*/
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
}
