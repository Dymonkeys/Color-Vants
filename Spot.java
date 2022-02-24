/*
 * File: Spot.java
 * Author: David Neufeld
 * Created Date: Fri Dec 03 2021 at 3:57:49 PM
 * E-mail: david.neufeld@maine.edu
 * Description:
 * 
 * Collaboration: 
 * 
 */
import java.awt.*;
public class Spot {
    Spot up;
    Spot down;
    Spot left;
    Spot right;
    int state;
    int totalStates;
    public Spot(int totalStates){
        this.state=0;
        this.totalStates=totalStates;
    }
    public void progressState(){
        state++;
        if(state==totalStates) state=0;
    }
    public void regressState(){
        state--;
        if(state==-1) state=totalStates-1;
    }
    public Color getColor(){
        if(state==1) return Color.white;
        if(state==2) return Color.red;
        if(state==3) return Color.blue;
        if(state==4) return Color.green;
        if(state==5) return Color.orange;
        if(state==6) return Color.yellow;
        if(state==7) return Color.pink;
        if(state==8) return Color.cyan;
        return Color.darkGray;
    }
}
