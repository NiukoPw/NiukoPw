package com.michelezulian.example.niuko.data;

public class Corso {
    public String mNomeCorso, mSede, mDescrizione, mImgUrl;
    public int mId, mDurata;
    /*
    Blob img;
    stato
    postiLiberi
     */

    public Corso(int aId, String aNomeCorso, String aDescrizione, String aSede, String aImgUrl, int aDurata) {
        this.mId = aId;
        this.mNomeCorso = aNomeCorso;
        this.mDescrizione = aDescrizione;
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

    public String getmDescrizione() {
        return mDescrizione;
    }

    public void setmDescrizione(String mDescrizione) {
        this.mDescrizione = mDescrizione;
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
