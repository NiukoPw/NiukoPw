package com.michelezulian.example.niuko.data;

public class Utente {
    private String mNome, mCognome, mImgUrl, mNomeUtente;
    private boolean mAmministratore;
    private int mId;

    public Utente(String mNome, String mCognome, String mImgUrl, String mNomeUtente, boolean mAmministratore, int mId) {
        this.mNome = mNome;
        this.mCognome = mCognome;
        this.mImgUrl = mImgUrl;
        this.mNomeUtente = mNomeUtente;
        this.mAmministratore = mAmministratore;
        this.mId = mId;
    }

    public String getmNome() {
        return mNome;
    }

    public String getmCognome() {
        return mCognome;
    }

    public String getmImgUrl() {
        return mImgUrl;
    }

    public String getmNomeUtente() {
        return mNomeUtente;
    }

    public boolean ismAmministratore() {
        return mAmministratore;
    }

    public int getmId() {
        return mId;
    }
}
