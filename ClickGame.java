package PA05;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.ArrayList;

public class ClickGame extends JPanel implements MouseListener{

  ArrayList<FallingSquare> squareInfo = new ArrayList<FallingSquare>();
  static int result=0;
  FallingSquare fallingSquare = new FallingSquare();


  public ClickGame(){
    addMouseListener(this);
    squareInfo.add(fallingSquare);
  }



  public void paint(Graphics graphics) {
      //paints square objects to the screen
    for (FallingSquare squareArray : squareInfo) {
        squareArray.paint(graphics);
    }

  }

  public void update() {
    //calls the Square class update method on the square objects
    for (FallingSquare squareArray : squareInfo) {
      squareArray.update();
    }
  }




    public void mousePressed(MouseEvent evt) {
      int mx = evt.getX();  // x-coordinate where user clicked.
      int my = evt.getY();  // y-coordinate where user clicked.
      for(int i = 0;i < squareInfo.size();i++){
        int theX = squareInfo.get(i).getXCoordinate();
        int theY = squareInfo.get(i).getYCoordinate();
        if (((theX<mx)&&(mx<theX+30)) && ((theY<my)&&(my<theY+30))){
          result++;
          System.out.println(squareInfo.get(i).toString());
        }

      }

    }

    public void mouseEntered(MouseEvent evt) { }
    public void mouseExited(MouseEvent evt) { }
    public void mouseClicked(MouseEvent evt) { }
    public void mouseReleased(MouseEvent evt) { }




    public static void main(String[] args)throws InterruptedException {
        ClickGame drawingArea = new ClickGame();
        JFrame frame = new JFrame("ClickGame");
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        JButton stopB = new JButton("Click to see your score!");
        JLabel scoreL = new JLabel("Your current score is 0");
        JLabel rateL = new JLabel("Please rate this game");
        JTextArea textTA = new JTextArea("\nTry to click the falling squares and earn points.\n");
        JSlider scoreJS = new JSlider(0,10);
        scoreJS.setMajorTickSpacing(10);
        scoreJS.setMinorTickSpacing(1);
        scoreJS.setPaintTicks(true);
        scoreJS.setPaintLabels(true);
        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(0,2));

        JCheckBox likeCB = new JCheckBox("You like the game.");
        likeCB.setMnemonic(KeyEvent.VK_C);
        likeCB.setSelected(false);
        JCheckBox dislikeCB = new JCheckBox("You don't like the game.");
        dislikeCB.setMnemonic(KeyEvent.VK_D);
        dislikeCB.setSelected(false);

        bottom.add(stopB);
        bottom.add(scoreL);

        bottom.add(rateL);
        bottom.add(scoreJS);
        bottom.add(likeCB);
        bottom.add(dislikeCB);

        content.add(textTA,BorderLayout.NORTH);
        content.add(bottom,BorderLayout.SOUTH);
        drawingArea.setBackground(Color.BLACK);

        stopB.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent event){
                String response = "Your current score is "+result;
                scoreL.setText(response);

            }
        });


        frame.setContentPane(content);
        frame.add(drawingArea);
        frame.setSize(370,700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);


        while (true) {
            drawingArea.update();
            drawingArea.repaint();
            Thread.sleep(16);
        }


  }




}
