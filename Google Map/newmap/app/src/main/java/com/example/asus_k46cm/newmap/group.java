package com.example.asus_k46cm.newmap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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

public class group extends AppCompatActivity {
    Button btNewgroup, btAdd,btSub,btRefresh;
    ListView lvNameGroup;
    ArrayList<Show> arrList;
    ArrayAdapter<String> adapter=null;
    ParseQuery<ParseObject> query;
    ParseUser user;
    public static Activity ac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        btNewgroup=(Button)findViewById(R.id.btNewGroup);
        lvNameGroup=(ListView)findViewById(R.id.lvNameGroup);
        ac = this;
        btNewgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(group.this, newgroup.class);
                startActivity(intent);
                Query();

            }
        });
       Query();
        btRefresh=(Button)findViewById(R.id.btRefresh);
        btRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query();
            }
        });
        lvNameGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"OnClick:"+ position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        lvNameGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(),"OnClick:"+ position, Toast.LENGTH_SHORT).show();
            }
        });



    }
    public void Query()
    {
        user = ParseUser.getCurrentUser();
        //lấy tên nhóm
        arrList=new ArrayList<Show>();

        query = ParseQuery.getQuery("GroupData");
        query.whereEqualTo("userName", user);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    for (ParseObject g : list) {
                        arrList.add(new Show(g.getString("groupName")));
                        shown(arrList,g);
                    }

                    // Toast.makeText(getApplicationContext(), arrList.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(getApplicationContext(), arrList.toString(), Toast.LENGTH_SHORT).show();
            }

        });

    }
    public void shown(ArrayList<Show> arrList,ParseObject g)
    {
        ListAdapter adapter = new ListAdapter(this,R.layout.activity_text_view,arrList,g);
        //adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrList);
        lvNameGroup.setAdapter(adapter);
    }

}

