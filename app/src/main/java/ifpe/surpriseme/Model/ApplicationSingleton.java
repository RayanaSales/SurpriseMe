package ifpe.surpriseme.Model;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

import ifpe.surpriseme.controllers.BackgroundController;

public class ApplicationSingleton {

    private static Context applicationContext;
    private static FragmentActivity currentActivity;
    private static FragmentActivity currentBackgroundActivity;
    private static View rootView = null;
    private static ImageView currentImageSystemView;
    private static int screenHeight;
    private static int screenWidth;
    private static String currentUrlPic;
    private static BackgroundController backgroundController;








    public static Context getApplicationContext(){
        return  applicationContext;
    }

    public static void setApplicationContext(Context ctx){
        applicationContext = ctx;
    }

    public static void setCurrentActivity(FragmentActivity currentActivity) {
        ApplicationSingleton.currentActivity = currentActivity;
    }

    public static FragmentActivity getCurrentActivity() {
        return currentActivity;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }

    public static void setScreenHeight(int screenHeight) {
        ApplicationSingleton.screenHeight = screenHeight;
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static void setScreenWidth(int screenWidth) {
        ApplicationSingleton.screenWidth = screenWidth;
    }

    public static ImageView getCurrentImageSystemView() {
        return currentImageSystemView;
    }

    public static void setCurrentImageSystemView(ImageView currentImageSystemView) {
        ApplicationSingleton.currentImageSystemView = currentImageSystemView;
    }

    public static View getRootView() {
        return rootView;
    }

    public static void setRootView(View rootView) {
        ApplicationSingleton.rootView = rootView;
    }

    public static FragmentActivity getCurrentBackgroundActivity() {
        return currentBackgroundActivity;
    }

    public static void setCurrentBackgroundActivity(FragmentActivity currentBackgroundActivity) {
        ApplicationSingleton.currentBackgroundActivity = currentBackgroundActivity;
    }

    public static String getCurrentUrlPic() {
        return currentUrlPic;
    }

    public static void setCurrentUrlPic(String currentUrlPic) {
        ApplicationSingleton.currentUrlPic = currentUrlPic;
    }

    public static BackgroundController getBackgroundController() {
        return backgroundController;
    }

    public static void setBackgroundController(BackgroundController backgroundController) {
        ApplicationSingleton.backgroundController = backgroundController;
    }
}
