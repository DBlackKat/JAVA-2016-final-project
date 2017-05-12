import Animation.Wrapper.Animation;
import Controller.Maze;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.Stage;
import javafx.util.Duration;
import os.CheckOs;
import resource.Dimension;

/**
 * Created by theblackcat on 11/5/17.
 */
public class TestAnimation extends Application implements EventHandler<MouseEvent>{
    private ImageView imageView;
    private Image[] images;
    private static final int COLUMNS  =  1;
    private static final int COUNT    =  6;
    private static final int OFFSET_X =  0;
    private static final int OFFSET_Y =  0;
    private static final int WIDTH    = 95;
    private static final int HEIGHT   = 172;
    private Animation animation;
    private Path path;
    private PathTransition pathTransition;
    private double transparency = 0.7;
    private int oldPathX = Dimension.width/2,oldPathY = Dimension.width/2;
    @Override
    public void start(Stage primaryStage) throws Exception{
        String imagePath = CheckOs.getAnimationResourcePath("Bear-right")+"Bear-";
        System.out.println(imagePath);
        images = new Image[6];
        for (int i = 0;i < images.length;i++)
            images[i] = new Image(imagePath+i+".png");
        imageView = new ImageView();
        imageView.setImage(images[5]);
        imageView.setFitHeight(92);
        imageView.setFitWidth(92);
        imageView.setX(Dimension.width/2);
        imageView.setY(Dimension.height/2);
        Canvas canvas = new Canvas(Dimension.width, Dimension.height);
        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        Maze.initDraw(graphicsContext);
        imageView.setPreserveRatio(true);
        Group root = new Group(imageView);
        root.getChildren().addAll(canvas, Maze.getOverlay());
        Scene scene = new Scene(root, Dimension.width, Dimension.height);
        //
        //write your code here
        path = new Path();
        animation = new Animation(
                imageView,
                Duration.millis(113*Dimension.animationFrame),
                COUNT, COLUMNS,
                OFFSET_X, OFFSET_Y,
                WIDTH, HEIGHT,imagePath,0
        );
        pathTransition = new PathTransition();
        scene.setOnMouseClicked(this);
        //
        primaryStage.setTitle("Lab6");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handle(MouseEvent e) {
        if(pathTransition.getCycleCount() == 1) {
            imageView.setX(oldPathX);
            imageView.setY(oldPathY);
            path.getElements().clear();
            long distance = (long)Math.sqrt((e.getX() - oldPathX)*(e.getX() - oldPathX) + (e.getY()-oldPathY)*(e.getY()-oldPathY));
            path.getElements().add(new MoveTo(oldPathX, oldPathY));
            path.getElements().add(new LineTo((int) e.getX(), (int) e.getY()));
            oldPathX = (int) e.getX();
            oldPathY = (int) e.getY();
            pathTransition.setDuration(Duration.millis(distance*5));
            pathTransition.setPath(path);
            pathTransition.setNode(imageView);
//            pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition.setCycleCount(1);
            pathTransition.setAutoReverse(false);
            pathTransition.play();
            animation.setCycleCount((int) ((distance*5) / (113 * 6)));
            animation.play();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

}
