package ifpe.surpriseme.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import ifpe.surpriseme.R;

/**
 * Created by Rayana on 10/08/2016.
 */
public class CurrentBackgroundFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_current_background, container, false);

        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);

        String imgUrl = "http://s9.favim.com/orig/130723/beach-hot-ocean-paradise-photography-water-waves-Favim.com-796393.jpg";
        Picasso.with(getContext()).load(imgUrl).resize(950, 900).into(imageView); //largura x altura
        return rootView;
    }
}
