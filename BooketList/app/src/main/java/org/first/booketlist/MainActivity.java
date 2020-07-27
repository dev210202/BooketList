package org.first.booketlist;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.first.booketlist.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private HomeFragment homeFragment = new HomeFragment();
    private SearchFragment searchFragment = new SearchFragment();
    private AddFragment addFragment = new AddFragment();
    private UserFragment userFragment = new UserFragment();
    private ReportFragment reportFragment = new ReportFragment();
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
                    case R.id.search_navigation: {
                        transaction.replace(R.id.framelayout, searchFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.add_navigation: {
                        transaction.replace(R.id.framelayout, addFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.user_navigation: {
                        transaction.replace(R.id.framelayout, userFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.report_navigation: {
                        transaction.replace(R.id.framelayout, reportFragment).commitAllowingStateLoss();
                        break;
                    }
                }

                return true;
            }
        });

    }
}