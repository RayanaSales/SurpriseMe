package ifpe.surpriseme;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ifpe.surpriseme.fragments.CategoriesFragment;
import ifpe.surpriseme.fragments.CurrentBackgroundFragment;
import ifpe.surpriseme.fragments.SettingsFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0:
                return new CurrentBackgroundFragment();
            case 1:
                return new CategoriesFragment();
            case 2:
                return new SettingsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

}