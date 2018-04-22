package com.gin.praktice.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.gin.praktice.R;
import com.gin.praktice.composite.DDay;
import com.gin.praktice.composite.Location;

public class Acty_Location extends Activity {
    private static final int ADD_MEMBER = 1;

    private Intent resultIntent;
    private Intent locationIntent;

    private DDay dDay;

    private EditText storeNameEditText;
    private EditText moneyEditText;

    private ListView locationMemberView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_location);

        dDay = DDay.getInstance();

        resultIntent = new Intent(this, Acty_Result.class);
        locationIntent = new Intent(this, Acty_Location.class);

        storeNameEditText = findViewById(R.id.storeNameEditText);
        moneyEditText = findViewById(R.id.moneyEditText);

        locationMemberView = (ListView) findViewById(R.id.locationMemberView);

//        locationMemberView.add
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
        if (dDay.getList().size() >= 0) {
            // TODO need to check showing added memberList with listView or something.

            startActivity(resultIntent);
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
                case ADD_MEMBER : requestContact(intent); break;
//                case REQUEST_KAKAO : ; break;
            }
        }
    }

    // TODO Need to make request interface
    private void requestContact(Intent intent) {


    }

    private void addMoreLocationButtonAction() {
        Location location = new Location();
        location.setName(storeNameEditText.getText().toString());
        location.setMoney(Integer.parseInt(moneyEditText.getText().toString()));

        dDay.add(location.clone());

        startActivity(locationIntent);
        finish();
    }
}
