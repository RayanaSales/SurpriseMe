package ifpe.surpriseme.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ifpe.surpriseme.R;

/**
 * Created by Rayana on 10/08/2016.
 */
public class CategoriesFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);

        return rootView;
    }
}
