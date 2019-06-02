package com.michelezulian.example.niuko.fragments;

import android.annotation.SuppressLint;

import com.michelezulian.example.niuko.data.Corso;

public class LessonCourseFragment extends UserCourseDetailFragment {
    public LessonCourseFragment () { }

    @SuppressLint("ValidFragment")
    public LessonCourseFragment (Corso aCorso) {
        super(aCorso);
    }
}
