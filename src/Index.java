import audio.BackgroundMusic;
import environment.EnvironmentElement;
import sun.plugin2.message.GetAppletMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by theblackcat on 27/4/17.
 */
public class Index extends JFrame {
    private BackgroundMusic music = new BackgroundMusic("This_Game.wav");
    private GameOption gameOption;
    private Start startGame;
    public Index(){
        music.start();
        startGame = new Start(this);
        gameOption = new GameOption(this);
        this.add(startGame);
    }

    public void stopMusic(){
        music.stop();
    }
    public void startMusic(){
        music.start();
    }

    public static void main(String []args){
        Index frame = new Index();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((int)(1191*1.1),(int)(670*1.1));
        frame.setVisible(true);
    }
    public void showOption()
    {
        this.add(gameOption);
        startGame.setVisible(false);
        gameOption.setVisible(true);
    }
    public void showMenu()
    {
        gameOption.setVisible(false);
        startGame.setVisible(true);
    }
}
