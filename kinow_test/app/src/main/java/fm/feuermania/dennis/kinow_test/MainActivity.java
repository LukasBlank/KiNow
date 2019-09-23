package fm.feuermania.dennis.kinow_test;

import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

//import com.roughike.bottombar.BottomBar;
//import com.roughike.bottombar.OnMenuTabClickListener;

public class MainActivity extends AppCompatActivity implements MoviesFragment.OnFragmentInteractionListener, ShoppingCartFragment.OnFragmentInteractionListener, SearchFragment.OnFragmentInteractionListener, AccountFragment.OnFragmentInteractionListener{

    //private BottomBar BottomBar;
    private ActionBar kinowToolbar;

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

        /*
        BottomBar = BottomBar.attach(findViewById(R.id.mainLayout), savedInstanceState);
        BottomBar.noTopOffset();
        BottomBar.setItems(R.menu.bot_nav_items);
        BottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                switch (menuItemId) {
                    case R.id.tab_search:
                        Toast.makeText(MainActivity.this, "Search", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tab_movies:
                        Toast.makeText(MainActivity.this, "Movies", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tab_cart:
                        Toast.makeText(MainActivity.this, "Purchases", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tab_account:
                        Toast.makeText(MainActivity.this, "Account", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.tab_search) {
                    // The user reselected item number one, scroll your content to top.
                }
            }
        });

        // Setting colors for different tabs when there's more than three of them.
        // You can set colors for tabs in three different ways as shown below.
        BottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.mint));
        BottomBar.mapColorForTab(1, ContextCompat.getColor(this, R.color.brown));
        BottomBar.mapColorForTab(2, ContextCompat.getColor(this, R.color.violet));
        BottomBar.mapColorForTab(3, ContextCompat.getColor(this, R.color.red));
         */

    }

    //Change Fragment depending on which Button is pressed
    private BottomNavigationView.OnNavigationItemSelectedListener botNavItemListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.tab_movies:
                    kinowToolbar.setTitle("Movies");
                    fragment = new MoviesFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.tab_search:
                    kinowToolbar.setTitle("Search");
                    fragment = new SearchFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.tab_cart:
                    kinowToolbar.setTitle("Purchases");
                    fragment = new ShoppingCartFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.tab_account:
                    kinowToolbar.setTitle("Account");
                    fragment = new AccountFragment();
                    loadFragment(fragment);
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
