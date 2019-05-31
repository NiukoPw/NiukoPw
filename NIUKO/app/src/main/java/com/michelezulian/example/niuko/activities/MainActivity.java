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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.michelezulian.example.niuko.R;
import com.michelezulian.example.niuko.data.Utente;
import com.michelezulian.example.niuko.fragments.CalendarFragment;
import com.michelezulian.example.niuko.fragments.CourseDetailFragment;
import com.michelezulian.example.niuko.fragments.ExploreFragment;
import com.michelezulian.example.niuko.fragments.NewsDetailFragment;
import com.michelezulian.example.niuko.fragments.NewsFragment;
import com.michelezulian.example.niuko.fragments.UserCourseDetailFragment;
import com.michelezulian.example.niuko.fragments.UserCoursesFragment;
import com.michelezulian.example.niuko.fragments.UserFragment;
import com.michelezulian.example.niuko.misc.FragmentListener;

import static com.michelezulian.example.niuko.misc.StaticValues.IMG_URL;
import static com.michelezulian.example.niuko.misc.StaticValues.PROPIC_URL;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, FragmentListener {
    ImageView mIconautente;
    BottomNavigationView mNavigation;
    TextView mNomeUtente;
    Utente mUtente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // creo utente di prova
        mUtente = new Utente(
                "Zayed",
                "Ahmed",
                IMG_URL,
                "aaa",
                false,
                1
        );

        // metto immagine profilo
        mIconautente = findViewById(R.id.mainUserImage);
        Glide.with(this)
                .load(PROPIC_URL)
                .centerInside()
                .into(mIconautente);

        // metto nome utente
        mNomeUtente = findViewById(R.id.mainUserNameTextView);
        mNomeUtente.setText(mUtente.getmNomeUtente());

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

    @Override
    public void onBackPressed() {
        Object vFragment = getFragmentManager().findFragmentById(R.id.fragment_container);
        // in base al fragment in cui ci si trova, si torna ad un dato fragment

        // visualizzazione notizie -> esci dall'app
        if (vFragment instanceof NewsFragment) {
            finish();
        } else

            // dettagli notizia, visualizzazione calendario, tutti i corsi, utente -> notizie
            if (vFragment instanceof CalendarFragment ||
                    vFragment instanceof ExploreFragment ||
                    vFragment instanceof NewsDetailFragment ||
                    vFragment instanceof UserFragment) {
                mNavigation.setSelectedItemId(R.id.navigation_news);
                loadFragment(new NewsFragment());
            } else

                // dettagli corso -> lista corsi principale
                if (vFragment instanceof CourseDetailFragment) {
                    mNavigation.setSelectedItemId(R.id.navigation_explore);
                    loadFragment(new ExploreFragment());
                } else

                    // dettagli corso (da corsi dell'utente) -> corsi dell'utente
                    if (vFragment instanceof UserCourseDetailFragment) {
                        loadFragment(new UserCoursesFragment());
                    } else

                        // corsi dell'utente -> utente
                        if (vFragment instanceof UserCoursesFragment) {
                            loadFragment(new UserFragment());
                        }
    }

    @Override
    public Utente getUtente() {
        return mUtente;
    }
}
