package FinalProject;
import java.io.*;

public class Game{

  public static void main(String[] args) throws InterruptedException{

    try {
      Passwords.userInformation();
    } catch (FileNotFoundException e){}



    while(true){

      if (Passwords.getMatch()==true) {
        try{ClickGame.runGame();} catch (InterruptedException e){}
        break;

      }
    }

  }
}
