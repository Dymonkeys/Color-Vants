import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;

/*
 * File: Grid.java
 * Author: David Neufeld
 * Created Date: Fri Dec 03 2021 at 3:52:30 PM
 * E-mail: david.neufeld@maine.edu
 * Description:
 * 
 * Collaboration: 
 * 
 */
public class Grid extends JPanel{
    Spot[][] spaces;
    int width;
    int height;
    ArrayList<Vant> vants;
    public Grid(int width, int height,Scanner r){
        this(width,height);
        //vants.add(new Vant(spot, facing, seed))
        int colorNum=r.nextInt();
        r.nextLine();
        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                spaces[x][y].totalStates=colorNum;
            }
        }
        r.nextLine();
        vants=new ArrayList<Vant>();
        while(r.hasNextLine()){
            //String[] line=r.nextLine().split("|");
            int x = r.nextInt();
            int y = r.nextInt();
            r.next();
            int facing = r.nextInt();
            boolean anti;
            if(r.nextInt()!=0) anti=true;
            else anti=false;
            r.next();
            int[] mapping = new int[colorNum];
            for(int i=0;i<colorNum;i++){
                mapping[i]=r.nextInt();
            }
            Vant v = new Vant(spaces[x][y], facing, mapping);
            v.anti=anti;
            vants.add(v);
        }
        //vants.add(new Vant(spaces[26][26], 0, new int[] {0,0,1,0,0,1}));
        //vants.add(vants.get(0).antiVant(spaces[25][26], 1));
        //vants.add(vants.get(0).copy(spaces[26][25], 2));
        //vants.add(vants.get(2).antiVant(spaces[25][25], 3));
    }
    public Grid(int width, int height){
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 0, 1);
        this.width=width;
        this.height=height;
        spaces = new Spot[width][height];
        //initialize spots
        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                spaces[x][y] = new Spot(6);
            }
        }
        //set up pointers
        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                Spot spot = spaces[x][y];
                //Left Right pointers
                if(x==0){
                    spot.left=spaces[width-1][y];
                    spot.right=spaces[1][y];
                } else if(x==width-1){
                    spot.left=spaces[width-2][y];
                    spot.right=spaces[0][y];
                } else{
                    spot.left=spaces[x-1][y];
                    spot.right=spaces[x+1][y];

                }
                //Up down pointers
                if(y==0){
                    spot.up=spaces[x][height-1];
                    spot.down=spaces[x][1];
                } else if(y==height-1){
                    spot.up=spaces[x][height-2];
                    spot.down=spaces[x][0];
                } else{
                    spot.up=spaces[x][y-1];
                    spot.down=spaces[x][y+1];

                }
            }
        }
        //vants = new Vant[4];
        //Random r = new Random();
        //int seed = r.nextInt();
        //vants[0]=new Vant(spaces[26][26], 0, new int[] {0,0,1,0,0,1});
        //vants[1]=vants[0].antiVant(spaces[25][26], 1);
        //vants[2]=vants[0].copy(spaces[26][25], 2);
        //vants[3]=vants[2].antiVant(spaces[25][25], 3);
        //System.out.println(seed);
        //vants[0].printMapping();
        
        
    }
    public void wait(int waitTime){
        long start=System.nanoTime();
        while(System.nanoTime()>start+waitTime){

        }
    }
    int count=0;
    public void run(){
        //count++;
        //vants[0].run();
        //if(count>50) vants[1].run();
        for(int i=0;i<vants.size();i++){
            vants.get(i).run();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        // Auto-generated method stub
        run();
        super.paintComponent(g);
        double pixelSize;
        if(this.getBounds().height<this.getBounds().width)
            pixelSize=this.getBounds().height/height;
        else pixelSize=this.getBounds().width/width;
        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                g.setColor(spaces[x][y].getColor());
                g.fillRect((int)(x*pixelSize), (int)(y*pixelSize), (int)(pixelSize), (int)(pixelSize));
                g.setColor(Color.BLACK);
                //g.drawRect(x*PixelWidth, y*PixelHeight, PixelWidth, PixelHeight);
            }   
        } 
    }
}
