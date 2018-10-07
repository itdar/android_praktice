package com.gin.praktice.activities.splitor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.gin.praktice.R;
import com.gin.praktice.component.DDay;
import com.gin.praktice.config.lang.Config_Language;

import java.util.ArrayList;
import java.util.List;

public class Acty_AddMember2Location extends Activity {
    private static final int ADD_MEMBER = 1;

    private ListView locationMemberView;
    private List<String> dayMembersList = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    private TextView lmaAddUsingDDayMemText;
    private Button lmaCancelButton;
    private Button lmaAddButton;

    private DDay dDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_member2location);

        dDay = DDay.getInstance();

        ArrayList<String> existNameList = getIntent().getExtras().getStringArrayList("existNameList");

        getComponentsId();
        setComponentsNames();

        //TODO 멤버추가하는 intent 받을때 location에서 리스트에 이미 있는 이름들 받아서, 없는 이름들만 dayMembersList에 추가하도록
        // 2018-07-26 // 2018-09-24 //
        boolean isExist;
        for (int i = 0; i < dDay.dayMembers.getLength(); ++i)
        {
            isExist = false;
            for (int j = 0; j < existNameList.size(); ++j)
            {
                if (dDay.dayMembers.get(i).getName().equals(existNameList.get(j)))
                {
                    isExist = true;
                }
            }
            if (!isExist)
            {
                dayMembersList.add(dDay.dayMembers.get(i).getName());
            }
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, dayMembersList);
        locationMemberView = (ListView) findViewById(R.id.locationMemberView);
        locationMemberView.setAdapter(adapter);
        locationMemberView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        locationMemberView.setItemsCanFocus(false);

        adapter.notifyDataSetChanged();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lmaAddButton : addButtonAction(); break;
            case R.id.lmaCancelButton : cancelButtonAction(); break;
            default: break;
        }
    }

    private void addButtonAction() {
        ArrayList<String> nameList = new ArrayList<String>();

        SparseBooleanArray checked = locationMemberView.getCheckedItemPositions();
        for (int i = 0; i < locationMemberView.getCount(); ++i) {
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

    private void getComponentsId() {
        lmaAddUsingDDayMemText = (TextView) findViewById(R.id.lmaAddUsingDDayMemText);
        lmaCancelButton = (Button) findViewById(R.id.lmaCancelButton);
        lmaAddButton = (Button) findViewById(R.id.lmaAddButton);
    }

    private void setComponentsNames() {
        lmaAddUsingDDayMemText.setText(Config_Language.get().lmaAddUsingDDayMemText);
        lmaCancelButton.setText(Config_Language.get().lmaCancelButton);
        lmaAddButton.setText(Config_Language.get().lmaAddButton);
    }
}
