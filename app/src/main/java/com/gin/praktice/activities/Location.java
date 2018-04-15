package com.gin.praktice.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gin.praktice.R;

public class Location extends Activity {
    private Intent resultIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_location);

        resultIntent = new Intent(this, Result.class);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextButton : startActivity(resultIntent); finish(); break;
            default: break;
        }
    }
}
