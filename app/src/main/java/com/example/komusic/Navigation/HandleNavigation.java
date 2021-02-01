package com.example.komusic.Navigation;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.komusic.DB;
import com.example.komusic.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HandleNavigation extends AppCompatActivity {
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    public static DB sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
        setContentView(R.layout.bottm_nav);

        //ánh xạ view vào
        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.btnavi);

//        khai báo viewadapter vừa mới viết
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.nav_search:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.nav_explore:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.nav_collection:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        }
        );
        sql=new DB(HandleNavigation.this);
    }


}
