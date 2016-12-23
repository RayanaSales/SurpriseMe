package ifpe.surpriseme.fragments;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import ifpe.surpriseme.controllers.BackgroundController;
import ifpe.surpriseme.flickr.ManagerFlickr;
import ifpe.surpriseme.repositories.CategoryRepository;

public class CurrentBackgroundFragment extends Fragment {

    String photoUrl = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //initialize
        ApplicationSingleton.setCurrentBackgroundActivity(getActivity());
        ApplicationSingleton.setCurrentActivity(getActivity());
        ApplicationSingleton.setRootView(inflater.inflate(R.layout.fragment_current_background, container, false));
        ApplicationSingleton.setCurrentImageSystemView((ImageView) ApplicationSingleton.getRootView().findViewById(R.id.imageView));

//        if(ApplicationSingleton.getCurrentImageSystemView() != null){
//            Picasso.with(ApplicationSingleton.getApplicationContext()).load(photoUrl).resize(950, 900).into(ApplicationSingleton.getCurrentImageSystemView());
//        }

        BackgroundController bg = new BackgroundController();
        bg.manageSwapBackground();

        return ApplicationSingleton.getRootView();
    }


}
