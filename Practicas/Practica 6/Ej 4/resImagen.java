/**
* @author Guillermo Girón García
* @version 1.0
* Class that highlight an image without concurrency
*/

/*
* @author Guillermo Girón García
* @version 1.0
* Class that implements highlighted of pictures without concurrency
*/

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

public class resImagen
{
  //private int start, end;
  private static int height, wide;
  private static BufferedImage pic;

  public static void load()
  {
    Random r = new Random();
    int grey;
    for(int i = 0; i < height; ++i)
      for(int j = 0; j < wide; ++j)
      {
        Color colour = new Color(r.nextInt(255));
        grey = (int)((colour.getRed() + colour.getGreen() + colour.getBlue()) / 3);
        pic.setRGB(i, j, (new Color(grey, grey, grey).getRGB()));
      }

  }

  public static void Highlight(int height, int wide, String resultFile)
  throws IOException
  {
      for(int i = 1 ; i < height-1; ++i)
          for(int j = 1 ; j < wide-1; ++j)
          {
              Color x = new Color(pic.getRGB(i, j));
              Color xiij = new Color(pic.getRGB(i+1, j));
              Color xijj = new Color(pic.getRGB(i, j+1));
              Color xj = new Color(pic.getRGB(i-1, j));
              Color xi = new Color(pic.getRGB(i, j-1));
              int highlight = (4*(x.getRGB()) - xiij.getRGB() - xijj.getRGB()
              - xj.getRGB() - xi.getRGB()) / 8;
              pic.setRGB(i, j, (new Color(highlight)).getRGB());
          }

      ImageIO.write(pic, "png", new File(resultFile));
  }

  public static void main(String[] args) throws IOException
  {
      height = Integer.parseInt(args[0]);
      wide = Integer.parseInt(args[1]);

      pic =
      new BufferedImage(height, wide, BufferedImage.TYPE_INT_RGB);

      double endTime, iniTime = System.currentTimeMillis();
      System.out.println("Loading file in matrix");
      load();

      System.out.println("Highlighting picture");
      Highlight(height, wide, "hightlighted.png");
      endTime = System.currentTimeMillis();
      double time = (endTime-iniTime)/1000;

      System.out.println("Matrix saved");
      System.out.println("Time: " + time + "s");

  }
}
