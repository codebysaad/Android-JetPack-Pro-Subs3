package xyz.webflutter.myandroidjetpackpro.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import xyz.webflutter.myandroidjetpackpro.ui.movie.favorite.MovieFavFragment;
import xyz.webflutter.myandroidjetpackpro.ui.tvshow.favorite.TvShowFavFragment;

public class FragmentPageAdapter extends FragmentStatePagerAdapter {
    private final int count;

    public FragmentPageAdapter(@NonNull FragmentManager fm, int count) {
        super(fm);
        this.count = count;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MovieFavFragment();
            case 1:
                return new TvShowFavFragment();
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return count;
    }
}
