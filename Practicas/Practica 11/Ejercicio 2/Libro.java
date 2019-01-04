/**
* @author Guillermo Girón García
* @version 1.0
* Class that implements the basic facts of a book
*/

public class Libro
{
  private String title, author;
	private int downloads = 0;

	public Libro() {}

	public Libro(String title, String author)
  {
		this.title = title;
		this.author = author;
	}

	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	public String getAuthor() { return author;	}
	public void setAuthor(String author) {	this.author = author;	}
	public int getDownloads()	{	return downloads;	}
	public void download(){++downloads;}

	@Override
	public String toString()
	{	return "Libro [title = " + title + ", author = " + author + " ,downloads = "
	 + downloads + "]";	}
}
