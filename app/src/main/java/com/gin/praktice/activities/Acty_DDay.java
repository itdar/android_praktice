package com.gin.praktice.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gin.praktice.R;
import com.gin.praktice.adapter.ComponentRecyclerAdapter;
import com.gin.praktice.component.Component;
import com.gin.praktice.component.DDay;
import com.gin.praktice.component.Member;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Acty_DDay extends Activity {

    private static final int REQUEST_CONTACT = 1;
    private static final int REQUEST_ADD_MEMBER = 2;
    private static final int REQUEST_KAKAO = 3;


    private DDay dDay;
    private Calendar myCalendar;

    private EditText nameEditText;
    private EditText dateEditText;

    private RecyclerView dayMembersView;
    private List<Component> dayMembersList;

    private ComponentRecyclerAdapter adapter;


    private Intent locationIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_dday);

        dDay = DDay.getInstance();
        dayMembersList = dDay.dayMembers.getList();
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        dateEditText = (EditText) findViewById(R.id.dateEditText);

        dayMembersView = (RecyclerView) findViewById(R.id.dayMembersView);
        setRecyclerView();

        locationIntent = new Intent(this, Acty_Location.class);

        nameEditText.setOnFocusChangeListener(new TextView.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager imm = (InputMethodManager) nameEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(nameEditText.getWindowToken(), 0);
                }
            }
        });
        dateEditText.setOnFocusChangeListener(new TextView.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager imm = (InputMethodManager) dateEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(dateEditText.getWindowToken(), 0);
                }
            }
        });

        myCalendar = Calendar.getInstance();

        EditText edittext= (EditText) findViewById(R.id.dateEditText);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();
            }

        };

        edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Acty_DDay.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    private void updateDate() {
        String myFormat = "yyyy-dd-MM"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateEditText.setText(sdf.format(myCalendar.getTime()));
    }


    private void setRecyclerView() {
        dayMembersView.setHasFixedSize(true);

        adapter = new ComponentRecyclerAdapter(dayMembersList);
        dayMembersView.setAdapter(adapter);

                // 지그재그형의 그리드 형식
                //mainBinding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                // 그리드 형식
                //mainBinding.recyclerView.setLayoutManager(new GridLayoutManager(this,4));
                // 가로 또는 세로 스크롤 목록 형식
        dayMembersView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void addData(String receiveName, String receivePhone) {
//        dayMembersList.add
        dDay.dayMembers.add(new Member.Builder().name(receiveName).phoneNumber(receivePhone).build());

        adapter.notifyDataSetChanged();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contactAddButton : contactAddButtonAction(); break;
            case R.id.addMemberButton : addMemberButtonAction(); break;
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

    private void addMemberButtonAction() {
        Intent intent = new Intent(this, Acty_AddNewMember.class);
        startActivityForResult(intent, REQUEST_ADD_MEMBER);
    }

    private void openKakao() {
        Toast.makeText(this, "Open KakaoTalk friend list.", Toast.LENGTH_LONG).show();

    }

    private void deleteButtonAction() {
//        Toast.makeText(this, "Delete Member button clicked. ", Toast.LENGTH_LONG).show();

        if (adapter.getSelectedList().size() > 0) {
            dDay.dayMembers.remove(adapter.getSelectedList().get(0).intValue());
            adapter.getSelectedList().clear();
        }

        adapter.notifyDataSetChanged();
    }

    private void nextButtonAction() {
        Editable name = nameEditText.getText();
        Editable date = dateEditText.getText();

        if (!name.equals("") && !date.equals(""))
        {
            if (dayMembersList.size() > 1)
            {
                dDay.setName(name.toString());
                dDay.setDate(date.toString());

                startActivity(locationIntent);
                finish();
            }
            else
            {
                Toast.makeText(this, "모임날의 이름과 날짜를 채워주세요.", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(this, "모임날 참석한 모든 멤버를 추가해주세요.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CONTACT : requestContact(intent); break;
                case REQUEST_ADD_MEMBER : addMember(intent); break;
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

        // Need to check ramda below function

        addData(receiveName, receivePhone);
    }

    private void addMember(Intent intent) {
        Bundle bundle = intent.getExtras();

        String receiveName = bundle.getString("name");
        String receiveBank = bundle.getString("bank");
        String receiveAccount = bundle.getString("account");

        if (!isExist(receiveName))
        {
            addData(receiveName, "");
            Toast.makeText(this, "추가된 이름 : " + receiveName +
                    "\n추가된 은행 : " + receiveBank +
                    "\n추가된 계좌 : " + receiveAccount, Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "이미 중복된 이름이 있습니다.", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isExist(String receiveName) {
        for (int i = 0; i < dDay.dayMembers.getLength(); ++i)
        {
            if (dDay.dayMembers.get(i).getName().equals(receiveName))
            {
                return true;
            }
        }
        return false;
    }

//    public void addItems(String receiveName) {
//        adapter.add(receiveName);
//        adapter.notifyDataSetChanged();
//    }


}
