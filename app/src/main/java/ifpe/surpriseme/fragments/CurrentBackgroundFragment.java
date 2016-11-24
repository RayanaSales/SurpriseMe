package ifpe.surpriseme.fragments;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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

import ifpe.surpriseme.Model.Category;
import ifpe.surpriseme.R;
import ifpe.surpriseme.flickr.ManagerFlickr;
import ifpe.surpriseme.repositories.CategoryRepository;

/**
 * Created by Rayana on 10/08/2016.
 */
public class CurrentBackgroundFragment extends Fragment {

    String photoUrl = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_current_background, container, false);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);

        try {
            photoUrl = new ManagerFlickr().execute(sortTagFromRepository()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Picasso.with(getContext()).load(photoUrl).resize(950, 900).into(imageView); //largura x altura

        //set system background
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);

                        if(imageView != null)
                            changeSystemBackground(imageView);
                    }
                },
                15000
        );

        Toast.makeText(getActivity(), "Imagem definida como background", Toast.LENGTH_LONG).show();

        return rootView;
    }

    public String sortTagFromRepository(){
        ArrayList<Category> categories = CategoryRepository.getCategoryRepository().list(getActivity());

        Random random = new Random();
        int position = random.nextInt(categories.size());

        System.out.println("Tag sorteada: " + categories.get(position).getName());

        return categories.get(position).getName();
    }

    public void changeSystemBackground(ImageView imageSorted){
        Bitmap bitmap = ((BitmapDrawable)imageSorted.getDrawable()).getBitmap();
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getContext());

        try {
            wallpaperManager.setBitmap(bitmap);
            wallpaperManager.suggestDesiredDimensions(width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
