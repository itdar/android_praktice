package com.gin.praktice.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.gin.praktice.R;

public class Acty_AddNewMember extends Activity {

    private EditText nameEditText;
//    private Button addButton;
//    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_addnewmember);

        nameEditText = findViewById(R.id.nameEditText);
//        addButton = findViewById(R.id.addButton);
//        cancelButton = findViewById(R.id.cancelButton);

    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.addButton : addButtonAction(); break;
            case R.id.cancelButton : cancelButtonAction(); break;
            default : break;
        }

    }

    private void addButtonAction() {
        //TODO 여기부터 시작 0510

        String name = nameEditText.getText().toString();
        if (name != null && !name.equals("")) {
            Bundle bundle = new Bundle();
            bundle.putString("name", name);

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
