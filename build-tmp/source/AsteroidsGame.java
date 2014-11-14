import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class AsteroidsGame extends PApplet {

SpaceShip ship;
Star[] stars;
ArrayList <Asteroid> asteroids;
boolean forward = false;
boolean backward = false;
boolean clockwise = false;
boolean counterclockwise = false;
public void setup() 
{
  size(800, 800);
  //frameRate(100);
  ship = new SpaceShip();
  stars = new Star[800];
  asteroids = new ArrayList <Asteroid>();
  for(int i = 0; i < stars.length; i++)
  {
    stars[i] = new Star();
  }
  for(int i = 0; i < 15; i++)
  {
    asteroids.add(new Asteroid());
  }
}
public void draw() 
{
  background(0);
  for(int i = 0; i < stars.length; i++)
  {
    stars[i].show();
  }
  ship.move();
  ship.show();
  for(int i = 0; i < asteroids.size(); i++)
  {
    asteroids.get(i).move();
    asteroids.get(i).show();
    if(dist(ship.getX(), ship.getY(), asteroids.get(i).getX(), asteroids.get(i).getY()) < 20)
    {
      asteroids.remove(i);
    }
  }
  if(forward == true)
  {
    ship.accelerate(0.1f);
  }
  if(backward == true)
  {
    ship.accelerate(-0.1f);
  }
  if(clockwise == true)
  {
    ship.rotate(5);
  }
  if(counterclockwise == true)
  {
    ship.rotate(-5);
  }
  
}
public void keyPressed()
{
  if(key == 'w')
  {
    forward = true;
  }
  if(key == 's')
  {
    backward = true;
  }
  if(key == 'd')
  {
    clockwise = true;
  }
  if(key == 'a')
  {
    counterclockwise = true;
  }
  if (key == ' ')
  {
    ship.setX((int)(Math.random() * 500));
    ship.setY((int)(Math.random() * 500));
    ship.setDirectionX(0);
    ship.setDirectionY(0);
    ship.setPointDirection((int)(Math.random() * 30));
  }
}
public void keyReleased()
{
  if(key == 'w')
  {
    forward = false;
  }
  if(key == 's')
  {
    backward = false;
  }
  if(key == 'd')
  {
    clockwise = false;
  }
  if(key == 'a')
  {
    counterclockwise = false;
  }
}
class Star
{
  private int myX, myY, mySize;
  public Star()
  {
    myX = (int)(Math.random() * 800);
    myY = (int)(Math.random() * 800);
    mySize = (int)(Math.random() * 3) + 1;
  }
  public void show()
  {
    strokeWeight(mySize);
    stroke(255, 255, 255, 100);
    point(myX, myY);
  }
}
class SpaceShip extends Floater  
{   
  public SpaceShip()
  {
    corners = 4;
    xCorners = new int[corners];
    yCorners = new int[corners];
    xCorners[0] = -10;
    yCorners[0] = -10;
    xCorners[1] = 16;
    yCorners[1] = 0;
    xCorners[2] = -10;
    yCorners[2] = 10;
    xCorners[3] = -6;
    yCorners[3] = 0;
    myColor = color(255);
    myCenterX = 400;
    myCenterY = 400;
    myDirectionX = 0;
    myDirectionY = 0;
    myPointDirection = 0;
  }
  public void setX(int x){myCenterX = x;}
  public int getX(){return (int)myCenterX;}
  public void setY(int y){myCenterY = y;}
  public int getY(){return (int)myCenterY;}
  public void setDirectionX(double x){myDirectionX = x;}
  public double getDirectionX(){return myDirectionX;}
  public void setDirectionY(double y){myDirectionY = y;}
  public double getDirectionY(){return myDirectionY;}
  public void setPointDirection(int degrees){myPointDirection = degrees;}
  public double getPointDirection(){return myPointDirection;}
}
class Asteroid extends Floater
{
  private double myRotationSpeed;
  public Asteroid()
  { 
    corners = 6;
    xCorners = new int[corners];
    yCorners = new int[corners];
    xCorners[0] = 12;
    yCorners[0] = 0;
    xCorners[1] = 9;
    yCorners[1] = 9;
    xCorners[2] = -3;
    yCorners[2] = 12;
    xCorners[3] = -12;
    yCorners[3] = 0;
    xCorners[4] = -6;
    yCorners[4] = -6;
    xCorners[5] = 3;
    yCorners[5] = -9;
    myColor = color(200);
    myCenterX = (int)(Math.random() * 800);
    myCenterY = (int)(Math.random() * 800);
    myDirectionX = 0;
    myDirectionY = 0;
    myPointDirection = 0;
    myRotationSpeed = (Math.random() * 3) - 1;
  }
  public void setX(int x){myCenterX = x;}
  public int getX(){return (int)myCenterX;}
  public void setY(int y){myCenterY = y;}
  public int getY(){return (int)myCenterY;}
  public void setDirectionX(double x){myDirectionX = x;}
  public double getDirectionX(){return myDirectionX;}
  public void setDirectionY(double y){myDirectionY = y;}
  public double getDirectionY(){return myDirectionY;}
  public void setPointDirection(int degrees){myPointDirection = degrees;}
  public double getPointDirection(){return myPointDirection;}
  public void move()
  {
    myPointDirection = myPointDirection + myRotationSpeed;

    //change the x and y coordinates by myDirectionX and myDirectionY       
    myCenterX = myCenterX + myRotationSpeed;    
    myCenterY = myCenterY + myRotationSpeed;
    //wrap around screen    
    if(myCenterX >width)
    {     
      myCenterX = 0;    
    }    
    else if (myCenterX<0)
    {     
      myCenterX = width;    
    }    
    if(myCenterY >height)
    {    
      myCenterY = 0;    
    }   
    else if (myCenterY < 0)
    {     
      myCenterY = height;    
    }   
  }
}
abstract class Floater //Do NOT modify the Floater class! Make changes in the SpaceShip class 
{   
  protected int corners;  //the number of corners, a triangular floater has 3   
  protected int[] xCorners;   
  protected int[] yCorners;   
  protected int myColor;   
  protected double myCenterX, myCenterY; //holds center coordinates   
  protected double myDirectionX, myDirectionY; //holds x and y coordinates of the vector for direction of travel   
  protected double myPointDirection; //holds current direction the ship is pointing in degrees    
  abstract public void setX(int x);  
  abstract public int getX();   
  abstract public void setY(int y);   
  abstract public int getY();   
  abstract public void setDirectionX(double x);   
  abstract public double getDirectionX();   
  abstract public void setDirectionY(double y);   
  abstract public double getDirectionY();   
  abstract public void setPointDirection(int degrees);   
  abstract public double getPointDirection(); 

  //Accelerates the floater in the direction it is pointing (myPointDirection)   
  public void accelerate (double dAmount)   
  {          
    //convert the current direction the floater is pointing to radians    
    double dRadians =myPointDirection*(Math.PI/180);     
    //change coordinates of direction of travel    
    myDirectionX += ((dAmount) * Math.cos(dRadians));    
    myDirectionY += ((dAmount) * Math.sin(dRadians));       
  }   
  public void rotate (int nDegreesOfRotation)   
  {     
    //rotates the floater by a given number of degrees    
    myPointDirection+=nDegreesOfRotation;   
  }   
  public void move ()   //move the floater in the current direction of travel
  {      
    //change the x and y coordinates by myDirectionX and myDirectionY       
    myCenterX += myDirectionX;    
    myCenterY += myDirectionY;     

    //wrap around screen    
    if(myCenterX >width)
    {     
      myCenterX = 0;    
    }    
    else if (myCenterX<0)
    {     
      myCenterX = width;    
    }    
    if(myCenterY >height)
    {    
      myCenterY = 0;    
    }   
    else if (myCenterY < 0)
    {     
      myCenterY = height;    
    }   
  }   
  public void show ()  //Draws the floater at the current position  
  {             
    fill(myColor);   
    stroke(myColor);    
    //convert degrees to radians for sin and cos         
    double dRadians = myPointDirection*(Math.PI/180);                 
    int xRotatedTranslated, yRotatedTranslated;    
    beginShape();         
    for(int nI = 0; nI < corners; nI++)    
    {     
      //rotate and translate the coordinates of the floater using current direction 
      xRotatedTranslated = (int)((xCorners[nI]* Math.cos(dRadians)) - (yCorners[nI] * Math.sin(dRadians))+myCenterX);     
      yRotatedTranslated = (int)((xCorners[nI]* Math.sin(dRadians)) + (yCorners[nI] * Math.cos(dRadians))+myCenterY);      
      vertex(xRotatedTranslated,yRotatedTranslated);    
    }   
    endShape(CLOSE);  
  }   
} 

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "AsteroidsGame" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
