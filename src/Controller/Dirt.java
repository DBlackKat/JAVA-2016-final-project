package Controller;

import java.awt.*;

/**
 * Created by theblackcat on 12/5/17.
 */

// TODO extends stone, sand, hard-rock
public class Dirt extends EnvironmentElement {
    private int amount;
    private Point position;
    public Dirt(){

    }

    public void setPosition(Point coordinate){
        this.position = coordinate;
    }
    public Point getPosition(){
        return position;
    }
}
