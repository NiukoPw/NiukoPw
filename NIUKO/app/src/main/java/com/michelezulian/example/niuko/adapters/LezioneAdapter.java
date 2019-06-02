package com.michelezulian.example.niuko.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.michelezulian.example.niuko.R;
import com.michelezulian.example.niuko.data.Corso;
import com.michelezulian.example.niuko.data.Lezione;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.michelezulian.example.niuko.misc.StaticValues.ORE;

public class LezioneAdapter extends BaseAdapter {
    ArrayList<Lezione> mLezioni;
    Context mContext;

    public LezioneAdapter(ArrayList<Lezione> mLezioni, Context mContext) {
        this.mLezioni = mLezioni;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mLezioni.size();
    }

    @Override
    public Object getItem(int position) {
        return mLezioni.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mLezioni.get(position).getMid();
    }

    class ViewHolder {
        TextView mTitolo, mDescrizione, mData, mSede, mDurata;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater vInflater = LayoutInflater.from(mContext);
            convertView = vInflater.inflate(R.layout.cell_lezione, null);

            TextView vTitolo = convertView.findViewById(R.id.lezioneTitolo);
            TextView vDescrizione = convertView.findViewById(R.id.lezioneDescrizione);
            TextView vData = convertView.findViewById(R.id.lezioneData);
            TextView vSede = convertView.findViewById(R.id.lezioneSede);
            TextView vDurata = convertView.findViewById(R.id.lezioneDurata);

            ViewHolder vHolder = new ViewHolder();
            vHolder.mTitolo = vTitolo;
            vHolder.mDescrizione = vDescrizione;
            vHolder.mData = vData;
            vHolder.mSede = vSede;
            vHolder.mDurata = vDurata;

            convertView.setTag(vHolder);
        }

        Lezione vLezione = (Lezione) getItem(position);
        ViewHolder vHolder = (ViewHolder) convertView.getTag();
        vHolder.mTitolo.setText(vLezione.getmTitolo());
        vHolder.mDescrizione.setText(vLezione.getmDescrizione());
        vHolder.mSede.setText(vLezione.getmSede());
        vHolder.mDurata.setText(vLezione.getmDurata() + ORE);

        SimpleDateFormat vFormat = new SimpleDateFormat("dd/MM/yyy ");
        vHolder.mData.setText("" + vFormat.format(vLezione.getmData()));

        return convertView;
    }
}
