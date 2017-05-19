package com.example.vijay.mybloodbank;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by vijay on 12/6/2016.
 */
public class CustomListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] itemname;
    private final String[]  Group;
    private final String[]  City;
    private final String[]  Contact;
   // Integer res;
   // private final Integer[] imgid;

    public CustomListAdapter(Activity context, String[] itemname,String[]group,String[]city,String[]contact) {
        super(context, R.layout.mylist, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.Group=group;
        this.City=city;
        this.Contact=contact;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);
        TextView city = (TextView) rowView.findViewById(R.id.textView2);
        TextView contact = (TextView) rowView.findViewById(R.id.textView3);
        txtTitle.setText(itemname[position]);
        extratxt.setText(Group[position]);
        city.setText(City[position]);
        contact.setText(Contact[position]);
        return rowView;

    };
}
