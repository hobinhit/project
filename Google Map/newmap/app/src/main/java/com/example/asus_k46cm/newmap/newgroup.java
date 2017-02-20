package com.example.asus_k46cm.newmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;


public class newgroup extends AppCompatActivity {
    Button btOK;
    EditText edNameGroup;
    ParseObject parseObject;
    ParseQuery<ParseObject> query;
    ParseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgroup);
        btOK=(Button)findViewById(R.id.btOKGroup);
        edNameGroup=(EditText)findViewById(R.id.edNameGroup);
        user = ParseUser.getCurrentUser();
        btOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edNameGroup.getTextSize() == 0) {
                    Toast.makeText(getApplicationContext(), "Phải nhập tên nhóm", Toast.LENGTH_SHORT).show();
                } else {
                    query = ParseQuery.getQuery("GroupData");
                    query.whereEqualTo("groupName", edNameGroup.getText().toString());
                    query.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> list, ParseException e) {
                            if (e == null) {
                                if (list.size() != 0) {
                                    Toast.makeText(getApplicationContext(), "Tên nhóm đã tồn tại", Toast.LENGTH_SHORT).show();
                                } else {
                                    ParseObject parseObject = new ParseObject("GroupData");
                                    parseObject.put("groupName", edNameGroup.getText().toString());
                                    parseObject.put("userName", user);
                                    parseObject.put("captain", user);
                                    parseObject.saveInBackground();
                                    Toast.makeText(getApplicationContext(), "Tạo Nhóm Thành công", Toast.LENGTH_SHORT).show();

                                    user = ParseUser.getCurrentUser();
                                    //lấy tên nhóm
                                    final ArrayList<Show> arrList=new ArrayList<Show>();

                                    query = ParseQuery.getQuery("GroupData");
                                    query.whereEqualTo("userName", user);
                                    query.findInBackground(new FindCallback<ParseObject>() {
                                        @Override
                                        public void done(List<ParseObject> list, ParseException e) {
                                            if (e == null) {

                                                for (ParseObject g : list) {
                                                    arrList.add(new Show(g.getString("groupName")));
                                                    shown(arrList, g);
                                                }

                                                // Toast.makeText(getApplicationContext(), arrList.toString(), Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                            //Toast.makeText(getApplicationContext(), arrList.toString(), Toast.LENGTH_SHORT).show();
                                        }

                                    });
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
                finish();
            }
        });

    }
    public void shown(ArrayList<Show> arrList,ParseObject g)
    {
        ListAdapter adapter = new ListAdapter(this,R.layout.activity_text_view,arrList,g);
        ListView lvNameGroup = (ListView) group.ac.findViewById(R.id.lvNameGroup);
        //adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrList);
        lvNameGroup.setAdapter(adapter);
    }

}
