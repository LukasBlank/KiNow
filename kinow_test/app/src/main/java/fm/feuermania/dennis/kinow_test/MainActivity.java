package fm.feuermania.dennis.kinow_test;

import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

public class MainActivity extends AppCompatActivity {

    private BottomBar BottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    }
}
