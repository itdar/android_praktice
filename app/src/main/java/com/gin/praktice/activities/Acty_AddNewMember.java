package com.gin.praktice.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.gin.praktice.R;

public class Acty_AddNewMember extends Activity {

    private EditText nameEditText;
    private EditText bankEditText;
    private EditText accountEditText;
//    private Button addButton;
//    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_addnewmember);

        nameEditText = findViewById(R.id.nameEditText);
        bankEditText = findViewById(R.id.bankEditText);
        accountEditText = findViewById(R.id.accountEditText);
//        addButton = findViewById(R.id.addButton);
//        cancelButton = findViewById(R.id.cancelButton);

        nameEditText.setOnFocusChangeListener(new TextView.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager imm = (InputMethodManager) nameEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(nameEditText.getWindowToken(), 0);
                }
            }
        });


    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.addButton : addButtonAction(); break;
            case R.id.cancelButton : cancelButtonAction(); break;
            default : break;
        }

    }

    //Component serialize 상속받아서 객체로 member를 전달할수 있음, 나중에 바꾸면 될듯
    private void addButtonAction() {

        String name = nameEditText.getText().toString();
        String bank = bankEditText.getText().toString();
        String account = accountEditText.getText().toString();
        if (name != null && !name.equals("")) {
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            bundle.putString("bank", bank);
            bundle.putString("account", account);

            Intent returnIntent = new Intent();
            returnIntent.putExtras(bundle);

            setResult(RESULT_OK, returnIntent);
            finish();
        }
    }

    private void cancelButtonAction() {
        finish();
    }

}
