package com.example.libreria;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity { //MOSTRA TUTTI I LIBRI IN UNA LISTVIEW

    final LibraryDB db = new LibraryDB(this);
    ListView mListView;
    Button mButton;
    Button mButton2;
    ImageView mImageView;
    Cursor vCursor;
    ArrayList<Post> vPosts = new ArrayList<>();//Creo un ArrayList
    PostAdapter vPostAdapter = new PostAdapter(vPosts, MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.list);
        mButton = findViewById(R.id.button);
        mButton2 = findViewById(R.id.button2);
        mImageView = findViewById(R.id.imageView);


        db.open();
        vCursor = db.ottieniTuttiLibri();
        while (vCursor.moveToNext()) { //Prendo dalla tabella i campi che mi interessano e li aggiungo all'Arraylist
            int vTitoloColumIndex = vCursor.getColumnIndex((LibraryDB.TITOLO));
            int vDescrizioneColumIndex = vCursor.getColumnIndex((LibraryDB.DESCRIZIONE));
            int AutoreColumIndex = vCursor.getColumnIndex((LibraryDB.AUTORE));
            int vIdColumIndex = vCursor.getColumnIndex((LibraryDB._ID));
            int vUrlColumIndex = vCursor.getColumnIndex((LibraryDB.URL));

            String vTitolo = vCursor.getString(vTitoloColumIndex);
            String vDescrizione = vCursor.getString(vDescrizioneColumIndex);
            String vAutore = vCursor.getString(AutoreColumIndex);
            String vUrl = vCursor.getString(vUrlColumIndex);
            int vId = vCursor.getInt(vIdColumIndex);
            vPosts.add(new Post(vId, vAutore, vTitolo, vDescrizione, vUrl, Calendar.getInstance()));
            mListView.setAdapter(vPostAdapter);
            vPostAdapter.notifyDataSetChanged();
        }

        mButton2.setOnClickListener(new View.OnClickListener() { //svuoto i record della tabella e aggiorno la ListView
            @Override
            public void onClick(View v) {
                db.svuotaDatabase();
                mListView.setAdapter(vPostAdapter);
                vPostAdapter.notifyDataSetChanged();
                vPosts.clear();
                mostraLista();
            }
        });


        mButton.setOnClickListener(new View.OnClickListener() { //Quando clicco passo all'activity che mi permette di aggiungere un libro
            @Override
            public void onClick(View v) {//Quando clicco passo all'activity che mi permette di aggiungere un libro
                Intent i = new Intent(MainActivity.this, InsertNewBooks.class);
                startActivityForResult(i, 1000);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, ListViewModify.class);
                startActivityForResult(i, 7000);

            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent databack) { //Controllo di essere arrivato dall'activity giusta e aggiorno la ListView
        //devo aggiornare l'activity
        if (requestCode == 1000 && resultCode == 2000) {
            mListView.setAdapter(vPostAdapter);
            vPostAdapter.notifyDataSetChanged();
            Toast.makeText(this, "LIBRO AGGIUNTO!", Toast.LENGTH_SHORT).show();
            vPosts.clear();
            this.mostraLista();
        } else {
        }
    }

    public void mostraLista() {
        vCursor = db.ottieniTuttiLibri();
        while (vCursor.moveToNext()) { //Prendo dalla tabella i campi che mi interessano e li mostro sulla listview
            int vTitoloColumIndex = vCursor.getColumnIndex((LibraryDB.TITOLO));
            int vDescrizioneColumIndex = vCursor.getColumnIndex((LibraryDB.DESCRIZIONE));
            int AutoreColumIndex = vCursor.getColumnIndex((LibraryDB.AUTORE));
            int vIdColumIndex = vCursor.getColumnIndex((LibraryDB._ID));
            int vUrlColumIndex = vCursor.getColumnIndex((LibraryDB.URL));

            String vTitolo = vCursor.getString(vTitoloColumIndex);
            String vDescrizione = vCursor.getString(vDescrizioneColumIndex);
            String vAutore = vCursor.getString(AutoreColumIndex);
            String vUrl = vCursor.getString(vUrlColumIndex);
            int vId = vCursor.getInt(vIdColumIndex);
            vPosts.add(new Post(vId, vAutore, vTitolo, vDescrizione, vUrl, Calendar.getInstance()));
            mListView.setAdapter(vPostAdapter);
            vPostAdapter.notifyDataSetChanged();
        }

    }
}
