package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Filter {

    private BufferedImage image;
    private BufferedImage dummy;
    private Integer width;
    private Integer height;
    private String imageName="";

    public String getImageName() {
        return imageName;
    }

    public Filter(String fileName){
        setClassArguments(fileName);
    }

    private void setClassArguments(String fileName)
    {
        File input = new File(fileName);
        try {
            this.image = ImageIO.read(input);
            this.dummy = image;
            this.width = image.getWidth();
            this.height = image.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getPixelColorhexCode()
    {
        String hex;
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                Color c = new Color(image.getRGB(j, i));
                hex = String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
                System.out.println(hex);
            }
        }
    }

    private int[] getRGBAVector(int i,int j)
    {
        int[] RGBA = new int[4];

        int pixel = image.getRGB(i,j);
        int alpha = (pixel>>24)&0xff;
        int red = (pixel>>16)&0xff;
        int green = (pixel>>8)&0xff;
        int blue = pixel&0xff;

        RGBA[0] = alpha;
        RGBA[1] = red;
        RGBA[2] = green;
        RGBA[3] = blue;

        return RGBA;
    }

    public void applyGrayscale()
    {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
            {
                int pixel;
                int[] RGBA = getRGBAVector(i,j);
                int avg = (RGBA[1]+RGBA[2]+RGBA[3])/3;
                pixel = (RGBA[0]<<24) | (avg<<16) | (avg<<8) | avg;
                dummy.setRGB(i, j, pixel);
            }
        exportDummyToFile("/home/fluffle2/Desktop/dummy1.jpg");
        imageName = "dummy1";
    }

    public void applyRed()
    {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
            {
                int pixel;
                int[] RGBA = getRGBAVector(i,j);
                RGBA[2] = 0;
                RGBA[3] = 0;
                pixel = (RGBA[0]<<24) |(RGBA[1]<<16) | (RGBA[2]<<8) | RGBA[3];
                dummy.setRGB(i, j, pixel);
            }
        exportDummyToFile("/home/fluffle2/Desktop/dummy2.jpg");
        imageName = "dummy2";
    }

    public void applyThresholdingFromRGBA(int limit)
    {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                int pixel;
                int[] RGBA = getRGBAVector(i,j);

                if(RGBA[1] >= 0 && RGBA[1] < limit)
                    RGBA[1] = 0;
                else if(RGBA[1] > limit && RGBA[1] <= 255)
                    RGBA[1] = 255;
                else RGBA[1] = 0;

                if(RGBA[2] >= 0 && RGBA[2] <= limit)
                    RGBA[2] = 0;
                else if(RGBA[2] > limit && RGBA[2] <= 255)
                    RGBA[2] = 255;
                else RGBA[2] = 0;

                if(RGBA[3] >= 0 && RGBA[3] <= limit)
                    RGBA[3] = 0;
                else if(RGBA[3] > limit && RGBA[3] <= 255)
                    RGBA[3] = 255;
                else RGBA[3] = 0;

                pixel = (RGBA[0]<<24) |(RGBA[1]<<16) | (RGBA[2]<<8) | RGBA[3];
                dummy.setRGB(i, j, pixel);
            }

        }
        exportDummyToFile("/home/fluffle2/Desktop/dummy3.jpg");
        imageName = "dummy3";
    }

    public void applyThresholdingFromGrayscale(int limit)
    {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                int pixel;
                int[] RGBA = getRGBAVector(i,j);
                int avg = (RGBA[1]+RGBA[2]+RGBA[3])/3;
                if (avg >= 0  && avg < 125) avg = 0;
                else if (avg >= 125 && avg < 256) avg = 255;
                else avg = 0;

                pixel = (RGBA[0]<<24) | (avg<<16) | (avg<<8) | avg;
                dummy.setRGB(i, j, pixel);
            }
        }
        exportDummyToFile("/home/fluffle2/Desktop/dummy4.jpg");
        imageName = "dummy4";
    }

    public void applyContrast()
    {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                int pixel;
                int[] RGBA = getRGBAVector(i,j);
                if(RGBA[1] >= 0 && RGBA[1] <= 255)
                    RGBA[1] = (int)((Math.sin(Math.PI*RGBA[1]/255 - Math.PI/2)+1)/2*255);
                if(RGBA[1] < 0)
                    RGBA[1] = 0;
                if(RGBA[1] > 255)
                    RGBA[1] = 255;

                if(RGBA[2] >= 0 && RGBA[2] <= 255)
                    RGBA[2] = (int)((Math.sin(Math.PI*RGBA[2]/255 - Math.PI/2)+1)/2*255);
                if(RGBA[2] < 0)
                    RGBA[2] = 0;
                if(RGBA[2] > 255)
                    RGBA[2] = 255;

                if(RGBA[3] >= 0 && RGBA[3] <= 255)
                    RGBA[3] = (int)((Math.sin(Math.PI*RGBA[3]/255 - Math.PI/2)+1)/2*255);
                if(RGBA[3] < 0)
                    RGBA[3] = 0;
                if(RGBA[3] > 255)
                    RGBA[3] = 255;

                pixel = (RGBA[0]<<24) | (RGBA[1] <<16) | (RGBA[2]<<8) | RGBA[3];
                dummy.setRGB(i, j, pixel);
            }
        }
        exportDummyToFile("/home/fluffle2/Desktop/dummy5.jpg");
        imageName = "dummy5";
    }

    public void applyInvert()
    {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                int pixel;
                int[] RGBA = getRGBAVector(i,j);
                if(RGBA[1] >= 0 && RGBA[1] <= 255)
                    RGBA[1] = 255 - RGBA[1];
                if(RGBA[1] < 0)
                    RGBA[1] = 0;
                if(RGBA[1] > 255)
                    RGBA[1] = 255;

                if(RGBA[2] >= 0 && RGBA[2] <= 255)
                    RGBA[2] = 255 - RGBA[2];
                if(RGBA[2] < 0)
                    RGBA[2] = 0;
                if(RGBA[2] > 255)
                    RGBA[2] = 255;

                if(RGBA[3] >= 0 && RGBA[3] <= 255)
                    RGBA[3] = 255 - RGBA[3];
                if(RGBA[3] < 0)
                    RGBA[3] = 0;
                if(RGBA[3] > 255)
                    RGBA[3] = 255;

                pixel = (RGBA[0]<<24) | (RGBA[1] <<16) | (RGBA[2]<<8) | RGBA[3];
                dummy.setRGB(i, j, pixel);
            }

        }
        exportDummyToFile("/home/fluffle2/Desktop/dummy6.jpg");
        imageName = "dummy6";
    }

    public void applyGammaCorrection()
    {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                int pixel;
                int[] RGBA = getRGBAVector(i,j);
                if(RGBA[1] >= 0 && RGBA[1] <= 255)
                    RGBA[1] = (int) (255 * Math.pow((RGBA[1]/255),1/2.2));
                if(RGBA[1] < 0)
                    RGBA[1] = 0;
                if(RGBA[1] > 255)
                    RGBA[1] = 255;

                if(RGBA[2] >= 0 && RGBA[2] <= 255)
                    RGBA[2] = (int) (255 * Math.pow((RGBA[2]/255),1/2.2));
                if(RGBA[2] < 0)
                    RGBA[2] = 0;
                if(RGBA[2] > 255)
                    RGBA[2] = 255;

                if(RGBA[3] >= 0 && RGBA[3] <= 255)
                    RGBA[3] = (int) (255 * Math.pow((RGBA[3]/255),1/2.2));
                if(RGBA[3] < 0)
                    RGBA[3] = 0;
                if(RGBA[3] > 255)
                    RGBA[3] = 255;

                pixel = (RGBA[0]<<24) | (RGBA[1] <<16) | (RGBA[2]<<8) | RGBA[3];
                dummy.setRGB(i, j, pixel);
            }

        }
        exportDummyToFile("/home/fluffle2/Desktop/dummy7.jpg");
        imageName = "dummy7";
    }


    private void exportDummyToFile(String fileName)
    {
        try
        {
            File file = new File(fileName);
            ImageIO.write(dummy, "jpg", file);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }


}
