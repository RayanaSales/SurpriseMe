package ifpe.surpriseme.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;

import ifpe.surpriseme.R;
import ifpe.surpriseme.flickr.ManagerFlickr;

/**
 * Created by Rayana on 10/08/2016.
 */
public class CurrentBackgroundFragment extends Fragment {

    String photoUrl = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_current_background, container, false);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);

        try {
            photoUrl = new ManagerFlickr().execute("love").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Picasso.with(getContext()).load(photoUrl).resize(950, 900).into(imageView); //largura x altura
        return rootView;
    }
}
