package com.michelezulian.example.niuko.data;

import java.sql.Blob;

public class Corso {
    public String mNomeCorso, mSede, mImgUrl;
    public int mId, mDurata;
    /*
    Blob img;
    descrizione
    stato
    postiLiberi
     */

    public Corso(int aId, String aNomeCorso, String aSede, String aImgUrl, int aDurata) {
        this.mId = aId;
        this.mNomeCorso = aNomeCorso;
        this.mSede = aSede;
        this.mImgUrl = aImgUrl;
        this.mDurata = aDurata;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmDurata() {
        return mDurata;
    }

    public void setmDurata(int mDurata) {
        this.mDurata = mDurata;
    }

    public String getmNomeCorso() {
        return mNomeCorso;
    }

    public void setmNomeCorso(String mNomeCorso) {
        this.mNomeCorso = mNomeCorso;
    }

    public String getmSede() {
        return mSede;
    }

    public void setmSede(String mSede) {
        this.mSede = mSede;
    }

    public String getmImgUrl() {
        return mImgUrl;
    }

    public void setmImgUrl(String mImgUrl) {
        this.mImgUrl = mImgUrl;
    }
}
