/**
* @author Guillermo Girón García
* @version 1.0
* Class that highlight an image without concurrency
*/

/*
* @author Guillermo Girón García
* @version 1.0
* Class that implements highlighted of pictures with fine grained parallelism
*/

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;
import java.util.concurrent.*;

public class resImagenParFin implements Runnable
{
  private int start, end;
  private static int height, wide;
  private static BufferedImage pic;
  //private static int[][] matrix;

  public resImagenParFin(int start, int end)
  {
    this.start = start;
    this.end = end;
  }

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

  /*public void Highlight()
  {

  }*/

  @Override
  public void run()
  {
    for(int i = this.start+1; i < this.end-1; ++i)
        for(int j = 1 ; j < wide-1; ++j)
        {
            //System.out.println(Thread.currentThread().getName() + " " + i + " " + j);
            Color x = new Color(pic.getRGB(i, j));
            Color xiij = new Color(pic.getRGB(i+1, j));
            Color xijj = new Color(pic.getRGB(i, j+1));
            Color xj = new Color(pic.getRGB(i-1, j));
            Color xi = new Color(pic.getRGB(i, j-1));
            int highlight = (4*(x.getRGB()) - xiij.getRGB() - xijj.getRGB()
            - xj.getRGB() - xi.getRGB()) / 8;
            pic.setRGB(i, j, (new Color(highlight)).getRGB());
        }
  }

  public static void main(String[] args) throws Exception
  {
      height = Integer.parseInt(args[0]);
      wide = Integer.parseInt(args[1]);
      int nThreads = 4;
      int frameSize = height/nThreads;
      int s = 0;
      int e = frameSize;
      pic =
      new BufferedImage(height, wide, BufferedImage.TYPE_INT_RGB);
      ExecutorService executor = Executors.newFixedThreadPool(nThreads);

      long endTime, iniTime = System.currentTimeMillis();
      System.out.println("Loading file in matrix");
      load();
      for(int i = 0; i < nThreads; ++i)
      {
        executor.execute(new resImagenParFin(s,e));
        s = e+1;
        e += frameSize;
      }
      System.out.println("Highlighting picture");
      if(!executor.awaitTermination(10, TimeUnit.SECONDS))
        System.err.println("Error");
      System.out.println("Saving Matrix");
      ImageIO.write(pic, "png", new File("hightlighted"));
      endTime = System.currentTimeMillis();

      System.out.println("Time: " + (endTime-iniTime) + "ms");

  }
}
