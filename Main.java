/*
 * File: Main.java
 * Author: David Neufeld
 * Created Date: Fri Dec 03 2021 at 4:24:09 PM
 * E-mail: david.neufeld@maine.edu
 * Description:
 * 
 * Collaboration: 
 * 
 */
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
public class Main {
    public static void main(String[] args) throws IOException{
        //Vant vants[] = {new Vant(spot, facing);
        Scanner s = new Scanner(System.in);
        //File file = new File(s.nextLine());
        File file = new File("demo.txt");       //Change this string to change starting condition file
        s.close();
        Scanner r = new Scanner(file);
        String[] dimentions = r.nextLine().split("x");
        int width=Integer.parseInt(dimentions[0]);
        int height=Integer.parseInt(dimentions[1]);
        Grid grid = new Grid(width,height,r);JFrame theGUI = new JFrame();
        theGUI.setBackground(Color.BLACK);
        theGUI.setTitle("Vants");
        theGUI.setSize(800,800);
        theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = theGUI.getContentPane();
        pane.add(grid);
        theGUI.setVisible(true);
        r.close();
    }
}
