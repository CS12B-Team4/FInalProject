import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class FallingSquare extends JComponent {
    private int side = 30; //set the size of the square to be 30
    private int x;
    private int y = 0;
    private int speed;

    public FallingSquare() {
        setXCoordinate();
        setSpeed();
    }

    Random r = new Random();

    public int getXCoordinate() {
        return this.x;
    }

    public int getYCoordinate() {
        return this.y;
    }

    public int setXCoordinate() {
        x = r.nextInt(350 - 30);
        return x;
    }

    public int setSpeed() {
        speed = r.nextInt(9) + 1;
        // set the speed between 1 and 10
        return speed;
    }

    /*
    Set the movement of the square
    When the square is inside the window, change y-coordinate and make it fall.
    When the square reaches the bottom, reset its locataion and speed.
    */
    public void update() {
        if (y <= 550) {
            y += speed;
        }

        if (y >= 550) {
            setXCoordinate();
            y = -30;
            setSpeed();
        }

    }

    public void paint(Graphics g) {
        g.setColor(new Color(255, 150, 150));
        g.fillRect(x, y, 30, 30);
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(x, y, 30, 30);
    }

}
