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
import com.michelezulian.example.niuko.data.Notizia;

import java.util.ArrayList;

public class NotiziaAdapter extends BaseAdapter {
    private static final String TITOLO = "Notizia: ";
    private static final String DATA = "Data: ";
    ArrayList<Notizia> mNotizie;
    Context mContext;

    public NotiziaAdapter(ArrayList<Notizia> mNotizie, Context mContext) {
        this.mNotizie = mNotizie;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mNotizie.size();
    }

    @Override
    public Object getItem(int position) {
        return mNotizie.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mNotizie.get(position).getmId();
    }

    class ViewHolder {
        TextView mTitolo, mData;
        ImageView mImmagine;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater vInflater = LayoutInflater.from(mContext);
            convertView = vInflater.inflate(R.layout.cell_notizia, null);

            TextView vTitolo = convertView.findViewById(R.id.notiziaTextViewTitolo);
            TextView vData = convertView.findViewById(R.id.notiziaTextViewData);
            ImageView vImmagine = convertView.findViewById(R.id.notiziaImageView);


            ViewHolder vHolder = new ViewHolder();
            vHolder.mTitolo = vTitolo;
            vHolder.mData = vData;
            vHolder.mImmagine = vImmagine;

            convertView.setTag(vHolder);
        }

        Notizia vNotizia = (Notizia) getItem(position);
        ViewHolder vHolder = (ViewHolder) convertView.getTag();
        vHolder.mTitolo.setText(TITOLO + vNotizia.getmTitolo());
        vHolder.mData.setText(DATA + vNotizia.getmData());
        Glide.with(mContext)
                .load(vNotizia.getmImgUrl())
                .centerInside()
                .into(vHolder.mImmagine);

        return convertView;
    }
}
