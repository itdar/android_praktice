package com.gin.praktice.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.gin.praktice.R;
import com.gin.praktice.composite.DDay;
import com.gin.praktice.composite.Member;

import java.util.ArrayList;

public class Acty_DDay extends Activity {

    private static final int REQUEST_CONTACT = 1;
    private static final int REQUEST_KAKAO = 2;

    private DDay dDay;

    private EditText nameEditText;
    private EditText dateEditText;

    private ListView dayMembersView;
    private ArrayList<String> dayMembersList = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    private Intent locationIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_dday);

        dDay = DDay.getInstance();

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        dateEditText = (EditText) findViewById(R.id.dateEditText);

        dayMembersView = (ListView) findViewById(R.id.dayMembersView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dayMembersList);
        dayMembersView.setAdapter(adapter);

        locationIntent = new Intent(this, Acty_Location.class);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contactAddButton : contactAddButtonAction(); break;
            case R.id.deleteButton : deleteButtonAction(); break;
            case R.id.nextButton : nextButtonAction(); break;
            default: break;
        }
    }

    private void contactAddButtonAction() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        startActivityForResult(intent, REQUEST_CONTACT);
    }

    private void openKakao() {
        Toast.makeText(this, "Open KakaoTalk friend list.", Toast.LENGTH_LONG).show();


    }

    private void deleteButtonAction() {
        Toast.makeText(this, "Delete Member button clicked.", Toast.LENGTH_LONG).show();
    }

    private void nextButtonAction() {
        Editable name = nameEditText.getText();
        Editable date = dateEditText.getText();
        // TODO 값 비어있는거 확인해서 넘어가야함
        if (!name.equals("") && !date.equals("") && dayMembersList.size() > 1) {
            dDay.setName(name.toString());
            dDay.setDate(date.toString());
            for (int i = 0; i < dayMembersList.size(); i++) {
                dDay.members.add(new Member(dayMembersList.get(i).toString()));
            }
            startActivity(locationIntent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CONTACT : requestContact(intent); break;
                case REQUEST_KAKAO : ; break;
            }
        }
    }

    // TODO Need to make request interface
    private void requestContact(Intent intent) {
        Cursor cursor = getContentResolver().query(intent.getData(),
                new String[] { ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER },
                null, null, null);
        cursor.moveToFirst();
        String receiveName = cursor.getString(0);
        String receivePhone = cursor.getString(1);
        cursor.close();

        Toast.makeText(this, "연락처 이름 : " + receiveName + "\n연락처 전화번호 : " + receivePhone, Toast.LENGTH_LONG).show();

        // TODO Need to check ramda below function
        addItems(receiveName);
    }

    public void addItems(String receiveName) {
        adapter.add(receiveName);
        adapter.notifyDataSetChanged();
    }


}
