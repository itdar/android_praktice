package com.gin.praktice.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gin.praktice.R;
import com.gin.praktice.composite.DDay;

import java.util.ArrayList;

public class Acty_AddMember2Location extends Activity {


    private ListView locationMemberView;
    private ArrayList<String> locationMemberList = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    private DDay dDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_member2location);

        dDay = DDay.getInstance();

        locationMemberView = (ListView) findViewById(R.id.locationMemberView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, locationMemberList);
        locationMemberView.setAdapter(adapter);

        for (int i = 0; i < dDay.members.getLength(); i++) {
            adapter.add(dDay.members.get(i).getName());
        }
        adapter.notifyDataSetChanged();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contactAddButton : addButtonAction(); break;
            case R.id.deleteButton : cancelButtonAction(); break;
            default: break;
        }
    }

    private void addButtonAction() {

    }

    private void cancelButtonAction() {

    }

    public void addItems(String receiveName) {
        adapter.add(receiveName);
        adapter.notifyDataSetChanged();
    }

}
