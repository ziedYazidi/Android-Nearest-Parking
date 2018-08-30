package com.example.zied.testgooglemaps;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarBadge;
import com.roughike.bottombar.OnMenuTabClickListener;
import android.view.Menu;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    BottomBar bottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomBar=BottomBar.attach(this,savedInstanceState);
        bottomBar.setItemsFromMenu(R.menu.main_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId)
            {
                if(menuItemId == R.id.profileId)
                {
                    ProfileFragment f = new ProfileFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame,f).commit();
                }
                if(menuItemId == R.id.parkingId)
                {
                    ParkingFragment f = new ParkingFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame,f).commit();
                }
                if(menuItemId == R.id.rechercheId)
                {
                    SearchFragment f = new SearchFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame,f).commit();
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });
        bottomBar.mapColorForTab(0,"#3F51B5");
        bottomBar.mapColorForTab(1,"#795548");
        bottomBar.mapColorForTab(2,"#607D8B");
        BottomBarBadge unread;
        unread = bottomBar.makeBadgeForTabAt(1,"#DD2C00",13);
        unread.show();
    }
}
