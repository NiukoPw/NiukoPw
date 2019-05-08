package com.example.libreria;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PostAdapter extends BaseAdapter {

    ArrayList<Post> mObjects;
    Context mContext;

    public PostAdapter(ArrayList<Post> mObjects, Context mContext) {
        this.mObjects = mObjects;
        this.mContext = mContext;

    }

    @Override
    public int getCount() {
        return mObjects.size();
    }

    @Override
    public Object getItem(int position) {
        return mObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mObjects.get(position).getmId();
    }

    class ViewHolder {
        TextView mTitle, mDescription, mAutor;
        ImageView mImageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater vInflater = LayoutInflater.from(mContext);
            convertView = vInflater.inflate(R.layout.cell_post, null);
            TextView vTitle = convertView.findViewById(R.id.textTitolo);
            TextView vDescription = convertView.findViewById(R.id.textDescrizione);
            TextView vAutor = convertView.findViewById(R.id.textAutor);
            ImageView vImageView = convertView.findViewById(R.id.imageView);
            ViewHolder vHolder = new ViewHolder();
            vHolder.mDescription = vDescription;
            vHolder.mTitle = vTitle;
            vHolder.mAutor = vAutor;
            vHolder.mImageView = vImageView;
            convertView.setTag(vHolder);

        }
        final LibraryDB db = new LibraryDB(mContext);
        Post vPost = (Post) getItem(position);
        ViewHolder vHolder = (ViewHolder) convertView.getTag();
        vHolder.mTitle.setText(vPost.getmTitle());
        vHolder.mDescription.setText(vPost.getmDescriptio());
        vHolder.mAutor.setText("" + vPost.getmAutor());
        Glide.with(mContext)
                .load(db.getUrl())
                .centerInside()
                .into(vHolder.mImageView);

        return convertView;

    }
}


