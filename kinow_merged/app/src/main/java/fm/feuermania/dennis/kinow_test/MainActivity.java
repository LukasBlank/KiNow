package fm.feuermania.dennis.kinow_test;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements MoviesFragment.OnFragmentInteractionListener, ShoppingCartFragment.OnFragmentInteractionListener, LocationFragment.OnFragmentInteractionListener, AccountFragment.OnFragmentInteractionListener{

    private ActionBar kinowToolbar;

    //save the opened fragments so that you do not have to open a new one every time
    Fragment movieFragment = null;
    Fragment locationFragment = null;
    Fragment shoppingCartFragment = null;
    Fragment accountFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set default ActionBar Title to "kiNOW"
        kinowToolbar = getSupportActionBar();
        kinowToolbar.setTitle("Movies");

        //Set default Fragment
        loadFragment(new MoviesFragment());

        //Bottom Navigation
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(botNavItemListener);
        navigation.setSelectedItemId(R.id.tab_movies);

    }

    //Change Fragment depending on which Button is pressed
    private BottomNavigationView.OnNavigationItemSelectedListener botNavItemListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.tab_movies:
                    kinowToolbar.setTitle("Movies");
                    if (movieFragment==null) movieFragment = new MoviesFragment();
                    loadFragment(movieFragment);
                    return true;
                case R.id.tab_location:
                    kinowToolbar.setTitle("Location");
                    if (locationFragment==null)locationFragment = new LocationFragment();
                    loadFragment(locationFragment);
                    return true;
                case R.id.tab_cart:
                    kinowToolbar.setTitle("ShoppingCart");
                    if(shoppingCartFragment==null)shoppingCartFragment = new ShoppingCartFragment();
                    loadFragment(shoppingCartFragment);
                    return true;
                case R.id.tab_account:
                    kinowToolbar.setTitle("Account");
                    if(accountFragment==null)accountFragment = new AccountFragment();
                    loadFragment(accountFragment);
                    return true;
            }
            return false;
        }
    };

    //Do NOT DELETE this method, it is necessary for the Fragments to work even if it is empty
    public void onFragmentInteraction(Uri uri) {
        // The user selected an fragment
        // Do something here to display that fragment
    }

    // Load the Fragment
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
