package com.michelezulian.example.niuko.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.michelezulian.example.niuko.R;
import com.michelezulian.example.niuko.fragments.CalendarFragment;
import com.michelezulian.example.niuko.fragments.ExploreFragment;
import com.michelezulian.example.niuko.fragments.NewsFragment;
import com.michelezulian.example.niuko.fragments.UserFragment;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    ImageView mIconautente;
    BottomNavigationView mNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // creo bottom navigation bar
        mNavigation =  findViewById(R.id.mainBottomNavigation);
        mNavigation.setOnNavigationItemSelectedListener(this);
        mNavigation.setSelectedItemId(R.id.navigation_news);

        // setto icona utente cliccabile
        mIconautente = findViewById(R.id.mainUserImage);
        mIconautente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new UserFragment());
            }
        });
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

            case R.id.navigation_news: {
                vFragment = new NewsFragment();
            } break;
        }

        return loadFragment(vFragment);
    }
}
