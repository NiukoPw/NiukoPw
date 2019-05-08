package com.example.libreria;

import java.util.Calendar;

public class Post {
    private int mId;
    private String mAutor;
    private String mTitle, mDescriptio, mImage;
    private Calendar mPublishDate;

    public Post(int mId, String mAutor, String mTitle, String mDescriptio, String mImage, Calendar mPublishDate) {
        this.mId = mId;
        this.mAutor = mAutor;
        this.mTitle = mTitle;
        this.mDescriptio = mDescriptio;
        this.mImage = mImage;
        this.mPublishDate = mPublishDate;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public void setmAutor(String mAutor) {
        this.mAutor = mAutor;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmDescriptio(String mDescriptio) {
        this.mDescriptio = mDescriptio;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public void setmPublishDate(Calendar mPublishDate) {
        this.mPublishDate = mPublishDate;
    }

    public int getmId() {
        return mId;
    }

    public String getmAutor() {
        return mAutor;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDescriptio() {
        return mDescriptio;
    }

    public String getmImage() {
        return mImage;
    }

    public Calendar getmPublishDate() {
        return mPublishDate;
    }
}