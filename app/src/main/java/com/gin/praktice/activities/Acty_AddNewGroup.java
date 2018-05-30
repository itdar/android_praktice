package com.gin.praktice.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gin.praktice.R;
import com.gin.praktice.adapter.ComponentRecyclerAdapter;
import com.gin.praktice.component.Component;
import com.gin.praktice.component.Member;

import java.util.ArrayList;
import java.util.List;

public class Acty_AddNewGroup extends Activity {
    private EditText squadNameEditText;

    private static final int REQUEST_CONTACT = 1;
    private static final int REQUEST_ADD_MEMBER = 2;

    private RecyclerView newGroupMembersView;
    private List<Component> newGroupMembersList;

    private ComponentRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_addnewgroup);

        newGroupMembersList = new ArrayList<>();

        squadNameEditText = (EditText) findViewById(R.id.squadNameEditText);

        newGroupMembersView = (RecyclerView) findViewById(R.id.newGroupMembersView);
        setRecyclerView();

    }

    private void setRecyclerView() {
        newGroupMembersView.setHasFixedSize(true);

        adapter = new ComponentRecyclerAdapter(newGroupMembersList);
        newGroupMembersView.setAdapter(adapter);

        newGroupMembersView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contactAddButton : contactAddButtonAction(); break;
            case R.id.addMemberButton : addMemberButtonAction(); break;
            case R.id.deleteButton : deleteButtonAction(); break;
            case R.id.newSquadButton : newSquadButtonAction(); break;
            default: break;
        }
    }

    private void contactAddButtonAction() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        startActivityForResult(intent, REQUEST_CONTACT);
    }

    private void addMemberButtonAction() {
        Intent intent = new Intent(this, Acty_AddNewMember.class);
        startActivityForResult(intent, REQUEST_ADD_MEMBER);
    }

    private void deleteButtonAction() {

        Toast.makeText(this, "Delete Member button clicked. ", Toast.LENGTH_LONG).show();
        if (adapter.getSelectedList().size() > 0) {
            newGroupMembersList.remove(adapter.getSelectedList().get(0).intValue());
            adapter.getSelectedList().clear();
        }
        adapter.notifyDataSetChanged();
    }

    private void newSquadButtonAction() {
        Editable name = squadNameEditText.getText();
//        // TODO 값 비어있는거 확인해서 넘어가야함
//        if (!name.equals("") && !date.equals("") && dayMembersList.size() > 1) {
//            dDay.setName(name.toString());
//            dDay.setDate(date.toString());
////            for (int i = 0; i < dayMembersList.size(); i++) {
////                dDay.dayMembers.add(new Member(dayMembersList.get(i).getName(), dayMembersList.get(i).getPhoneNumber()));
////            }
//            startActivity(locationIntent);
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CONTACT : requestContact(intent); break;
                case REQUEST_ADD_MEMBER : addMember(intent); break;
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

        // Need to check ramda below function

        addData(receiveName, receivePhone);
    }

    private void addData(String receiveName, String receivePhone) {

        newGroupMembersList.add(new Member(receiveName, receivePhone));
        Toast.makeText(this, Integer.toString(newGroupMembersList.size()), Toast.LENGTH_LONG).show();

        adapter.notifyDataSetChanged();
    }

    private void addMember(Intent intent) {
        Bundle bundle = intent.getExtras();

        String receiveName = bundle.getString("name");
        String receiveBank = bundle.getString("bank");
        String receiveAccount = bundle.getString("account");

        addData(receiveName, "");
        Toast.makeText(this, "추가된 이름 : " + receiveName +
                "\n추가된 은행 : " + receiveBank +
                "\n추가된 계좌 : " + receiveAccount, Toast.LENGTH_LONG).show();
    }
}
