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

public class resImagen
{
  public static int[][] load(String file) throws IOException
  {
      BufferedImage pic = ImageIO.read(new File(file));
      int[][] matrix = new int[pic.getHeight()][pic.getWidth()];

      for(int i = 0 ; i < pic.getHeight() ; ++i)
          for(int j = 0 ; j < pic.getWidth() ; ++j)
          {
              Color c = new Color(pic.getRGB(j, i));
              matrix[i][j] = (int) ((c.getRed() + c.getGreen() + c.getBlue()) / 3);
          }

      return matrix;
  }

  public static void save(int[][] matrix, String fichero) throws IOException
  {
      BufferedImage pic = new BufferedImage(matrix[0].length, matrix.length, BufferedImage.TYPE_4BYTE_ABGR);

      for(int i = 0 ; i < matrix.length ; ++i)
          for(int j = 0 ; j < matrix[0].length ; ++j)
          {
              Color c = new Color(matrix[j][i], matrix[j][i], matrix[j][i]);
              pic.setRGB(i, j, c.getRGB());
          }

      ImageIO.write(pic, "png", new File(fichero));
  }

  public static void Highlight(String Originalfile,
  String resultFile) throws IOException
  {
      BufferedImage pic = ImageIO.read(new File(Originalfile));

      for(int i = 1 ; i < pic.getHeight()-1 ; ++i)
          for(int j = 1 ; j < pic.getWidth()-1 ; ++j)
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
      System.out.println("Highlighting picture");
      Highlight("timeXCb.png", "uca_hightlighted.png");

      System.out.println("Loading file in matrix");
      int[][] matrix = load("uca_hightlighted.png");

      System.out.println("Saving matrix in file");
      save(matrix, "uca_highlighted_processed.png");
  }
}
