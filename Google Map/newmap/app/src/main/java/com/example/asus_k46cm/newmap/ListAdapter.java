package com.example.asus_k46cm.newmap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by ASUS_K46CM on 18/03/2016.
 */
public class ListAdapter extends ArrayAdapter<Show> {

    public ListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }
   //truyền dữ liệu parse;
   Context context;
   ParseObject g=null;
    //contructor
    public ListAdapter(Context context, int resource, List<Show> items,ParseObject myObject) {
        super(context, resource, items);
         g= myObject;
        this.context =context;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.activity_text_view, null);
        }

        Show p = getItem(position);

        if (p != null) {
            // Anh xa + Gan gia tri
            TextView tvNameGroup = (TextView) v.findViewById(R.id.tvNameGroup);
            tvNameGroup.setText(p.Namegroup);
            Button btOut =(Button) v.findViewById(R.id.btOut);
            final String s=p.Namegroup;
            btOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Delete a= new Delete(g,s);
                    a.Delete_Del();
                }
            });
            Button btin =(Button) v.findViewById(R.id.btThamGia);
            btin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent Mapintent = new Intent(context,MapsActivity.class);
                    Mapintent.putExtra("namegroup",s.toString());
                    context.startActivity(Mapintent);
                }
            });
        }

        return v;
    }



//    public void Delete(String s)
//    {
//        g.deleteInBackground();
//        g.remove(s);
//        g.saveInBackground();
//
//    }



}