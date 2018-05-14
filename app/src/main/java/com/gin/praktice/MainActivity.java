/*
package com.gin.praktice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gin.praktice.activities.Acty_Main;

public class MainActivity extends Activity {
    private Intent mainIntent;

    private EditText idEditText;
    private EditText pwdEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainIntent = new Intent(this, Acty_Main.class);

        idEditText = (EditText)findViewById(R.id.idEditText);
        pwdEditText = (EditText)findViewById(R.id.pwdEditText);


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginButton : idEditText.setText("IDIDIDIDIDID"); break;
            case R.id.toastButton : Toast.makeText(this, "This is Toast msg from gin", Toast.LENGTH_LONG).show(); break;
            case R.id.buzzButton : pwdEditText.setText("PWDDPWDPWDP");
                            ((Vibrator)getSystemService(Context.VIBRATOR_SERVICE)).vibrate(4000); break;
            case R.id.nextButton : startActivity(mainIntent); break;
            default: break;
        }
    }
}
*/
