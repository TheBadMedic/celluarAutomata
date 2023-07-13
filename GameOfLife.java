import java.util.Scanner;  // Needed for the Scanner class
//import java.io.*;

//auto screeshot

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
public class GameOfLife
{
   public static void main(String[] args) //throws IOException
   {
      // Create a Scanner object to read input.
      Scanner keyboard = new Scanner(System.in);
      
      //settings
      boolean screenshot = false; //take a screenschot of each generation
      String filePath = "C:/Users/User/Desktop/codescreenie/generation_"; //file path to put the screenshots
      
      int gridWidth = 80; //width of display
      int gridHeight = 80; //height of display
      int displayFrom = 0; //skip to this generation
      int endGeneration = 100; //automatically end after this generation
      //initial generation
      int[][] initialData = { { 0,0,0,0,0,0,0,0,0,0 },
                              { 0,0,0,0,0,0,0,0,0,0 },
                              { 0,0,0,0,0,0,0,0,0,0 },
                              { 0,0,0,0,0,0,0,0,0,0 },
                              { 0,0,0,0,1,1,0,0,0,0 },
                              { 0,0,0,1,9,5,9,6,0,0 },
                              { 0,0,0,0,0,1,7,0,0,0 },
                              { 0,0,0,0,0,0,0,0,0,0 },
                              { 0,0,0,0,0,0,0,0,0,0 },
                              { 0,0,0,0,0,0,0,0,0,0 }, };
                              
      
      int numNeighbor,period=1,toggle=1;
      int widthOffset = 0, heightOffset = 0;
      int[][][] gridData = new int[2][gridWidth][gridHeight];
      
  

             //initial generatio templates
             /*               empty
                            { { 0,0,0,0,0,0,0,0,0,0 },
                              { 0,0,0,0,0,0,0,0,0,0 },
                              { 0,0,0,0,0,0,0,0,0,0 },
                              { 0,0,0,0,0,0,0,0,0,0 },
                              { 0,0,0,0,0,0,0,0,0,0 },
                              { 0,0,0,0,0,0,0,0,0,0 },
                              { 0,0,0,0,0,0,0,0,0,0 },
                              { 0,0,0,0,0,0,0,0,0,0 },
                              { 0,0,0,0,0,0,0,0,0,0 },
                              { 0,0,0,0,0,0,0,0,0,0 }, };
                              
                              conway glider gun
                            { { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0 },
                              { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0  },
                              { 0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0  },
                              { 0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0  },
                              { 1,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0  },
                              { 1,1,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,1,1,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0  },
                              { 0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0  },
                              { 0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0  },
                              { 0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0  },
                              { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0  }, };   
                                                        
             */   
                           
     //copy initial data into main grid
      for(int height = 0; height < initialData[0].length ; height++)
      {
         for(int width = 0; width < initialData.length ; width++)
         {
            gridData[0][gridData[0].length / 2 - initialData[0].length/2 + height + widthOffset][gridData[0][0].length / 2 - initialData.length/2 + width + heightOffset] = initialData[width][height];
         }
      }
      
      //disturbute random 9s into grid 
      /*
      for(int amt = 0 ; amt < 15 ; amt++)
      {
         gridData[0][(int)(Math.random()*60+10)][(int)(Math.random()*70+5)]=9;
      
      } */
      
     //main loop
      while(period<=250)
      {
         if(displayFrom>0)
            displayFrom--;
      //display
         toggle = (toggle==0) ? 1 : 0;
         for(int height = 1; height < gridData[0][0].length-2 ; height++)
         {  
         
            if(displayFrom==0)
               System.out.println("|");
            for(int width = 1; width < gridData[0].length-2 ; width++)
            {  
            
               if(displayFrom==0)
               {  //tempretureShadingDisplay
                  if(gridData[toggle][width][height]==0)
                     System.out.print("  ");
                  else if(gridData[toggle][width][height]==1)
                     System.out.print("- ");
                  else if(gridData[toggle][width][height]==2)
                     System.out.print("= ");
                  else if(gridData[toggle][width][height]==3)
                     System.out.print("+ ");
                  else if(gridData[toggle][width][height]==4)
                     System.out.print("o ");
                  else if(gridData[toggle][width][height]==5)
                     System.out.print("O ");
                  else if(gridData[toggle][width][height]==6)
                     System.out.print("0 ");
                  else if(gridData[toggle][width][height]==7)
                     System.out.print("% ");
                  else if(gridData[toggle][width][height]==8)
                     System.out.print("# ");
                  else if(gridData[toggle][width][height]==9)
                     System.out.print("@ ");
                  else
                     System.out.print("E");
               
               /* tempretureValueDisplay
               if(gridData[toggle][width][height]==0)
               System.out.print("   ");
               else
               System.out.print("["+gridData[toggle][width][height]+"]");
               *
               
               /* conwayDisplay
               if(gridData[toggle][width][height])
               System.out.print("|||");
               else
               System.out.print("   ");*/
               }
              //calculate   
               numNeighbor = 0;
               numNeighbor += gridData[toggle][width-1][height];
               numNeighbor += gridData[toggle][width+1][height];
               numNeighbor += gridData[toggle][width-1][height-1];
               numNeighbor += gridData[toggle][width  ][height-1];
               numNeighbor += gridData[toggle][width+1][height-1];
               numNeighbor += gridData[toggle][width-1][height+1];
               numNeighbor += gridData[toggle][width  ][height+1];
               numNeighbor += gridData[toggle][width+1][height+1];
            
            
            //rules----------------------------------------------------------
               if (gridData[toggle][width][height] > 5 && numNeighbor > 5)
                  gridData[(toggle==0) ? 1 : 0][width][height]=gridData[toggle][width][height];
               else if(numNeighbor>=4&&numNeighbor<=9)
                  gridData[(toggle==0) ? 1 : 0][width][height]=gridData[toggle][width][height]+1;
               else if(numNeighbor>9)
                  gridData[(toggle==0) ? 1 : 0][width][height] = 0;
               else
                  gridData[(toggle==0) ? 1 : 0][width][height]=gridData[toggle][width][height]-1;
             //--------------------------------------------------------------     
               
               /* rulebook
            conway
                        if(numNeighbor==3)
            gridData[(toggle==0) ? 1 : 0][width][height]=1;
            else if(gridData[toggle][width][height] && numNeighbor==2)
            gridData[(toggle==0) ? 1 : 0][width][height]=1;
            else
            gridData[(toggle==0) ? 1 : 0][width][height]=0;
            
            crystal
                        if(gridData[toggle][width][height]==1&&numNeighbor>1)
            gridData[(toggle==0) ? 1 : 0][width][height]=0;
            else if(numNeighbor>=2 && numNeighbor<=3)
            gridData[(toggle==0) ? 1 : 0][width][height]=1;
            else
            gridData[(toggle==0) ? 1 : 0][width][height]=0;
            
            lines
               if (gridData[toggle][width][height] > 5 && numNeighbor > 5)
                  gridData[(toggle==0) ? 1 : 0][width][height]=gridData[toggle][width][height];
               else if(numNeighbor>=4&&numNeighbor<=9)
                  gridData[(toggle==0) ? 1 : 0][width][height]=gridData[toggle][width][height]+1;
               else if(numNeighbor>9)
                  gridData[(toggle==0) ? 1 : 0][width][height] = 0;
               else
                  gridData[(toggle==0) ? 1 : 0][width][height]=gridData[toggle][width][height]-2;
                
                pulse
                               if((gridData[toggle][width][height]== 0 && numNeighbor>=9) || numNeighbor == 4)
                  gridData[(toggle==0) ? 1 : 0][width][height]=9;
               else if(numNeighbor>0)
                  gridData[(toggle==0) ? 1 : 0][width][height]=gridData[toggle][width][height]-1;  
                  
                  slime
                                 if (gridData[toggle][width][height] == numNeighbor)
                  gridData[(toggle==0) ? 1 : 0][width][height] = gridData[toggle][width][height]-3;
               else if(numNeighbor>=6 && numNeighbor<=9)
                  gridData[(toggle==0) ? 1 : 0][width][height] = gridData[toggle][width][height]+1;
               else if(gridData[toggle][width][height] > 2 && numNeighbor>=3 && numNeighbor<=12)
                  gridData[(toggle==0) ? 1 : 0][width][height] = gridData[toggle][width][height]+1;
               else
                  gridData[(toggle==0) ? 1 : 0][width][height] = gridData[toggle][width][height]-2;
                  
                  space filling
                  
                                 if (gridData[toggle][width][height] == 0 && numNeighbor <= 9)
                  gridData[(toggle==0) ? 1 : 0][width][height] = numNeighbor;
               else if(numNeighbor<=9)
                  gridData[(toggle==0) ? 1 : 0][width][height] = gridData[toggle][width][height]-1;
                  else
                  gridData[(toggle==0) ? 1 : 0][width][height] = gridData[toggle][width][height];
                  
                  windmill
                                 if (gridData[toggle][width][height]==0 && numNeighbor>2&&numNeighbor<5)
                  gridData[(toggle==0) ? 1 : 0][width][height] = numNeighbor-1;
               else if (gridData[toggle][width][height]>0 && numNeighbor > gridData[toggle][width][height])
                  gridData[(toggle==0) ? 1 : 0][width][height] = gridData[toggle][width][height]+1;
               else if (gridData[toggle][width][height]>0 && numNeighbor < gridData[toggle][width][height])
                  gridData[(toggle==0) ? 1 : 0][width][height] = gridData[toggle][width][height]-2;
               else
               gridData[(toggle==0) ? 1 : 0][width][height] = gridData[toggle][width][height];
               
               maze
               
                           if (gridData[toggle][width][height] == 0 &&numNeighbor==3)
            gridData[(toggle==0) ? 1 : 0][width][height]=1;
            else if (gridData[toggle][width][height] == 1 && numNeighbor>0 && numNeighbor<=5)
            gridData[(toggle==0) ? 1 : 0][width][height]=1;
            else if (gridData[toggle][width][height] == 1 && numNeighbor>5)
            gridData[(toggle==0) ? 1 : 0][width][height]=0;
            else 
            gridData[(toggle==0) ? 1 : 0][width][height]=0;
                  
            */
               
            //clamp value
               if(gridData[(toggle==0) ? 1 : 0][width][height]<0 || gridData[(toggle==0) ? 1 : 0][width][height]>9)
                  gridData[(toggle==0) ? 1 : 0][width][height] = 0;
            
            
                       
            }
         }
         if(displayFrom==0)
            System.out.println("generation:"+ period + "\n"+"---".repeat(gridData[0][0].length));
      
         period++;
      
      
      
      //delayloop 
         if(displayFrom==0)
         {   
         
            if(!(screenshot))
            { 
               try 
               {
                  Thread.sleep(1000);
               }
               catch(InterruptedException ex)
               {
                  Thread.currentThread().interrupt();
               }
            }
            else
            {
            
               try {
                  Thread.sleep(1500);
                  Robot r = new Robot();
               
               // It saves screenshot to desired path
                  String path = filePath + period + ".jpg";
               
               // Used to get ScreenSize and capture image
                  Rectangle capture = 
                     //new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                     new Rectangle(200,100,600,700);
                  BufferedImage Image = r.createScreenCapture(capture);
                  ImageIO.write(Image, "jpg", new File(path));
               //System.out.println("Screenshot saved");
               }
               catch (AWTException | IOException | InterruptedException ex) {
                  System.out.println(ex);
               }
            }
         }
      }
   }
}