package com.michelezulian.example.niuko.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.michelezulian.example.niuko.R;
import com.michelezulian.example.niuko.data.Corso;

import java.util.ArrayList;

public class CorsoAdapter extends BaseAdapter {
    private static final String TITOLO = "Corso: ";
    private static final String DURATA = "Durata: ";
    private static final String ORE = " ore";
    private static final String SEDE = "Sede: ";

    ArrayList<Corso> mCorsi;
    Context mContext;

    public CorsoAdapter(ArrayList<Corso> mCorsi, Context mContext) {
        this.mCorsi = mCorsi;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mCorsi.size();
    }

    @Override
    public Object getItem(int position) {
        return mCorsi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mCorsi.get(position).getmId();
    }

    class ViewHolder {
        TextView mTitolo, mSede, mDurata;
        ImageView mImmagine;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater vInflater = LayoutInflater.from(mContext);
            convertView = vInflater.inflate(R.layout.cell_corso, null);

            TextView vTitolo = convertView.findViewById(R.id.corsoTextViewTitolo);
            TextView vSede = convertView.findViewById(R.id.corsoTextViewSede);
            TextView vDurata = convertView.findViewById(R.id.corsoTextViewDurata);
            ImageView vImmagine = convertView.findViewById(R.id.corsoImageView);


            ViewHolder vHolder = new ViewHolder();
            vHolder.mTitolo = vTitolo;
            vHolder.mSede = vSede;
            vHolder.mDurata = vDurata;
            vHolder.mImmagine = vImmagine;

            convertView.setTag(vHolder);
        }

        Corso vCorso = (Corso) getItem(position);
        ViewHolder vHolder = (ViewHolder) convertView.getTag();
        vHolder.mTitolo.setText(TITOLO + vCorso.getmNomeCorso());
        vHolder.mSede.setText(SEDE + vCorso.getmSede());
        vHolder.mDurata.setText(DURATA + vCorso.getmDurata() + ORE);
        Glide.with(mContext)
                .load(vCorso.getmImgUrl())
                .centerInside()
                .into(vHolder.mImmagine);

        return convertView;
    }
}
