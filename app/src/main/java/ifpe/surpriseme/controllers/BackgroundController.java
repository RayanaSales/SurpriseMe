package ifpe.surpriseme.controllers;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import ifpe.surpriseme.Model.ApplicationSingleton;
import ifpe.surpriseme.Model.Category;
import ifpe.surpriseme.Model.PhoneSettings;
import ifpe.surpriseme.R;
import ifpe.surpriseme.activities.MainActivity;
import ifpe.surpriseme.flickr.ManagerFlickr;
import ifpe.surpriseme.repositories.CategoryRepository;
import ifpe.surpriseme.repositories.SettingsRepository;
import ifpe.surpriseme.services.ChangeBackgroundOffline;

public class BackgroundController extends Application {

    private String photoUrl = "";
    private String photoUrlAnterior = "";
    private Long timeToSwitchPic = 10L;

    private String timeFrequency = "";
    private String valueFrequency = "";

    public BackgroundController(String timeFrequency, String valueFrequency) {
        this.timeFrequency = timeFrequency;
        this.valueFrequency = valueFrequency;
    }

    public BackgroundController() {

    }

    public void manageSwapBackground() {
        if (verificaConexao()) {
            try {
                while (photoUrl.equals(photoUrlAnterior)) {
                    Thread.sleep(timeToSwitchPic * 1000);
                    photoUrl = new ManagerFlickr().execute(sortTagFromRepository()).get();
                }
                photoUrlAnterior = photoUrl;
                ApplicationSingleton.setCurrentUrlPic(photoUrl);

                //photoUrl = new ManagerFlickr().execute(sortTagFromRepository()).get();
                // Picasso.with(ApplicationSingleton.getApplicationContext()).load(photoUrl).resize(950, 900).into(ApplicationSingleton.getCurrentImageSystemView()); //largura x altura

                alterarPapelParede(photoUrl);

                Intent it = new Intent(ApplicationSingleton.getCurrentActivity(), ChangeBackgroundOffline.class);
                PendingIntent p = PendingIntent.getBroadcast(ApplicationSingleton.getCurrentActivity(), 0, it, PendingIntent.FLAG_UPDATE_CURRENT);

                // agendar o alarme
                AlarmManager alarme = (AlarmManager) ApplicationSingleton.getApplicationContext().getSystemService(ALARM_SERVICE);
                System.out.print("horarios - timeFrequency: " + timeFrequency + " valueFrequency: " + valueFrequency);
                long time = convertTimeToSeconds();
                alarme.set(AlarmManager.RTC_WAKEUP, time, p);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast toast = Toast.makeText(ApplicationSingleton.getCurrentActivity(), "Sem internet! conecte-se! ", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void alterarPapelParede(final String photoUrl) {
        new AsyncTask<Object, Object, Object>() {

            @Override
            protected Object doInBackground(Object[] params) {

                try {
                    URL url = new URL(photoUrl);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.connect();
                    InputStream inputStream = urlConnection.getInputStream();

                    WallpaperManager wallpaperManager = WallpaperManager.getInstance(ApplicationSingleton.getApplicationContext());

                    wallpaperManager.setStream(inputStream);
                    inputStream.close();
                } catch (Exception e) {
                    Log.i(getClass().getSimpleName(), e.getMessage(), e);
                }
                return null;
            }
        }.execute();
    }

    private String sortTagFromRepository() {
        ArrayList<Category> categories = CategoryRepository.getCategoryRepository().list(ApplicationSingleton.getCurrentActivity(), true);

        if (categories.size() != 0) {
            Random random = new Random();
            int position = random.nextInt(categories.size());

            System.out.println("Tag sorteada: " + categories.get(position).getName());
            return categories.get(position).getName();
        }

        return "love";
    }

    private void changeSystemBackground(ImageView imageSorted) {
        Bitmap bitmap = ((BitmapDrawable) imageSorted.getDrawable()).getBitmap();
        DisplayMetrics metrics = new DisplayMetrics();
        ApplicationSingleton.getCurrentActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
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
        boolean conectado = false;
        try {
            ConnectivityManager conectivtyManager = (ConnectivityManager) ApplicationSingleton.getCurrentActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (conectivtyManager.getActiveNetworkInfo() != null
                    && conectivtyManager.getActiveNetworkInfo().isAvailable()
                    && conectivtyManager.getActiveNetworkInfo().isConnected()) {
                conectado = true;
            } else {
                conectado = false;
            }
        } catch (Exception e) {
            System.out.print("Deu merda");
        }
        return conectado;
    }

    private Long convertTimeToSeconds() {
        Long time = 0L;

        if (timeFrequency.equals("hour"))
            time = Long.parseLong(valueFrequency, 10) * 3600;
        else if (timeFrequency.equals("minute"))
            time = Long.parseLong(valueFrequency, 10) * 60;
        else if (timeFrequency.equals("second"))
            time = Long.parseLong(valueFrequency, 10);

        return time;
    }
}
