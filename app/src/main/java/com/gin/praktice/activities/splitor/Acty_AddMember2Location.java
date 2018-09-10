package com.gin.praktice.activities.splitor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gin.praktice.R;
import com.gin.praktice.component.DDay;

import java.util.ArrayList;
import java.util.List;

public class Acty_AddMember2Location extends Activity {
    private static final int ADD_MEMBER = 1;

    private ListView locationMemberView;
    private List<String> dayMembersList = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    private DDay dDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_member2location);

        dDay = DDay.getInstance();


        //TODO 멤버추가하는 intent 받을때 location에서 리스트에 이미 있는 이름들 받아서, 없는 이름들만 dayMembersList에 추가하도록
        //TODO 2018-07-26
        for (int i = 0; i < dDay.dayMembers.getLength(); i++) {
            dayMembersList.add(dDay.dayMembers.get(i).getName());
        }
//        dayMembersList = dDay.dayMembers.clone().getList();


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, dayMembersList);
        locationMemberView = (ListView) findViewById(R.id.locationMemberView);
        locationMemberView.setAdapter(adapter);
        locationMemberView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        locationMemberView.setItemsCanFocus(false);

//        for (int i = 0; i < dDay.members.getLength(); i++) {
//            adapter.add(dDay.members.get(i));
//        }
        adapter.notifyDataSetChanged();


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addButton : addButtonAction(); break;
            case R.id.cancelButton : cancelButtonAction(); break;
            default: break;
        }
    }

    private void addButtonAction() {
        ArrayList<String> nameList = new ArrayList<String>();

        SparseBooleanArray checked = locationMemberView.getCheckedItemPositions();
        for (int i = 0; i < locationMemberView.getCount(); i++) {
            if (checked.get(i)) {
                nameList.add(dayMembersList.get(i));
            }
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("nameList", nameList);

        Intent returnIntent = new Intent();
        returnIntent.putExtras(bundle);

        setResult(RESULT_OK, returnIntent);
        finish();
    }

    private void cancelButtonAction() {

        finish();
    }

//    public void addItems(String receiveName) {
//        adapter.add(receiveName);
//        adapter.notifyDataSetChanged();
//    }

}