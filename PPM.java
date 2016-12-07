//Susanna Pritchett

import java.util.Scanner;
import java.io.*;

public class PPM extends Image{

  Pixel [][] pixels;

  public PPM(String file) throws Exception{
    Scanner sc = new Scanner(new File(file));
    magic = sc.next();
    width = sc.nextInt();
    height = sc.nextInt();
    depth = sc.nextInt();

    pixels = new Pixel [height][width];
    for(int i = 0; i < height; i++){
      for(int j = 0; j < width; j++){
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels [i][j] = new Pixel(r,g,b);
      }
  }
  sc.close();
}

  public PPM(){
    magic = "";
    width = 0;
    height = 0;
    depth = 0;
  }

  @Override
  public void flip_horizontally(){
    Pixel [][] flipHpixels = new Pixel[height][width];

    for(int i = 0; i < height; i++){
      for(int j = 0; j < width; j++){
        flipHpixels [i][j] = pixels[i][width - 1 - j];
      }
    }
    pixels = flipHpixels;
  }

  @Override
  public void flip_vertically(){
    Pixel [][] flipVpixels = new Pixel[height][width];

    for(int i = 0; i < height; i++){
      for(int j = 0; j < width; j++){
        flipVpixels [i][j] = pixels[height - 1 -i][j];
      }
    }
    pixels = flipVpixels;
  }

  @Override
  public void rotate_right_90(){
    //flip dimensions first
    int h = height;
    height = width;
    width = h;

    //now flip the width and the height

    Pixel [][] rotatepixels = new Pixel[height][width];

    for(int i = 0; i < height; i++){
      for(int j = 0; j < width; j++){
        rotatepixels [i][j] = pixels[width - j - 1][i];
      }
    }
    pixels = rotatepixels;
  }

  public void negate_red(){
    for(int i = 0; i < height; i++){
      for(int j = 0; j < width; j++){
        pixels[i][j].setRed(255 - pixels[i][j].getRed());
      }
    }
  }

  public void negate_green(){
    for(int i = 0; i < height; i++){
      for(int j = 0; j < width; j++){
        pixels[i][j].setGreen(255 - pixels[i][j].getGreen());
      }
    }
  }

  public void negate_blue(){
    for(int i = 0; i < height; i++){
      for(int j = 0; j < width; j++){
        pixels[i][j].setBlue(255 - pixels[i][j].getBlue());
      }
    }
  }

  public void grey_scale(){
    for(int i = 0; i < height; i++){
      for(int j = 0; j < width; j++){

        int avg = (pixels[i][j].getRed() + pixels[i][j].getGreen() + pixels[i][j].getBlue()) / 3;

        pixels[i][j].setRed(avg);
        pixels[i][j].setGreen(avg);
        pixels[i][j].setBlue(avg);
      }
    }
  }

  public void flatten_red(){
    for(int i = 0; i < height; i++){
      for(int j = 0; j < width; j++){
        pixels[i][j].setRed(0);
      }
    }
  }

  public void flatten_green(){
    for(int i = 0; i < height; i++){
      for(int j = 0; j < width; j++){
        pixels[i][j].setGreen(0);
      }
    }
  }

  public void flatten_blue(){
    for(int i = 0; i < height; i++){
      for(int j = 0; j < width; j++){
        pixels[i][j].setBlue(0);
      }
    }
  }

  public void extreme_contrast(){
    for(int i = 0; i < height; i++){
      for(int j = 0; j < width; j++){
        if(pixels[i][j].getRed() > 127){
          pixels[i][j].setRed(255);
        }
        else{
          pixels[i][j].setRed(0);
        }

        if(pixels[i][j].getGreen() > 127){
          pixels[i][j].setGreen(255);
        }
        else{
          pixels[i][j].setGreen(0);
        }

        if(pixels[i][j].getBlue() > 127){
          pixels[i][j].setBlue(255);
        }
        else{
          pixels[i][j].setBlue(0);
        }
      }
    }
  }

  //print the torturous thing
  public void save(String f){
    try {
    PrintWriter writer = new PrintWriter(f);
    writer.println(magic);
    writer.print(width);
    writer.print(" ");
    writer.println(height);
    writer.println(depth);
    for(int i = 0; i < height; i++){
      for(int j = 0; j < width; j++){
        writer.print(pixels[i][j].getRed() + "  ");
        writer.print(pixels[i][j].getGreen() + "  ");
        writer.print(pixels[i][j].getBlue() + "  ");
        writer.print("\t");
      }
      writer.println();
    }
    writer.close();
  }
  catch(Exception e)
  {
    System.out.println("Unable to save.");
  }
  }

  public static void main(String[] args)
   {

     System.out.println("Portable Pixmap (PPM) Image Editor");
     System.out.println();

     Scanner sc = new Scanner(System.in);

     System.out.print("Enter name of image file: ");
     String file = sc.nextLine();

     PPM PPMImage;//this is null for now but we use it below; we had to establish it here
     //so that it can be used in the scope of main as well as try.
     try{PPMImage = new PPM(file);}//try this
     catch (Exception error){
       System.out.println("File not found.");
       return;//end the program
     }

     System.out.print("Enter name of output file: ");
     String out = sc.nextLine();

     System.out.println();

     System.out.println("Here are your choices:");
     System.out.println("[1]  convert to greyscale [2]  flip horizontally");
     System.out.println("[3]  negative of red [4]  negative of green [5]  negative of blue");
     System.out.println("[6]  just the reds [7]  just the greens [8]  just the blues");
     System.out.println("[9]  extreme contrast");
     System.out.println();

     System.out.print("Do you want [1]? (y/n) ");
     String answer1 = sc.nextLine();

     System.out.print("Do you want [2]? (y/n) ");
     String answer2 = sc.nextLine();

     System.out.print("Do you want [3]? (y/n) ");
     String answer3 = sc.nextLine();

     System.out.print("Do you want [4]? (y/n) ");
     String answer4 = sc.nextLine();

     System.out.print("Do you want [5]? (y/n) ");
     String answer5 = sc.nextLine();

     System.out.print("Do you want [6]? (y/n) ");
     String answer6 = sc.nextLine();

     System.out.print("Do you want [7]? (y/n) ");
     String answer7 = sc.nextLine();

     System.out.print("Do you want [8]? (y/n) ");
     String answer8 = sc.nextLine();

     System.out.print("Do you want [9]? (y/n) ");
     String answer9 = sc.nextLine();

     if(answer1.equals("y")){
       PPMImage.grey_scale();
     }

     if(answer2.equals("y")){
       PPMImage.flip_horizontally();
     }

     if(answer3.equals("y")){
       PPMImage.negate_red();
     }

     if(answer4.equals("y")){
       PPMImage.negate_green();
     }

     if(answer5.equals("y")){
       PPMImage.negate_blue();
     }

     if(answer6.equals("y")){
       PPMImage.flatten_blue();
       PPMImage.flatten_green();
     }

     if(answer7.equals("y")){
       PPMImage.flatten_blue();
       PPMImage.flatten_red();
     }

     if(answer8.equals("y")){
       PPMImage.flatten_red();
       PPMImage.flatten_green();
     }

     if(answer9.equals("y")){
       PPMImage.extreme_contrast();
     }

     PPMImage.save(out);

     System.out.println(out + " was created.");

   }
}
