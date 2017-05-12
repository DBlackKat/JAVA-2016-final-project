import audio.BackgroundMusic;
import os.CheckOs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user01 on 06/05/2017.
 */
public class GameOption extends JPanel implements ActionListener{
    JButton music;
    JButton back;
    Index index;
    private String backgroundImage = CheckOs.getImagePath()+"option_img.jpg";

    public GameOption(Index index){
        this.index = index;
        music = new JButton("Music On");
        music.addActionListener(this);
        back = new JButton("Back");
        back.addActionListener(this);
        GridLayout experimentLayout = new GridLayout(2,1,20,20);

        this.setLayout(new FlowLayout(FlowLayout.RIGHT,30,30));

        JPanel option = new JPanel(){
            public void paintComponent(Graphics g){
                g.setColor(Color.BLACK);
                g.fillRect(0,0,getWidth(),getHeight());
            }
        };
        music.setPreferredSize(new Dimension(100,50));
        back.setPreferredSize(new Dimension(100,50));

        option.setLayout(experimentLayout);
        option.add(music);
        option.add(back);
        this.add(option);

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Image image = new ImageIcon(backgroundImage).getImage();
        g.drawImage(image,0,0,getWidth(),getHeight(),null);
    }
    public void actionPerformed( ActionEvent event)
    {
        if(event.getSource()==back)
        {
            index.showMenu();
        }
        else if(event.getSource()==music)
        {
            if(music.getText()=="Music On")
            {
                music.setText("Music Off");
                index.stopMusic();
            }
            else
            {
                music.setText("Music On");
                index.startMusic();
            }
        }
    }
}
