package com.gin.praktice.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.gin.praktice.R;
import com.gin.praktice.component.DDay;
import com.gin.praktice.component.Location;

import java.util.ArrayList;

public class Acty_Location extends Activity {
    private static final int ADD_MEMBER = 1;

    private Intent resultIntent;
    private Intent locationIntent;

    private DDay dDay;
    private Location location;

    private EditText storeNameEditText;
    private EditText moneyEditText;

    private ListView locationMemberView;
    private ArrayList<String> locationMemberList = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_location);

        dDay = DDay.getInstance();
        location = new Location();

        resultIntent = new Intent(this, Acty_Result.class);
        locationIntent = new Intent(this, Acty_Location.class);

        storeNameEditText = findViewById(R.id.storeNameEditText);
        moneyEditText = findViewById(R.id.moneyEditText);

        locationMemberView = (ListView) findViewById(R.id.locationMemberView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, locationMemberList);
        locationMemberView.setAdapter(adapter);
//        locationMemberView.setItemsCanFocus(true);

        adapter.notifyDataSetChanged();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextButton : nextButtonAction(); break;
            case R.id.addMemberButton : addMemberButtonAction(); break;
            //Save n Add
            case R.id.addMoreLocationButton : addMoreLocationButtonAction(); break;
            default: break;
        }
    }

    private void nextButtonAction() {
        if (locationMemberList.size() >= 1 && storeNameEditText.getText() != null && moneyEditText.getText() != null) {
            location.setName(storeNameEditText.getText().toString());
            location.setMoney(Integer.parseInt(moneyEditText.getText().toString()));

            dDay.add(location.clone());

            startActivity(resultIntent);
            finish();
        } else {
            //error toast
        }

    }

    private void addMemberButtonAction() {
        Intent intent = new Intent(this, Acty_AddMember2Location.class);
        startActivityForResult(intent, ADD_MEMBER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ADD_MEMBER : addMember(intent); break;
//                case REQUEST_KAKAO : ; break;
            }
        }
    }

    // TODO Need to make request interface
    private void addMember(Intent intent) {
//        Toast.makeText(this, "Here I stand for you", Toast.LENGTH_SHORT).show();

        Bundle bundle = intent.getExtras();
        ArrayList<String> nameList = bundle.getStringArrayList("nameList");

        for (int i = 0; i < nameList.size(); i++) {
            adapter.add(nameList.get(i));
        }
        adapter.notifyDataSetChanged();
    }

    private void addMoreLocationButtonAction() {
        if (locationMemberList.size() >= 1 && storeNameEditText.getText() != null && moneyEditText.getText() != null) {
            location.setName(storeNameEditText.getText().toString());
            location.setMoney(Integer.parseInt(moneyEditText.getText().toString()));

            dDay.add(location.clone());

            startActivity(locationIntent);
            finish();
        }
    }
}
