package Animation.Wrapper;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;
import os.CheckOs;
import resource.Dimension;

import static javafx.application.Application.launch;

/**
 * Created by theblackcat on 11/5/17.
 */
public class BearAnimation extends Application{
    private ImageView imageView;
    private Image[] images;
    private static final int COLUMNS  =  1;
    private static final int COUNT    =  6;
    private static final int OFFSET_X =  0;
    private static final int OFFSET_Y =  0;
    private static final int WIDTH    = Dimension.bearWidth;
    private static final int HEIGHT   = Dimension.bearHeight;
    private Animation animation;
    private double transparency = 0.7;
    @Override
    public void start(Stage primaryStage) throws Exception{
        String imagePath = CheckOs.getAnimationResourcePath("Bear-right")+"Bear-";
        System.out.println(imagePath);
        images = new Image[6];
        for (int i = 0;i < images.length;i++)
            images[i] = new Image(imagePath+i+".png");
        imageView = new ImageView();
        imageView.setImage(images[5]);
        imageView.setX(0);
        imageView.setY(0);
        imageView.setFitHeight(92);
        imageView.setFitWidth(92);
        imageView.setPreserveRatio(true);
        Group root = new Group(imageView);
        Scene scene = new Scene(root, 600, 480);
        //
        //write your code here

        animation = new Animation(
                imageView,
                Duration.millis(113*6),
                COUNT, COLUMNS,
                OFFSET_X, OFFSET_Y,
                WIDTH, HEIGHT,imagePath,2
        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();

        Path path = new Path();
        path.getElements().add(new MoveTo(100,100));
        path.getElements().add(new LineTo(200,200));
        path.getElements().add(new LineTo(100,200));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(4000));
        pathTransition.setPath(path);
        pathTransition.setNode(imageView);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);
        pathTransition.play();

        //
        primaryStage.setTitle("Lab6");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
