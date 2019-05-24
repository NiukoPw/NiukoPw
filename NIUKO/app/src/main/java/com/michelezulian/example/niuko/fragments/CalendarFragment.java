package com.michelezulian.example.niuko.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.michelezulian.example.niuko.R;

public class CalendarFragment extends Fragment {
    CalendarView mCalendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.fragment_calendar, container, false);

        mCalendar = vView.findViewById(R.id.calendarCalendarView);


        return vView;
    }
}
