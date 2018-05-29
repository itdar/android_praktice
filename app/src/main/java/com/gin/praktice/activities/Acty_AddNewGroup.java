package com.gin.praktice.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.gin.praktice.R;

public class Acty_AddNewGroup extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_addnewgroup);



    }

    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.newDDayButton : newDDayButtonAction(); break;
//            case R.id.newGroupButton : newGroupButtonAction(); break;
            default: break;
        }
    }


}
