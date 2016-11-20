package ifpe.surpriseme.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ifpe.surpriseme.fragments.CategoriesFragment;
import ifpe.surpriseme.fragments.CurrentBackgroundFragment;
import ifpe.surpriseme.fragments.SettingsFragment;

/**
 * Created by Rayana on 11/18/2016.
 */

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
        // get item count - equal to number of tabs
        return 3;
    }

    @Override
    public int getItemPosition(Object object) {
        if (object instanceof CategoriesFragment ) {
            ((CategoriesFragment )object).listAllCategories();
        }
        return super.getItemPosition(object);
    }

}