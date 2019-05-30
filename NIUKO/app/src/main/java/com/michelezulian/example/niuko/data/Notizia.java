package com.michelezulian.example.niuko.data;

public class Notizia {
    int mId;
    String mImgUrl, mTitolo, mData, mDescrizione;

    public Notizia(int mId, String mTitolo, String mDescrizione, String mImgUrl, String mData) {
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

    public String getmData() {
        return mData;
    }

    public void setmData(String mData) {
        this.mData = mData;
    }
}
