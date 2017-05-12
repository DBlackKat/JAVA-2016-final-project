package Animation.Wrapper;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Created by theblackcat on 11/5/17.
 */
public class Animation extends Transition {
    private final ImageView imageView;
    private final int count;
    private final int columns;
    private final int offsetX;
    private final int offsetY;
    private final int width;
    private final int height;
    private final String path;
    private final int transparency;
    public Animation(
            ImageView imageView,
            Duration duration,
            int count,   int columns,
            int offsetX, int offsetY,
            int width,   int height,String path,int cycleCount) {
        this.imageView = imageView;
        this.count     = count;
        this.columns   = columns;
        this.offsetX   = offsetX;
        this.offsetY   = offsetY;
        this.width     = width;
        this.height    = height;
        this.transparency = 0;
        this.path = path;
        setCycleDuration(duration);
        setCycleCount(cycleCount);
        setInterpolator(Interpolator.LINEAR);
    }

    protected void interpolate(double k) {
        final int n = Math.round((count-1) * (float) k);
        Reflection reflection = new Reflection();
        System.out.println("Value:"+transparency);
        reflection.setFraction(transparency);
        Image images[] = new Image[6];
        for (int i = 0;i < images.length;i++)
            images[i] = new Image(path+i+".png");
        imageView.setImage(images[n]);
        imageView.setEffect(reflection);
        imageView.setViewport(new Rectangle2D(0, 0, width, height));
    }
}
