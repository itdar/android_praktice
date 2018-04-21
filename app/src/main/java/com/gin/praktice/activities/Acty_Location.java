package com.gin.praktice.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

import com.gin.praktice.R;

public class Acty_Location extends Activity {
    private Intent resultIntent;
    private Intent locationIntent;

    private ScrollView locationMemberView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_location);

        resultIntent = new Intent(this, Acty_Result.class);
        locationIntent = new Intent(this, Acty_Location.class);

        locationMemberView = (ScrollView) findViewById(R.id.locationMemberView);

//        locationMemberView.add
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextButton : startActivity(resultIntent); break;
            case R.id.addMemberButton : /*startActivity(resultIntent)*/; break;
            case R.id.addLocationButton : startActivity(locationIntent); finish(); break;
            default: break;
        }
    }
}
