package com.gin.praktice.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gin.praktice.R;

public class Acty_Main extends Activity {
    private Intent dDayIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_main);

        dDayIntent = new Intent(this, Acty_DDay.class);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.newDDayButton : newDDayButtonAction(); break;
            default: break;
        }
    }

    public void newDDayButtonAction() {
        Toast.makeText(this, "newDDayButtonAction", Toast.LENGTH_LONG).show();

        startActivity(dDayIntent);
    }

}
