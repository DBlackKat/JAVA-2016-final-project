package environment;

/**
 * Created by user01 on 07/05/2017.
 */
public class Minions extends EnvironmentElement {
    private int health;
    private int oxygen;
    private int strength;
    private int status;
    //queue task;
    public void setStatus(int status){this.status=status;}
    public void breath(){}
    public void eat(Device device){}
    public int getHealth(){return health;}
    public int getOxygen(){return oxygen;}
    public void addTask(){}
    public void removeTask(){}
}
