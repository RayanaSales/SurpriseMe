package ifpe.surpriseme.controllers;

import android.app.Application;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import ifpe.surpriseme.Model.ApplicationSingleton;
import ifpe.surpriseme.Model.Category;
import ifpe.surpriseme.R;
import ifpe.surpriseme.flickr.ManagerFlickr;
import ifpe.surpriseme.repositories.CategoryRepository;

public class BackgroundController extends Application {
    private ImageView imageView;
    private String photoUrl = "";

    public void manageSwapBackground(){
        if(verificaConexao()) {
            try {
                photoUrl = new ManagerFlickr().execute(sortTagFromRepository()).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            Picasso.with(ApplicationSingleton.getApplicationContext()).load(photoUrl).resize(950, 900).into(ApplicationSingleton.getCurrentImageSystemView()); //largura x altura
            ApplicationSingleton.setCurrentImageSystemView((ImageView) ApplicationSingleton.getRootView().findViewById(R.id.imageView)); //atualize a img atual

            //set system background
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            if (ApplicationSingleton.getCurrentImageSystemView() != null)
                                changeSystemBackground(ApplicationSingleton.getCurrentImageSystemView());
                        }
                    },
                    15000
            );
        }
        else
        {
            Toast toast = Toast.makeText(ApplicationSingleton.getCurrentBackgroundActivity(), "Sem internet! Conecte-se! ", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private String sortTagFromRepository(){
        ArrayList<Category> categories = CategoryRepository.getCategoryRepository().list(ApplicationSingleton.getCurrentBackgroundActivity(), true);

        if(categories.size() != 0){
            Random random = new Random();
            int position = random.nextInt(categories.size());

            System.out.println("Tag sorteada: " + categories.get(position).getName());
            return categories.get(position).getName();
        }
        return "love";
    }

    private void changeSystemBackground(ImageView imageSorted){
        Bitmap bitmap = ((BitmapDrawable)imageSorted.getDrawable()).getBitmap();
        DisplayMetrics metrics = new DisplayMetrics();
        ApplicationSingleton.getCurrentBackgroundActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        ApplicationSingleton.setScreenHeight(metrics.heightPixels);
        ApplicationSingleton.setScreenWidth(metrics.widthPixels);

        WallpaperManager wallpaperManager = WallpaperManager.getInstance(ApplicationSingleton.getApplicationContext());

        try {
            wallpaperManager.setBitmap(bitmap);
            wallpaperManager.suggestDesiredDimensions(ApplicationSingleton.getScreenWidth(), ApplicationSingleton.getScreenHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean verificaConexao() {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) ApplicationSingleton.getCurrentBackgroundActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }
}
