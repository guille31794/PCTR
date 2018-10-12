
public abstract class cPlanetario
{
  private int size;
  private String name;
  public cPlanetario(int s, String n) {size = s; name = n;}
  public cPlanetario()  {}

	/**
	* Returns value of size
	* @return
	*/
	public int getSize() {
		return size;
	}

	/**
	* Sets new value of size
	* @param
	*/
	public void setSize(int size) {
		this.size = size;
	}

	/**
	* Returns value of name
	* @return
	*/
	public String getName() {
		return name;
	}

	/**
	* Sets new value of name
	* @param
	*/
	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "cPlanetario [size=" + size + ", name=" + name + "]";
	}
}
