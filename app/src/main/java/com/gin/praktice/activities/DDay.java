package com.gin.praktice.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gin.praktice.R;

public class DDay extends Activity {
    private Intent locationIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_dday);

        locationIntent = new Intent(this, Location.class);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextButton : startActivity(locationIntent); finish(); break;
            default: break;
        }
    }
}
