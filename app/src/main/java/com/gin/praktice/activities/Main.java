package com.gin.praktice.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gin.praktice.R;

public class Main extends Activity {
    private Intent dDayIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_main);

        dDayIntent = new Intent(this, DDay.class);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextButton : startActivity(dDayIntent); finish(); break;
            default: break;
        }
    }

}
