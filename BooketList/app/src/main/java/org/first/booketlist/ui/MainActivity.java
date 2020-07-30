package org.first.booketlist.ui;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.first.booketlist.R;
import org.first.booketlist.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private HomeFragment homeFragment = new HomeFragment();
    private SearchFragment searchFragment = new SearchFragment();
    private UserFragment userFragment = new UserFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout, homeFragment).commitAllowingStateLoss();

        binding.bottomnavtigationview.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (menuItem.getItemId()) {
                    case R.id.home_navigation: {
                        transaction.replace(R.id.framelayout, homeFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.add_search_navigation: {
                        transaction.replace(R.id.framelayout, searchFragment).commitAllowingStateLoss();
                        break;
                    }

                    case R.id.user_navigation: {
                        transaction.replace(R.id.framelayout, userFragment).commitAllowingStateLoss();
                        break;
                    }

                }

                return true;
            }
        });

    }


}