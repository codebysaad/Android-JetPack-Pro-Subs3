package xyz.webflutter.myandroidjetpackpro.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import xyz.webflutter.myandroidjetpackpro.R;
import xyz.webflutter.myandroidjetpackpro.adapter.FragmentPageAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteHomeFragment extends Fragment implements TabLayout.OnTabSelectedListener{

    private ViewPager viewPager;
    public FavoriteHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_home, container, false);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);

        assert getFragmentManager() != null;
        FragmentPageAdapter fragmentPageAdapter = new FragmentPageAdapter(getFragmentManager(), tabLayout.getTabCount());
        tabLayout.setOnTabSelectedListener(this);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setAdapter(fragmentPageAdapter);
        return view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
