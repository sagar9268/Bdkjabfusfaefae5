package co.bingleapp.bingle;

import android.content.Intent;
import android.drm.DrmStore;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    Fragment fragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_settings:
                    mTextMessage.setText(R.string.title_fragment_settings);
                    fragment =new Settings();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_find_date:
                    mTextMessage.setText(R.string.title_fragment_find_date);
                    fragment = new FindDate();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_fragment_notifications);
                    fragment = new Notifications();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_fixed_date:
                    mTextMessage.setText(R.string.title_fragment_fixed_date);
                    fragment = new FixedDate();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //load main activity fragment by default
        loadFragment(new Notifications());

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    private void loadFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
