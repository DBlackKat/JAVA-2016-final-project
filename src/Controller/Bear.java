package Controller;

import java.awt.*;

/**
 * Created by theblackcat on 12/5/17.
 */
public class Bear extends EnvironmentElement{
    private int life;
    private int oxygen;
    private int sleep;
    private Point position;
    public Bear(){
        this.sleep  = 9;
        this.oxygen = 100;
        this.life = 100;
    }

    public void setPosition(Point coordinate){
        this.position = coordinate;
    }
    public Point getPosition(){
        return position;
    }

    public static void main(String []args){
        //TODO 測試code寫這裡
    }
}
