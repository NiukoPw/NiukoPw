package com.michelezulian.example.niuko;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // creo bottom navigation bar
        BottomNavigationView vNavigation =  findViewById(R.id.mainBottomNavigation);
        vNavigation.setOnNavigationItemSelectedListener(this);
        vNavigation.setSelectedItemId(R.id.navigation_explore);


    }


    /**
     * Inserisce il menu all'interno della navbar
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.directory, menu);
        return true;
    }


    /**
     * Sostituisce il fragment attualmente presente nel container con quello passato
     *
     * @param aFragment
     * @return
     */
    public boolean loadFragment (Fragment aFragment) {
        if(aFragment != null) {
            FragmentManager vManager = getFragmentManager();
            FragmentTransaction vTransaction = vManager.beginTransaction();
            vTransaction.replace(R.id.fragment_container, aFragment);
            vTransaction.commit();

            return true;
        }
        return false;
    }


    /**
     * Chiamato quando viene premuto un elemento della navbar
     * Prende l'id dell'elemento selezionato
     * In base all'id attiva il fragment corrispondente
     *
     * @param menuItem
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment vFragment = null;
        switch (menuItem.getItemId()) {
            case R.id.navigation_calendar: {
                vFragment = new CalendarFragment();
            } break;

            case R.id.navigation_explore: {
                vFragment = new ExploreFragment();
            } break;

            case R.id.navigation_user: {
                vFragment = new UserFragment();
            } break;
        }

        return loadFragment(vFragment);
    }
}
