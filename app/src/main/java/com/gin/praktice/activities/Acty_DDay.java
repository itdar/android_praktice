package com.gin.praktice.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;

import com.gin.praktice.R;

public class Acty_DDay extends Activity {
    private static final int PICK_CONTACT_REQUEST = 1;

    private static final int PICK_CONTACT = 0;

    private Intent locationIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_dday);

        locationIntent = new Intent(this, Acty_Location.class);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addFromPhoneButton : openPhoneBook(); break;
            case R.id.addFromKKOButton : /*startActivity(locationIntent)*/; break;
            case R.id.nextButton : startActivity(locationIntent); break;
            default: break;
        }
    }

    private void openPhoneBook() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == PICK_CONTACT_REQUEST) {
            Cursor cursor = getContentResolver().query(data.getData(),
                    new String[] { ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER },
                    null, null, null);

            cursor.moveToFirst();
            //이름획득
            String receiveName = cursor.getString(0);
            //전화번호 획득
            String receivePhone = cursor.getString(1);
            cursor.close();

            Toast.makeText(this, "연락처 이름 : " + receiveName + "\n연락처 전화번호 : " + receivePhone, Toast.LENGTH_LONG).show();
        }
    }
}
