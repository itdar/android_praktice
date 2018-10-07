package com.gin.praktice.activities.splitor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gin.praktice.R;
import com.gin.praktice.config.lang.Config_Language;

public class Acty_AddNewMember extends Activity {

    private TextView amdAddMemberText;
    private TextView amdNameText;
    private TextView amdBankNameText;
    private TextView amdBankAccountText;
    private TextView amdRemarkText;
    private EditText amdNameEditText;
    private EditText amdBankNameEditText;
    private EditText amdBankAccountEditText;
    private EditText amdRemarkEditText;
    private Button amdCancelButton;
    private Button amdAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_addnewmember);

        getComponentsId();
        setComponentsNames();

        amdNameEditText = findViewById(R.id.amdNameEditText);
        amdBankNameEditText = findViewById(R.id.amdBankNameEditText);
        amdBankAccountEditText = findViewById(R.id.amdBankAccountEditText);
        amdRemarkEditText = findViewById(R.id.amdRemarkEditText);

        amdNameEditText.setOnFocusChangeListener(new TextView.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager imm = (InputMethodManager) amdNameEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(amdNameEditText.getWindowToken(), 0);
                }
            }
        });


    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.amdAddButton : addButtonAction(); break;
            case R.id.amdCancelButton : cancelButtonAction(); break;
            default : break;
        }

    }

    //Component serialize 상속받아서 객체로 member를 전달할수 있음, 나중에 바꾸면 될듯
    private void addButtonAction() {

        String name = amdNameEditText.getText().toString();
        String bank = amdBankNameEditText.getText().toString();
        String account = amdBankAccountEditText.getText().toString();
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


    private void getComponentsId() {
        amdAddMemberText = (TextView) findViewById(R.id.amdAddMemberText);
        amdNameText = (TextView) findViewById(R.id.amdNameText);
        amdBankNameText = (TextView) findViewById(R.id.amdBankNameText);
        amdBankAccountText = (TextView) findViewById(R.id.amdBankAccountText);
        amdRemarkText = (TextView) findViewById(R.id.amdRemarkText);
        amdNameEditText = (EditText) findViewById(R.id.amdNameEditText);
        amdBankNameEditText = (EditText) findViewById(R.id.amdBankNameEditText);
        amdBankAccountEditText = (EditText) findViewById(R.id.amdBankAccountEditText);
        amdRemarkEditText = (EditText) findViewById(R.id.amdRemarkEditText);
        amdCancelButton = (Button) findViewById(R.id.amdCancelButton);
        amdAddButton = (Button) findViewById(R.id.amdAddButton);
    }

    private void setComponentsNames() {
        amdAddMemberText.setText(Config_Language.get().amdAddMemberText);
        amdNameText.setText(Config_Language.get().amdNameText);
        amdBankNameText.setText(Config_Language.get().amdBankNameText);
        amdBankAccountText.setText(Config_Language.get().amdBankAccountText);
        amdRemarkText.setText(Config_Language.get().amdRemarkText);
        amdNameEditText.setHint(Config_Language.get().amdNameEditText);
        amdBankNameEditText.setHint(Config_Language.get().amdBankNameEditText);
        amdBankAccountEditText.setHint(Config_Language.get().amdBankAccountEditText);
        amdRemarkEditText.setHint(Config_Language.get().amdRemarkEditText);
        amdCancelButton.setText(Config_Language.get().amdCancelButton);
        amdAddButton.setText(Config_Language.get().amdAddButton);
    }
}
