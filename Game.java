package FinalProject;
import java.io.*;

public class Game{

  public static void main(String[] args) throws InterruptedException{


    try {
      Passwords.userInformation();
    } catch (FileNotFoundException e){}



    if (!Passwords.match) {
      try{ClickGame.runGame();} catch (InterruptedException e){}

    }
  }
}
