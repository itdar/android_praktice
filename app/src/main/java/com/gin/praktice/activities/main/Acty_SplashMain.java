package com.gin.praktice.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class Acty_SplashMain extends AppCompatActivity {

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //db 읽어서 language 설정 해줘야함
//        Config_Language.get();

        this.handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(getApplicationContext(), Acty_Main.class);
                startActivity(intent);
                finish();
            }
        }, 1700);

    }

}
