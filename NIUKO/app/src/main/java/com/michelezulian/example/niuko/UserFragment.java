package com.michelezulian.example.niuko;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class UserFragment extends Fragment {
    ListView listView;
    String[] nameArray = {"Comunicazioni","Corsi frequentati","in aula","Modifica profilo","impostazioni"};

    Integer[] imageArray = {
            R.drawable.paper_plane,
            R.drawable.settings,
            R.drawable.successful,
            R.drawable.users,
            R.drawable.classroom};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.fragment_user, container, false);
        return vView;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CustomListAdapter whatever = new CustomListAdapter(this,imageArray, nameArray);

        listView = (ListView) getView().findViewById(R.id.listview);
        listView.setAdapter(whatever);
    }
}
