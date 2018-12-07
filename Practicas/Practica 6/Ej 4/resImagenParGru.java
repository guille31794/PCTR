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
import java.util.*;
import java.io.*;

public class resImagenParGru implements Runnable
{
  private int start, end;
  private static int height, wide;
  private static BufferedImage pic;

  public resImagenParGru(int start, int end)
  {
    this.start = start;
    this.end = end;
  }

  public void load()
  {
    Random r = new Random();
    int grey;
    for(int i = this.start; i < this.end; ++i)
      for(int j = 0; j < wide; ++j)
      {
        Color colour = new Color(r.nextInt(255));
        grey = (int)((colour.getRed() + colour.getGreen() + colour.getBlue()) / 3);
        pic.setRGB(i, j, (new Color(grey, grey, grey).getRGB()));
      }
  }

  public void Highlight()
  {
    for(int i = this.start+1; i < this.end-1; ++i)
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
  }

  @Override
  public void run()
  {
    this.load();
    this.Highlight();
  }

  public static void main(String[] args) throws Exception
  {
      height = Integer.parseInt(args[0]);
      wide = Integer.parseInt(args[1]);
      int nThreads = Runtime.getRuntime().availableProcessors();
      int frameSize = height/nThreads;
      int s = 0;
      int e = frameSize;
      pic =
      new BufferedImage(height, wide, BufferedImage.TYPE_INT_RGB);
      ExecutorService executor = Executors.newFixedThreadPool(nThreads);
      System.out.println("Loading file in matrix");
      for(int i = 0; i < nThreads; ++i)
      {
        executor.submit(new resImagenParGru(s,e));
        s = e+1;
        e += frameSize;
      }
      double endTime, iniTime = System.currentTimeMillis();
      executor.shutdown();
      System.out.println("Highlighting picture");
      if(!executor.awaitTermination(120, TimeUnit.SECONDS))
        System.err.println("Error");
      System.out.println("Saving Matrix");
      ImageIO.write(pic, "png", new File("hightlighted"));
      endTime = System.currentTimeMillis();
      double time = (endTime-iniTime)/1000;
      System.out.println("Time: " + time + "s");
  }
}
