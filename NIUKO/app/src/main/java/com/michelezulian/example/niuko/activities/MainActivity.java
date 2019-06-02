package com.michelezulian.example.niuko.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.michelezulian.example.niuko.R;
import com.michelezulian.example.niuko.data.Corso;
import com.michelezulian.example.niuko.data.Utente;
import com.michelezulian.example.niuko.fragments.CalendarFragment;
import com.michelezulian.example.niuko.fragments.CourseDetailFragment;
import com.michelezulian.example.niuko.fragments.ExploreFragment;
import com.michelezulian.example.niuko.fragments.LessonCourseFragment;
import com.michelezulian.example.niuko.fragments.NewsDetailFragment;
import com.michelezulian.example.niuko.fragments.NewsFragment;
import com.michelezulian.example.niuko.fragments.UserCourseDetailFragment;
import com.michelezulian.example.niuko.fragments.UserCoursesFragment;
import com.michelezulian.example.niuko.fragments.UserFragment;
import com.michelezulian.example.niuko.misc.ConnectionSingleton;
import com.michelezulian.example.niuko.misc.FragmentListener;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.michelezulian.example.niuko.misc.StaticValues.ID_KEY;
import static com.michelezulian.example.niuko.misc.StaticValues.IMG_URL;
import static com.michelezulian.example.niuko.misc.StaticValues.PROPIC_URL;
import static com.michelezulian.example.niuko.misc.StaticValues.URL_SIGNUP;
import static com.michelezulian.example.niuko.misc.StaticValues.URL_SINGLE_USER;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, FragmentListener {
    ImageView mIconautente;
    BottomNavigationView mNavigation;
    TextView mNomeUtente;
    Utente mUtente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        SharedPreferences vSharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        int vId = vSharedPref.getInt(ID_KEY, -1);
        if (vId >= 0) {
            JSONObject vParameters = new JSONObject();
            try {
                vParameters.put("id", vId);
                JsonObjectRequest vRequest = new JsonObjectRequest(
                        Request.Method.POST, URL_SINGLE_USER, vParameters,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("risposta", "onResponse Main R76: " + response.toString());
                                try {
                                    mUtente = new Utente(
                                            response.getString("nome"),
                                            response.getString("cognome"),
                                            IMG_URL,
                                            response.getString("nomeUtente"),
                                            response.getInt("amministratore"),
                                            response.getInt("id")
                                    );

                                    // metto nome utente
                                    mNomeUtente = findViewById(R.id.mainUserNameTextView);
                                    mNomeUtente.setText(mUtente.getmNomeUtente());
                                } catch (Exception e) {
                                    Log.d("risposta", "Errore MainActivity R97: " + e.toString());
                                    Toast.makeText(MainActivity.this, "Oops! C'è stato un errore", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("risposta", "Errore MainActivity R106: " + error.toString());
                                Toast.makeText(MainActivity.this, "Oops! C'è stato un errore", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                );
                ConnectionSingleton.getInstance(MainActivity.this).addToRequestQueue(vRequest);
            } catch (Exception e) {
                Log.d("risposta", "Errore MainActivity R114: " + e.toString());
                Toast.makeText(MainActivity.this, "Oops! C'è stato un errore", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            finish();
        }

        // metto immagine profilo
        mIconautente = findViewById(R.id.mainUserImage);
        Glide.with(this)
                .load(PROPIC_URL)
                .into(mIconautente);

        // creo bottom navigation bar
        mNavigation = findViewById(R.id.mainBottomNavigation);
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
    public boolean loadFragment(Fragment aFragment) {
        if (aFragment != null) {
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
            }
            break;

            case R.id.navigation_explore: {
                vFragment = new ExploreFragment();
            }
            break;

            case R.id.navigation_news: {
                vFragment = new NewsFragment();
            }
            break;
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
                        } else

                            // corso entrando dalla lezione -> calendario
                            if(vFragment instanceof LessonCourseFragment) {
                                loadFragment(new CalendarFragment());
                            }
    }

    @Override
    public Utente getUtente() {
        return mUtente;
    }
}
