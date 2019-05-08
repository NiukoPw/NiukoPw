package com.example.libreria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class InsertNewBooks extends AppCompatActivity {//MI PERMETTE DI AGGIUNGERE UN LIBRO
    EditText edit_titolo;
    EditText edit_descrizione;
    EditText edit_autore;
    EditText edit_url;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_new_books);
        final LibraryDB db = new LibraryDB(this);
        edit_titolo = findViewById(R.id.titolo);
        edit_descrizione = findViewById(R.id.descrizione);
        edit_autore = findViewById(R.id.autore);
        edit_url = findViewById(R.id.urlImg);
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //Quando premo il tasto invia inserisco il libro nel database e vado alla MainActivity
                db.open();
                db.inserisciLibro(edit_titolo.getText().toString(), edit_descrizione.getText().toString(), edit_autore.getText().toString(),edit_url.getText().toString());
                setResult(2000);
                db.close();
                finish();
            }
        });
    }
}
