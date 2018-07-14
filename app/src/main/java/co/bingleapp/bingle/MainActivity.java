package co.bingleapp.bingle;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener,
        ProfileSettings.OnFragmentInteractionListener, FindDate.OnFragmentInteractionListener,
        FixedDate.OnFragmentInteractionListener, Notifications.OnFragmentInteractionListener,
        Settings.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //load settings fragment by default
        loadFragment(new Settings());

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);


    }

    private boolean loadFragment(Fragment fragment) {
        if(fragment != null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_settings:
                fragment = new Settings();
                break;

            case R.id.navigation_find_date:
                fragment = new FindDate();
                break;

            case R.id.navigation_fixed_date:
                fragment = new FixedDate();
                break;

            case R.id.navigation_notifications:
                fragment = new Notifications();
                break;

            case R.id.navigation_bio_preference:
                fragment = new ProfileSettings();
                break;
        }
        return loadFragment(fragment);
    }

    @Override
    public void onFindDateFragmentInteraction() {
        Toast.makeText(MainActivity.this, "We will notify you when your date is available!", Toast.LENGTH_LONG).show();
        //rest of the code here for find date fragment
    }

    @Override
    public void onSettingsFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFixedDateFragmentInteraction(Uri uri){

    }

    @Override
    public void onProfileSettingsFragmentInteraction() {
        Toast.makeText(MainActivity.this, "Bio and Preference Updated Successfully!", Toast.LENGTH_LONG).show();
        //rest of the code for bio preference fragment
    }

    @Override
    public void onNotificationsFragmentInteraction(Uri uri) {

    }


}
