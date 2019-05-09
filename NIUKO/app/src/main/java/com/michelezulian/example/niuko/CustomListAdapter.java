package com.michelezulian.example.niuko;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomListAdapter extends ArrayAdapter {
    //to reference the Activity
    private final UserFragment context;

    //to store the animal images
    private final Integer[] imageIDarray;

    //to store the list of countries
    private final String[] nameArray;

    public CustomListAdapter(UserFragment context, Integer[] imageIDarray, String[] nameArray){

        super(context,R.layout.listview_user_fragment , nameArray;

        this.context = context;
        this.imageIDarray = imageIDarray;
        this.nameArray = nameArray;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_user_fragment, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameTextField = (TextView) rowView.findViewById(R.id.testo);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.immaggine);

        //this code sets the values of the objects to values from the arrays
        nameTextField.setText(nameArray[position]);
        imageView.setImageResource(imageIDarray[position]);

        return rowView;

    };

}
