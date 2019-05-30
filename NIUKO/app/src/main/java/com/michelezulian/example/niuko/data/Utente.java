package com.michelezulian.example.niuko.data;

public class Utente {
    String mNome, mCognome, mCodiceFiscale, mImgUrl, mNomeUtente, mPassword;
    boolean mAmministratore;
    int mId;

    public Utente(String mNome, String mCognome, String mCodiceFiscale, String mImgUrl, String mNomeUtente, String mPassword, boolean mAmministratore, int mId) {
        this.mNome = mNome;
        this.mCognome = mCognome;
        this.mCodiceFiscale = mCodiceFiscale;
        this.mImgUrl = mImgUrl;
        this.mNomeUtente = mNomeUtente;
        this.mPassword = mPassword;
        this.mAmministratore = mAmministratore;
        this.mId = mId;
    }

    public String getmNome() {
        return mNome;
    }

    public String getmCognome() {
        return mCognome;
    }

    public String getmCodiceFiscale() {
        return mCodiceFiscale;
    }

    public String getmImgUrl() {
        return mImgUrl;
    }

    public String getmNomeUtente() {
        return mNomeUtente;
    }

    public String getmPassword() {
        return mPassword;
    }

    public boolean ismAmministratore() {
        return mAmministratore;
    }

    public int getmId() {
        return mId;
    }
}
