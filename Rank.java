package FinalProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.File;

public class Rank{

  HashMap<String,ArrayList<Integer>> history = new HashMap<String,ArrayList<Integer>>();
  TreeMap<Integer,String> highestScore = new TreeMap<Integer,String>();
  JTable table;

  public static void main(String[] args){

    JPanel content = new JPanel();
    Rank rankChart = new Rank();
    JScrollPane scrollpane = new JScrollPane(rankChart.table);
    content.add(scrollpane);

    JFrame window = new JFrame("Rank");
    window.setContentPane(content);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setLocation(120,70);
    window.pack();
    window.setVisible(true);
  }

  public Rank(){
    loadData();
    sortAndStoreHighest();
    String[] columnNames = {"Rank","Player Name","Score"};
    Object[][] data = getRankings();
    this.table = new JTable(data,columnNames);
    this.table.setEnabled(false);
  }

  public void loadData(){

    ArrayList<Integer> scores;

    try{

      File rank = new File("rank.txt");
      Scanner scanner = new Scanner(rank);
      while (scanner.hasNextLine()){

        String[] words = scanner.nextLine().split(" +");
        if (!history.containsKey(words[0])){
          scores = new ArrayList<Integer>();
        } else {
          scores = history.get(words[0]);
        }
        scores.add(Integer.parseInt(words[1]));
        history.put(words[0],scores);

      }
    } catch(Exception e) {
      System.out.println("Exception:  "+e);
    }
  }

  public void sortAndStoreHighest(){

    for (Map.Entry<String, ArrayList<Integer>> entry : history.entrySet()) {
      String name = entry.getKey();
      ArrayList<Integer> scores = entry.getValue();
      Collections.sort(scores,Collections.reverseOrder());
      highestScore.put(scores.get(0),name);
    }
  }

  public Object[][] getRankings(){

    Object[][] data = new Object[20][3];
    Set<Integer> keys = highestScore.descendingKeySet();
    int index = 0;
    for (int scores: keys){
      data[index][0] = index+1;
      data[index][1] = highestScore.get(scores);
      data[index][2] = scores;
      index++;
    }
    return data;
  }


}
