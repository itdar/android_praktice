package com.gin.praktice.activities.splitor;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gin.praktice.R;
import com.gin.praktice.adapter.ComponentRecyclerAdapter;
import com.gin.praktice.component.Component;
import com.gin.praktice.component.DDay;
import com.gin.praktice.component.Member;
import com.gin.praktice.config.lang.Config_Language;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Acty_DDay extends Activity {

    private static final int REQUEST_CONTACT = 1;
    private static final int REQUEST_ADD_MEMBER = 2;
    private static final int REQUEST_KAKAO = 3;


    private DDay dDay;
    private Calendar myCalendar;

    private RecyclerView dayMembersView;
    private List<Component> dayMembersList;

    private ComponentRecyclerAdapter adapter;

    private String ddayTitleAddDDayMember;
    private String ddayTitleDDayModification;
    private String ddaySelectMemberFirst;
    private String ddayAddAllMemberFirst;
    private String ddayFillTitleOrDateFirst;
    private String ddayAlreadyExistName;
    private String ddayContactName;
    private String ddayContactNumber;
    private String ddayReceivedName;
    private String ddayReceivedBank;
    private String ddayReceiveAccount;

    private TextView ddayText;
    private TextView ddayNameText;
    private EditText ddayNameEditText;
    private TextView ddayDate;
    private EditText dateEditText;
    private Button ddayModifyButton;
    private Button ddayAddMemberButton;
    private Button ddayNextButton;

    private Intent locationIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_dday);

        dDay = DDay.getInstance();
        dayMembersList = dDay.dayMembers.getList();
        ddayNameEditText = (EditText) findViewById(R.id.ddayNameEditText);
        dateEditText = (EditText) findViewById(R.id.dateEditText);

        dayMembersView = (RecyclerView) findViewById(R.id.dayMembersView);
        setRecyclerView();

        locationIntent = new Intent(this, Acty_Location.class);

        ddayNameEditText.setOnFocusChangeListener(new TextView.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager imm = (InputMethodManager) ddayNameEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(ddayNameEditText.getWindowToken(), 0);
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
        final DatePickerDialog.OnDateSetListener datePicker = new DatePickerDialog.OnDateSetListener() {

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
                new DatePickerDialog(Acty_DDay.this, datePicker, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        dateEditText.setText(dateFormat.format(date));

        getComponentsId();
        setComponentsLang();
    }

    private void updateDate() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

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
            case R.id.ddayAddMemberButton : addMemberButtonAction(); break;
            case R.id.ddayModifyButton : modifyButtonAction(); break;
            case R.id.ddayNextButton : nextButtonAction(); break;
            default: break;
        }
    }

    private void addContactMember() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        startActivityForResult(intent, REQUEST_CONTACT);
    }

    private void addRawMember() {
        Intent intent = new Intent(this, Acty_AddNewMember.class);
        startActivityForResult(intent, REQUEST_ADD_MEMBER);
    }

    private void addMemberButtonAction() {
        final CharSequence[] items = {
                Config_Language.get().addRawMemberDirectly,
                Config_Language.get().addContactMemberToDDay,
                Config_Language.get().addKakaoFriend };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);     // 여기서 this는 Activity의 this

        // 여기서 부터는 알림창의 속성 설정
        builder.setTitle(this.ddayTitleAddDDayMember)        // 제목 설정
                .setItems(items, new DialogInterface.OnClickListener() {
                    // 목록 클릭시 설정
                    public void onClick(DialogInterface dialog, int index) {
                        switch (index)
                        {
                            case 0: addRawMember(); break;
                            case 1: addContactMember(); break;
                            case 2: addKakaoFriend(); break;
                            default: break;
                        }
                    }
                });
        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기
    }

    private void modifyButtonAction() {
        final CharSequence[] items = {
                Config_Language.get().deleteSelectedMemberFromDDay };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);     // 여기서 this는 Activity의 this

        // 여기서 부터는 알림창의 속성 설정
        builder.setTitle(this.ddayTitleDDayModification)        // 제목 설정
                .setItems(items, new DialogInterface.OnClickListener() {
                    // 목록 클릭시 설정
                    public void onClick(DialogInterface dialog, int index) {
                        switch (index) {
                            case 0: deleteMember(); break;
                            default: break;
                        }
                    }
                });
        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기
    }

    private void addKakaoFriend() {
//        Toast.makeText(this, "Open KakaoTalk friend list.", Toast.LENGTH_LONG).show();

    }

    private void deleteMember() {

        if (adapter.getSelectedList().size() > 0) {
            dDay.dayMembers.remove(adapter.getSelectedList().get(0).intValue());
            adapter.getSelectedList().clear();
        }
        else
        {
            Toast.makeText(this, this.ddaySelectMemberFirst, Toast.LENGTH_LONG).show();
        }
        adapter.notifyDataSetChanged();
    }

    private void nextButtonAction() {
        Editable name = ddayNameEditText.getText();
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
                Toast.makeText(this, this.ddayAddAllMemberFirst, Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(this, this.ddayFillTitleOrDateFirst, Toast.LENGTH_LONG).show();
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

        Toast.makeText(this, this.ddayContactName + " : " + receiveName + "\n" + this.ddayContactNumber + " : " + receivePhone, Toast.LENGTH_LONG).show();

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
            Toast.makeText(this, this.ddayReceivedName+ " : " + receiveName +
                    "\n" + this.ddayReceivedBank + " : " + receiveBank +
                    "\n" + this.ddayReceiveAccount + " : " + receiveAccount, Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, this.ddayAlreadyExistName, Toast.LENGTH_LONG).show();
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

    private void getComponentsId() {
        ddayText = (TextView)findViewById(R.id.ddayText);
        ddayNameText = (TextView)findViewById(R.id.ddayNameText);
        ddayDate = (TextView)findViewById(R.id.ddayDate);
        ddayModifyButton = (Button)findViewById(R.id.ddayModifyButton);
        ddayAddMemberButton = (Button)findViewById(R.id.ddayAddMemberButton);
        ddayNextButton = (Button)findViewById(R.id.ddayNextButton);
    }

    private void setComponentsLang() {
        ddayText.setText(Config_Language.get().ddayText);
        ddayNameText.setText(Config_Language.get().ddayNameText);
        ddayNameEditText.setHint(Config_Language.get().ddayNameEditText);
        ddayDate.setText(Config_Language.get().ddayDate);
        ddayModifyButton.setText(Config_Language.get().ddayModifyButton);
        ddayAddMemberButton.setText(Config_Language.get().ddayAddMemberButton);
        ddayNextButton.setText(Config_Language.get().ddayNextButton);
        ddayTitleAddDDayMember = Config_Language.get().ddayTitleAddDDayMember;
        ddayTitleDDayModification = Config_Language.get().ddayTitleDDayModification;
        ddaySelectMemberFirst = Config_Language.get().toastDDaySelectMemberFirst;
        ddayAddAllMemberFirst = Config_Language.get().toastDDayAddAllMemberFirst;
        ddayFillTitleOrDateFirst = Config_Language.get().toastDDayFillTitleOrDateFirst;
        ddayAlreadyExistName = Config_Language.get().toastDDayAlreadyExistName;
        ddayContactName = Config_Language.get().ddayContactName;
        ddayContactNumber = Config_Language.get().ddayContactNumber;
        ddayReceivedName = Config_Language.get().ddayReceivedName;
        ddayReceivedBank = Config_Language.get().ddayReceivedBank;
        ddayReceiveAccount = Config_Language.get().ddayReceiveAccount;

    }


}
