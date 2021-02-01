package com.example.komusic.Navigation;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.komusic.CollectionActivity;
import com.example.komusic.MainActivity;
import com.example.komusic.Profile;
import com.example.komusic.Search;

public class ViewPageAdapter extends FragmentStatePagerAdapter {
    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MainActivity();
            case 1:
                return new Search();
            case 2:
                return new CollectionActivity();
            case 3:
                return new Profile();
            default:
                return new MainActivity();

        }

    }

    @Override
// get ra số lượng item
    public int getCount() {
        return 4;
    }
}