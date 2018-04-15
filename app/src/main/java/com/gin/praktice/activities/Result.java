package com.gin.praktice.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.gin.praktice.R;

public class Result extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_result);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.doneButton : finish(); break;
            default: break;
        }
    }

}
