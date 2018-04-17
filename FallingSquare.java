package PA05;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.Random;

public class FallingSquare{
  private int side = 30; //set the size of the square to be 30
  public int x;
  public int y = 0;
  private int speed;

  public FallingSquare(){
    setXCoordinate();
    setSpeed();
  }

  Random r = new Random();

  public int setXCoordinate(){
    x  = r.nextInt(370-30);
    return x;
  }

  public int setSpeed(){
    speed = r.nextInt(9)+1;
    // set the speed between 1 and 10
    return speed;
  }



  /*
  Set the movement of the square
  When the square is inside the window, change y-coordinate and make it fall.
  When the square reaches the bottom, reset its locataion and speed.
  */
  public void update(){
    if(y <= 450){
        y += speed;
    }

    if(y >= 450){
        setXCoordinate();
        y = -30;
        setSpeed();
    }

  }

  public int getXCoordinate() {
    return this.x;
  }

  public int getYCoordinate() {
    return this.y;
  }

  public int getSpeed(){
    return this.speed;
  }

  public void paint(Graphics g){
    g.setColor(new Color(255,150,150));
    g.fillRect(x,y,30,30);
    g.setColor(Color.LIGHT_GRAY);
    g.drawRect(x,y,30,30);
  }

  public String toString(){
    return "Congrats! You clicked a square with a speed of "+this.speed;
  }


}
