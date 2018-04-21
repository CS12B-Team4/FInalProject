package FinalProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import java.beans.*;
import java.io.*;


public class Passwords extends JDialog{

  static TreeMap<String, String> userInfo = new TreeMap<String, String>();
  static TreeMap<String, String> userCheck = new TreeMap<String, String>();
  static JPasswordField password = new JPasswordField();
  static String nameS;
  static boolean match = false;


  public Passwords() throws FileNotFoundException{


    JPanel content = new JPanel();

    JButton setInfo = new JButton("Create Player Account");

    setInfo.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
          JTextField name = new JTextField();
          JPasswordField password = new JPasswordField();
          JComponent[] inputs = new JComponent[] {
            new JLabel("Name:"),
            name,
            new JLabel("Password:"),
            password
          };
          int result = JOptionPane.showConfirmDialog(null, inputs, "My custom dialog", JOptionPane.PLAIN_MESSAGE);
          if (result == JOptionPane.OK_OPTION) {

            try {
              FileWriter file = new FileWriter("Results.txt",true);
              String myPass = String.valueOf(password.getPassword());
              userInfo.put(name.getText(), myPass);
              nameS = name.getText();
              file.write(name.getText()+"  "+myPass);
              file.close();
              name.setText(" ");
              match = true;

            } catch(IOException e) {}
          }

        }
      });

    JButton logIn = new JButton("Log in");
    logIn.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
          loadPassword();
          JTextField name = new JTextField();
          JPasswordField password = new JPasswordField();
          JComponent[] inputs = new JComponent[] {
            new JLabel("Name:"),
            name,
            new JLabel("Password:"),
            password
          };

          int result = JOptionPane.showConfirmDialog(null, inputs, "My custom dialog", JOptionPane.PLAIN_MESSAGE);
          if (result == JOptionPane.OK_OPTION){
            String myPass= String.valueOf(password.getPassword());
            if (userCheck.containsKey(name.getText()) && userCheck.get(name.getText()).equals(myPass)){
              match = true;
              Rank rankChart = new Rank();
              JPanel content = new JPanel();
              JScrollPane scrollpane = new JScrollPane(rankChart.table);
              content.add(scrollpane);

              JFrame window = new JFrame("Rank");
              window.setContentPane(content);
              window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              window.setLocation(120,70);
              window.pack();
              window.setVisible(true);

            }
          }
        }
      });


    content.add(setInfo);
    content.add(logIn);

    JFrame window = new JFrame("Rank");
    window.setContentPane(content);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setLocation(120,70);
    window.pack();
    window.setVisible(true);

    return match;

  }




  public static void loadPassword(){

    try{

      File password = new File("Results.txt");
      Scanner scanner = new Scanner(password);
      while (scanner.hasNextLine()){

        String[] words = scanner.nextLine().split(" +");

        userCheck.put(words[0],words[1]);


      }
    } catch(Exception e) {
      System.out.println("Exception:  "+e);
    }


  }





}
