package com.gin.praktice.activities.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gin.praktice.R;
import com.gin.praktice.activities.splitor.Acty_MainSplitor;

public class Acty_Main extends Activity {

    Intent mainSplitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_main);

        mainSplitor = new Intent(this, Acty_MainSplitor.class);

    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mainSplitorButton : mainSplitorButtonAction(); break;
            default: break;
        }
    }

    private void mainSplitorButtonAction() {

        startActivity(mainSplitor);
        finish();
    }


}
