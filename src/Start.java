import audio.BackgroundMusic;
import javafx.scene.layout.Background;
import os.CheckOs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Start extends JPanel implements ActionListener{
    BackgroundMusic music;
    JButton start;
    JButton option;
    Index index;
    private String backgroundImage = CheckOs.getImagePath()+"start_img.jpg";

    public Start(Index index){
        this.index = index;
        start = new JButton("Start");
        start.addActionListener(this);
        option = new JButton("Option");
        option.addActionListener(this);
        GridLayout experimentLayout = new GridLayout(2,1,20,20);

        this.setLayout(new FlowLayout(FlowLayout.RIGHT,30,30));

        JPanel menu = new JPanel(){
            public void paintComponent(Graphics g){
                g.setColor(Color.BLACK);
                g.fillRect(0,0,getWidth(),getHeight());
            }
        };
        start.setPreferredSize(new Dimension(100,50));
        option.setPreferredSize(new Dimension(100,50));
        
        menu.setLayout(experimentLayout);
        menu.add(start);
        menu.add(option);
        this.add(menu);

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Image image = new ImageIcon(backgroundImage).getImage();
        g.drawImage(image,0,0,getWidth(),getHeight(),null);
    }
    public void actionPerformed( ActionEvent event)
    {
        if(event.getSource()==start)
        {
            String []args = new String[2];
            TestAnimation.main(args);
        }
        else if(event.getSource()==option)
        {
            index.showOption();
        }
    }
}
