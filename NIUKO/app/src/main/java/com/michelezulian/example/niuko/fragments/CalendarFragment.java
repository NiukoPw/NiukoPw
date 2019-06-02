package com.michelezulian.example.niuko.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.michelezulian.example.niuko.R;
import com.michelezulian.example.niuko.adapters.CorsoAdapter;
import com.michelezulian.example.niuko.adapters.LezioneAdapter;
import com.michelezulian.example.niuko.data.Corso;
import com.michelezulian.example.niuko.data.Lezione;
import com.michelezulian.example.niuko.data.Utente;
import com.michelezulian.example.niuko.misc.ConnectionSingleton;
import com.michelezulian.example.niuko.misc.FragmentListener;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EmptyStackException;

import static com.michelezulian.example.niuko.misc.StaticValues.IMG_URL;
import static com.michelezulian.example.niuko.misc.StaticValues.URL_ALL_COURSES;
import static com.michelezulian.example.niuko.misc.StaticValues.URL_COURSE_FROM_LESSON;
import static com.michelezulian.example.niuko.misc.StaticValues.URL_USER_LESSONS;

public class CalendarFragment extends Fragment {
    CompactCalendarView mCalendar;
    ArrayList<Event> mEventi;
    ArrayList<Lezione> mLezioni;
    ListView mListViewLezioni;
    LezioneAdapter mAdapter;
    Utente mUtente;
    FragmentListener mListener;
    SimpleDateFormat mDateFormat, mMonthFormat;
    TextView mMese;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.fragment_calendar, container, false);
        mUtente = mListener.getUtente();
        mDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        mMonthFormat = new SimpleDateFormat("MMM yyyy");

        mMese = vView.findViewById(R.id.calendarMonth);

        mCalendar = vView.findViewById(R.id.calendarCalendarView);
        mCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                ArrayList<Event> vDayEvents = (ArrayList<Event>) mCalendar.getEvents(dateClicked);

                mLezioni = new ArrayList<>();
                for (int i = 0; i < vDayEvents.size(); i++) {
                    mLezioni.add((Lezione) vDayEvents.get(i).getData());
                }

                mAdapter = new LezioneAdapter(mLezioni, getActivity());
                mListViewLezioni.setAdapter(mAdapter);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                mMese.setText(mMonthFormat.format(mCalendar.getFirstDayOfCurrentMonth()));
            }
        });

        mListViewLezioni = vView.findViewById(R.id.calendarListView);
        mListViewLezioni.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                JSONObject vParameters = new JSONObject();
                try {
                    vParameters.put("id", mLezioni.get(position).getMid());
                    JsonObjectRequest vJsonRequest = new JsonObjectRequest(
                            Request.Method.POST,
                            URL_COURSE_FROM_LESSON,
                            vParameters,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        Log.d("risposta", "Risposta: " + response.toString());
                                        Corso vCorso = new Corso(
                                                response.getInt("id"),
                                                response.getString("titolo"),
                                                response.getString("sede"),
                                                response.getInt("durata"),
                                                response.getString("descrizione"),
                                                response.getString("stato"),
                                                response.getInt("postiLiberi"),
                                                IMG_URL
                                        );

                                        FragmentManager vManager = getFragmentManager();
                                        FragmentTransaction vTransaction = vManager.beginTransaction();
                                        vTransaction.replace(R.id.fragment_container, new LessonCourseFragment(vCorso));
                                        vTransaction.commit();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                }
                            }
                    );

                    ConnectionSingleton.getInstance(getActivity()).addToRequestQueue(vJsonRequest);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // creo stringa di richiesta
        try {
            JSONObject vParameters = new JSONObject();
            vParameters.put("id", mUtente.getmId());
            JsonObjectRequest vJsonRequest = new JsonObjectRequest
                    (Request.Method.POST,
                            URL_USER_LESSONS,
                            vParameters,
                            new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.d("risposta", "Risposta: " + response.toString());
                                    try {
                                        JSONArray vRecords = response.getJSONArray("records");

                                        Lezione vLezione;
                                        long vLessonEpoch;
                                        mEventi = new ArrayList<>();
                                        /***** I = 0 DOPO AVER SISTEMATO DB *****/
                                        for (int i = 0; i < vRecords.length(); i++) {
                                            JSONObject vCurrent = vRecords.getJSONObject(i);

                                            vLezione = new Lezione(
                                                    vCurrent.getInt("id"),
                                                    vCurrent.getInt("durata"),
                                                    vCurrent.getString("titolo"),
                                                    vCurrent.getString("descrizione"),
                                                    vCurrent.getString("sede"),
                                                    mDateFormat.parse(vCurrent.getString("data"))
                                            );

                                            vLessonEpoch = vLezione.getmData().getTime();
                                            mEventi.add(new Event(R.color.vermilion, vLessonEpoch, vLezione));
                                        }

                                        mCalendar.addEvents(mEventi);

                                    } catch (Exception e) {
                                        Log.d("risposta", "Errore: " + e.toString());
                                        Toast.makeText(getActivity(), "Oops! C'è stato un errore", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("risposta", "Errore: " + error.toString());
                            Toast.makeText(getActivity(), "Oops! C'è stato un errore", Toast.LENGTH_SHORT).show();
                        }
                    });

            // eseguo richiesta
            ConnectionSingleton.getInstance(getActivity()).addToRequestQueue(vJsonRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMese.setText(mMonthFormat.format(mCalendar.getFirstDayOfCurrentMonth()));
        return vView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof FragmentListener) {
            mListener = (FragmentListener) activity;
        } else {
            mListener = null;
        }
    }
}
