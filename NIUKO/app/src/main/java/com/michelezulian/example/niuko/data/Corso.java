package com.michelezulian.example.niuko.data;

public class Corso {
    public String mNomeCorso, mSede, mDescrizione, mStato, mImgUrl;
    public int mId, mDurata, mPostiLiberi;

    public Corso(int mId, String mNomeCorso, String mSede, int mDurata, String mDescrizione, String mStato, int mPostiLiberi, String mImgUrl) {
        this.mNomeCorso = mNomeCorso;
        this.mSede = mSede;
        this.mDescrizione = mDescrizione;
        this.mStato = mStato;
        this.mImgUrl = mImgUrl;
        this.mId = mId;
        this.mDurata = mDurata;
        this.mPostiLiberi = mPostiLiberi;
    }

    public String getmNomeCorso() {
        return mNomeCorso;
    }

    public String getmSede() {
        return mSede;
    }

    public String getmDescrizione() {
        return mDescrizione;
    }

    public String getmStato() {
        return mStato;
    }

    public String getmImgUrl() {
        return mImgUrl;
    }

    public int getmId() {
        return mId;
    }

    public int getmDurata() {
        return mDurata;
    }

    public int getmPostiLiberi() {
        return mPostiLiberi;
    }
}
