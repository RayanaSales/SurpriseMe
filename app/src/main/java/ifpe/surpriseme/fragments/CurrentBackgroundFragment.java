package ifpe.surpriseme.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import ifpe.surpriseme.Model.ApplicationSingleton;
import ifpe.surpriseme.R;

public class CurrentBackgroundFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //initialize
        ApplicationSingleton.setCurrentBackgroundActivity(getActivity());
        ApplicationSingleton.setCurrentActivity(getActivity());
        ApplicationSingleton.setRootView(inflater.inflate(R.layout.fragment_current_background, container, false));
        ApplicationSingleton.setCurrentImageSystemView((ImageView) ApplicationSingleton.getRootView().findViewById(R.id.imageView));

        return ApplicationSingleton.getRootView();
    }


}
