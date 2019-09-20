package com.Sunflower.myapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import com.Sunflower.myapplication.Fragment.FragmentMovie;
import com.Sunflower.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    Bundle bundle = new Bundle();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;

            switch (item.getItemId()) {
                case R.id.navigation_home:

                    fragment = new FragmentMovie();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    bundle = new Bundle();
                    bundle.putString("type", "1");
                    fragment.setArguments(bundle);
                    return true;
                case R.id.navigation_dashboard:

                    fragment = new FragmentMovie();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    bundle = new Bundle();
                    bundle.putString("type", "2");
                    fragment.setArguments(bundle);

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            navigation.setSelectedItemId(R.id.navigation_home);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}




