package com.example.asus_k46cm.newmap;

import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS_K46CM on 19/03/2016.
 */
public class Delete {
    public String s;
    public ParseObject g;

    public Delete(ParseObject g, String s) {
        this.g = g;
        this.s = s;
    }

    public void Delete_Del()
    {
        g.deleteInBackground();
        g.remove(s);
        g.saveInBackground();
        ParseUser user = ParseUser.getCurrentUser();
        //lấy tên nhóm
        final ArrayList<Show> arrList=new ArrayList<Show>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("GroupData");
        query.whereEqualTo("userName", user);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    for (ParseObject g : list) {
                        arrList.add(new Show(g.getString("groupName")));
                        shown(arrList, g);
                    }

                     Toast.makeText(group.ac,"xóa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(group.ac, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(getApplicationContext(), arrList.toString(), Toast.LENGTH_SHORT).show();
            }

        });

    }
    public void shown(ArrayList<Show> arrList,ParseObject g)
    {
        ListAdapter adapter = new ListAdapter(group.ac,R.layout.activity_text_view,arrList,g);
        ListView lvNameGroup = (ListView) group.ac.findViewById(R.id.lvNameGroup);
        //adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrList);
        lvNameGroup.setAdapter(adapter);
    }
}
