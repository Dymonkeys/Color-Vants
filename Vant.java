import java.util.Random;

/*
 * File: Vant.java
 * Author: David Neufeld
 * Created Date: Fri Dec 03 2021 at 4:29:32 PM
 * E-mail: david.neufeld@maine.edu
 * Description:
 * 
 * Collaboration: 
 * 
 */
public class Vant {
    int facing;
    Spot spot;
    int[] mapping;
    boolean anti;
    public Vant(Spot spot, int facing,int[] mapping){
        this.spot=spot;
        this.facing=facing;
        this.mapping=mapping;
    }
    public Vant(Spot spot, int facing,int seed){
        this.spot=spot;
        this.facing=facing;
        mapping = new int[spot.totalStates];
        randomizeMapping(seed);
    }
    public void run(){
        //move in direction facing
        if(facing==0) spot=spot.up;
        if(facing==1) spot=spot.right;
        if(facing==2) spot=spot.down;
        if(facing==3) spot=spot.left;
        if(anti){
            spot.regressState();
            mappingRotate();
        } else{
            mappingRotate();
            spot.progressState();
        }
        //rotate depending on square
        //mappingRotate();
        //turnLeft();
        //progress spot state
        //if(anti) spot.regressState();
        //else spot.progressState();
    }
    public void mappingRotate(){
        for(int i=0;i<mapping.length;i++){
            if (i==spot.state){
                if(mapping[i]==0) turnLeft();
                if(mapping[i]==1) turnRight();
                break;
            }
        }
    }
    public void setMapping(int[] inp){
        mapping = inp;
    }
    public void randomizeMapping(int seed){
        Random r = new Random(seed);
        for(int i=0;i<mapping.length;i++){
            mapping[i]=r.nextInt(2);
        }
    }
    public void setDedaultMapping(){
        for(int i=0;i<mapping.length;i++){
            mapping[i]=i%2;
        }
    }
    public int[] mirrorMapping(){
        int[] out = new int[mapping.length];
        for(int i=0;i<mapping.length;i++){
            if(mapping[i]==1) out[i]=0;
            else if(mapping[i]==0) out[i]=1;
            else out[i]=mapping[i];
            //out[i]=mapping[i];
        }
        return out;
    }
    public int[] shiftMapping(){
        int[] out = new int[mapping.length];
        int temp=out[out.length-1];
        for(int i=out.length-1;i>0;i--){
            out[i]=out[i-1];
        }
        out[0]=temp;
        return out;
    }
    public Vant antiVant(Spot spot, int facing){
        Vant out = new Vant(spot, facing, mapping);
        out.anti=!this.anti;
        return out;
    }
    public Vant mirrorVant(Spot spot, int facing){
        Vant out = new Vant(spot, facing, mirrorMapping());
        out.anti=!anti;
        return out;
    }
    public Vant copy(Spot spot, int facing){
        Vant out = new Vant(spot, facing, mapping);
        out.anti=anti;
        return out;
    }
    public void polarityRotate(){
        //if(spot.state==2){
        //    turnLeft();
        //    turnLeft();
        //    return;
        //}
        if(spot.state%2==0) turnRight();
        else turnLeft();
    }
    public void turnRight(){
        facing++;
        if(facing==4) facing=0;
    }
    public void turnLeft(){
        facing--;
        if(facing==-1) facing=3;
    }
    public void printMapping(){
        System.out.print("[");
        for(int i=0;i<mapping.length-1;i++){
            System.out.print(mapping[i]+",");
        }
        System.out.println(mapping[mapping.length-1]+"]");
    }
}
