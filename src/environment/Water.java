package environment;

/**
 * Created by user01 on 07/05/2017.
 */
public class Water extends Fluid{
    private boolean isPoison;
    public void setState(boolean isPoison){this.isPoison=isPoison;}
    public boolean getState(){return isPoison;}
}
