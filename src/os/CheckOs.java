package os;

/**
 * Created by theblackcat on 27/4/17.
 */
public class CheckOs {
    private static String OS = System.getProperty("os.name").toLowerCase();


    public static boolean isMac(){
        return (OS.indexOf("mac") >= 0);
    }
    public static boolean isWindow(){
        return (OS.indexOf("win") >= 0);
    }
    public static String getMusicPath(){
        if(isMac()){
            return "/Users/theblackcat/Documents/University/JAVA-2016/FinalProject/src/resource/audio/";
        }
        else if(isWindow()){
            return "C:\\Users\\user01\\Desktop\\JAVA\\FinalProject\\src\\resource\\audio\\";
        }
        return "";
    }
    public static String getImagePath(){
        if(isMac()){
            return "/Users/theblackcat/Documents/University/JAVA-2016/FinalProject/src/resource/images/";
        }
        else if(isWindow()){
            return "C:\\Users\\user01\\Desktop\\JAVA\\FinalProject\\src\\resource\\images\\";
        }
        return "";
    }
    public static String getAnimationResourcePath(String ... directory){
        String result;
        result = getImagePath();
        result = "file:"+result;
        for(String i : directory){
            if(isMac()){
                result += i+'/';
            }
            else{
                result += i+'\\';
            }
        }
        return result;
    }

}
