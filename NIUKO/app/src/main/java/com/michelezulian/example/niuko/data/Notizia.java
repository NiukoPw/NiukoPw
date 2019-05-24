package com.michelezulian.example.niuko.data;

import java.util.Date;

public class Notizia {
    int mId;
    String mTitolo, mDescrizione, mImgUrl;
    Date mData;

    public Notizia(int mId, String mTitolo, String mDescrizione, String mImgUrl, Date mData) {
        this.mId = mId;
        this.mTitolo = mTitolo;
        this.mDescrizione = mDescrizione;
        this.mImgUrl = mImgUrl;
        this.mData = mData;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmTitolo() {
        return mTitolo;
    }

    public void setmTitolo(String mTitolo) {
        this.mTitolo = mTitolo;
    }

    public String getmDescrizione() {
        return mDescrizione;
    }

    public void setmDescrizione(String mDescrizione) {
        this.mDescrizione = mDescrizione;
    }

    public String getmImgUrl() {
        return mImgUrl;
    }

    public void setmImgUrl(String mImgUrl) {
        this.mImgUrl = mImgUrl;
    }

    public Date getmData() {
        return mData;
    }

    public void setmData(Date mData) {
        this.mData = mData;
    }
}
