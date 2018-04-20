import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ClickGame extends JPanel {
    int result = 0;
    ArrayList<Integer> scores;
    private boolean playing = false;
    private boolean menu = true;
    private boolean end = false;
    private FallingSquare[] squares = new FallingSquare[15];

    public ClickGame() {
        scores = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            squares[i] = new FallingSquare();
        }

    }


    public void paint(Graphics graphics) {
        //paints square objects to the screen
        for (FallingSquare squareArray : squares) {
            squareArray.paint(graphics);
        }

    }

    public void update() {
        //calls the Square class update method on the square objects
        for (FallingSquare squareArray : squares) {
            squareArray.update();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        final Font defaultFont = new Font("Serif", Font.ITALIC, 16);
        ClickGame drawingArea = new ClickGame();
        JFrame frame = new JFrame("ClickGame");
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        JButton startB = new JButton("START");
        JLabel welcomeLabel = new JLabel("Welcome to a simple game!");
//        welcomeLabel.setBackground(Color.MAGENTA);
        welcomeLabel.setFont(defaultFont);
        startB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (drawingArea.menu) {
                    drawingArea.playing = true;
                    drawingArea.menu = false;
                    content.remove(welcomeLabel);
                    content.add(drawingArea, BorderLayout.CENTER);
                    content.revalidate();
                    content.repaint();
                } else if (!drawingArea.playing) {
                    drawingArea.end = true;
                }

            }
        });
        JButton pauseB = new JButton("PAUSE");
        pauseB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingArea.playing = !drawingArea.playing;
            }
        });


        JLabel titleCard = new JLabel("A simple Game");
        titleCard.setOpaque(true);
        titleCard.setBackground(Color.MAGENTA);
        titleCard.setHorizontalAlignment(JLabel.CENTER);
        titleCard.setFont(new Font("Verdana", Font.BOLD, 30));


        JPanel side = new JPanel();
        side.setLayout(new GridLayout(2, 0));
        side.add(startB);
        side.add(pauseB);
        content.add(side, BorderLayout.WEST);
        drawingArea.setBackground(Color.BLACK);
//      set up drawing area event listeners
        drawingArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int mx = evt.getX();  // x-coordinate where user clicked.
                int my = evt.getY();  // y-coordinate where user clicked.
                for (int i = 0; i < 15; i++) {
                    int theX = drawingArea.squares[i].getXCoordinate();
                    int theY = drawingArea.squares[i].getYCoordinate();
                    if (((theX < mx) && (mx < theX + 30)) && ((theY < my) && (my < theY + 30))) {
                        drawingArea.result++;
                        System.out.println("hit");
                    }
                }
            }
        });

        content.add(welcomeLabel, BorderLayout.CENTER);


        content.add(titleCard, BorderLayout.NORTH);
        frame.setContentPane(content);
        frame.setSize(350, 650);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);

        while (true) {
            if (drawingArea.playing) {
                drawingArea.update();
                drawingArea.repaint();
                Thread.sleep(10);
            } else {
                Thread.sleep(10);
                if (drawingArea.end) {
                    content.remove(drawingArea);
                    JTextArea resultArea = new JTextArea("Your score is " + drawingArea.result + ". Try again in 5 seconds!\n\n");
                    drawingArea.scores.add(drawingArea.result);
                    resultArea.setFont(defaultFont);
                    resultArea.append("**HIGHSCORES**\n");
                    for (int i = 1; i <= drawingArea.scores.size(); i++){
                        resultArea.append(String.valueOf(i) + " : " + drawingArea.scores.get(i - 1) + "\n");
                    }
                    content.add(resultArea, BorderLayout.CENTER);
                    content.revalidate();
                    content.repaint();
                    drawingArea.result = 0;
                    drawingArea.end = false;
                    drawingArea.menu = true;
                    Thread.sleep(5000);
                    content.remove(resultArea);
                    content.add(welcomeLabel, BorderLayout.CENTER);
                    content.revalidate();
                    content.repaint();
                }
            }
        }

    }
}
