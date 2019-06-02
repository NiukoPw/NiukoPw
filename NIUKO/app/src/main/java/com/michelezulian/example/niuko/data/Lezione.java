package com.michelezulian.example.niuko.data;

import java.util.Date;

public class Lezione {
    int mid, mDurata;
    String mTitolo, mDescrizione, mSede;
    Date mData;

    public Lezione(int mid, int mDurata, String mTitolo, String mDescrizione, String mSede, Date mData) {
        this.mid = mid;
        this.mDurata = mDurata;
        this.mTitolo = mTitolo;
        this.mDescrizione = mDescrizione;
        this.mSede = mSede;
        this.mData = mData;
    }

    public int getMid() {
        return mid;
    }

    public String getmTitolo() {
        return mTitolo;
    }

    public String getmDescrizione() {
        return mDescrizione;
    }

    public Date getmData() {
        return mData;
    }

    public int getmDurata() {
        return mDurata;
    }

    public String getmSede() {
        return mSede;
    }
}
