import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;

/* File: GreenScreen.java
 * Name: Alex Luu
 * Date: 02 June 2019
 * Desc: Replaces the green part of a green screen image with the provided
 * replacement image.
 */

public class GreenScreen{
	
	public static void main(String[] args){
		
		
		//establish the basics
		Scanner input = new Scanner(System.in);
		int det = 0;
		
		//part 1
		BufferedImage imgMain = null;
		System.out.println("The first image is the one with the green screen.");		
		System.out.println("Is the main image a file (1) or URL (2)?");
		
		String answer = input.nextLine();
		
		try{
			det = Integer.parseInt(answer);
		}
		catch(Exception ex){
			
		}
		 
		while(det != 1 && det != 2){
			System.out.println("Sorry, is this an image file (1) or URL (2)?");
			try{
				answer = input.nextLine();
				det = Integer.parseInt(answer);
			}
			catch(Exception ex){
			
			}
		}
		 
		System.out.println("What's the file name with extension or URL?");
		String imgMainName = input.nextLine();
		 
		//get image
		if(det == 1){
			try{
			imgMain = ImageIO.read(new File(imgMainName));
			}
			catch(IOException ex1){
			ex1.printStackTrace();
			} 
		}
		 
		//make the URL a processable image
		if(det == 2){
			try{
				URL url1 = new URL(imgMainName);
				imgMain = ImageIO.read(url1);
			}
			catch(MalformedURLException ex2){
				ex2.printStackTrace();
			}
			catch(IOException ex3){
				ex3.printStackTrace();
			}
		}
		
		//part 2
		//establish the basics
		det = 0;
		BufferedImage imgBG = null;
		 
		
		System.out.println("Is the replacement image a file (1) or URL (2)?");
		System.out.println("Please make sure this is larger than the main image");
		System.out.println("or there will be a cutoff in the image.");
		
		answer = input.nextLine();
		
		try{
			det = Integer.parseInt(answer);
		}
		catch(Exception ex5){
			
		}
		 
		while(det != 1 && det != 2){
			System.out.println("Sorry, is this an image file (1) or URL (2)?");
			try{
				answer = input.nextLine();
				det = Integer.parseInt(answer);
			}
			catch(Exception ex6){
			
			}
		}
		 
		System.out.println("What's the file name with extension or URL?");
		String imgBGName = input.nextLine();
		 
		//get image
		if(det == 1){
			try{
			imgBG = ImageIO.read(new File(imgBGName));
			}
			catch(IOException ex7){
			ex7.printStackTrace();
			} 
		}
		 
		//make the URL a processable image
		if(det == 2){
			try{
				URL url2 = new URL(imgBGName);
				imgBG = ImageIO.read(url2);
			}
			catch(MalformedURLException ex2){
				ex2.printStackTrace();
			}
			catch(IOException ex3){
				ex3.printStackTrace();
			}
		}
		
		int BGWidth = imgBG.getWidth();
		int BGHeight = imgBG.getHeight();
		int MainWidth = imgMain.getWidth();
		int MainHeight = imgMain.getHeight();
		int MainType = imgMain.getType();
		Color set, setter;
		int red, green, blue;
		
		BufferedImage newImg = new BufferedImage(MainWidth, MainHeight, MainType);
		
		for(int i=0; i < MainWidth; i++){
			for(int j=0; j < MainHeight; j++){
				set = new Color(imgMain.getRGB(i,j));
				
				if(i < BGWidth && j < BGHeight){
					red = set.getRed();
					green = set.getGreen();
					blue = set.getBlue();
					
					//if it's too green, the image will be swapped
					if(green > 160 && red < 100 && blue < 100){
						setter = new Color(imgBG.getRGB(i,j));
						newImg.setRGB(i,j, setter.getRGB());
					}
					else{
						newImg.setRGB(i,j,set.getRGB());
					}
				}
				//end of the loops
			}
		}
		
		//return the image
		try{
			File output = new File("greenscreen.jpg");
			ImageIO.write(newImg, "jpg", output);
		}
		catch (IOException ex4){
			ex4.printStackTrace();
		}
		
		System.out.println("greenscreen saved to your directory");
		
	}
	
}
