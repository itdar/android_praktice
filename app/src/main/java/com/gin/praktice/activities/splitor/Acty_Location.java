package com.gin.praktice.activities.splitor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gin.praktice.R;
import com.gin.praktice.adapter.LocationRecyclerAdapter;
import com.gin.praktice.component.DDay;
import com.gin.praktice.component.Location;
import com.gin.praktice.component.Member;
import com.gin.praktice.config.lang.Config_Language;
import com.gin.praktice.member.DayMembers;

import java.util.ArrayList;

public class Acty_Location extends Activity {
    private static final int ADD_MEMBER = 1;

    private Intent resultIntent;
    private Intent locationIntent;

    private DDay dDay;
    private Location location;


    private RecyclerView locationMemberView;
    private LocationRecyclerAdapter adapter;

    private TextView locationVisitedText;
    private TextView locationStoreName;
    private EditText locationNameEditText;
    private TextView locationTotalMoney;
    private EditText locationMoneyEditText;
    private Button locationMemberDeleteButton;
    private Button locationAddMemberButton;
    private Button locationDetailOptionButton;
    private Button locationSetManagerButton;
    private Button addMoreLocationButton;
    private Button locationNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_location);

        dDay = DDay.getInstance();
        location = new Location();
//        locationMemberList = location.getList();

        resultIntent = new Intent(this, Acty_Result.class);
        locationIntent = new Intent(this, Acty_Location.class);

        locationNameEditText = findViewById(R.id.locationNameEditText);
        locationMoneyEditText = findViewById(R.id.locationMoneyEditText);

        locationMemberView = (RecyclerView) findViewById(R.id.locationMemberView);
        setRecyclerView();

        for (int i = 0; i < dDay.dayMembers.getLength(); ++i)
        {
            location.add(dDay.dayMembers.get(i));
        }
        adapter.notifyDataSetChanged();

        locationNameEditText.setOnFocusChangeListener(new TextView.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager imm = (InputMethodManager) locationNameEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(locationNameEditText.getWindowToken(), 0);
                }
            }
        });
        locationMoneyEditText.setOnFocusChangeListener(new TextView.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager imm = (InputMethodManager) locationMoneyEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(locationMoneyEditText.getWindowToken(), 0);
                }
            }
        });

        getComponentsId();
        setComponentsNames();
    }

    private void setRecyclerView() {
        locationMemberView.setHasFixedSize(true);

        adapter = new LocationRecyclerAdapter(location);
        locationMemberView.setAdapter(adapter);

        // 지그재그형의 그리드 형식
        //mainBinding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        // 그리드 형식
        //mainBinding.recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        // 가로 또는 세로 스크롤 목록 형식
        locationMemberView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.locationNextButton : nextButtonAction(); break;
            case R.id.locationMemberDeleteButton : deleteButtonAction(); break;
            case R.id.locationAddMemberButton : addMemberButtonAction(); break;
            case R.id.locationDetailOptionButton : detailOptionButtonAction(); break;
            case R.id.locationSetManagerButton : setManagerButtonAction(); break;
            case R.id.addMoreLocationButton : addMoreLocationButtonAction(); break;
            default: break;
        }
    }

    private void detailOptionButtonAction() {
        final CharSequence[] items = {
                Config_Language.get().littleLateMember,
                Config_Language.get().superLateMember,
                Config_Language.get().cancelLateMember };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);     // 여기서 this는 Activity의 this

        // 여기서 부터는 알림창의 속성 설정
        builder.setTitle("Add DDay Members")        // 제목 설정
                .setItems(items, new DialogInterface.OnClickListener() {
                    // 목록 클릭시 설정
                    public void onClick(DialogInterface dialog, int index) {
                        switch (index) {
                            case 0:littleLateAction(); break;
                            case 1:superLateAction(); break;
                            case 2:cancelLateAction(); break;
                            default:break;
                        }
                    }
                });
        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기
    }

    private void setManagerButtonAction() {
        if (adapter.getSelectedList().size() > 0)
        {
            location.setManager(location.get(adapter.getSelectedList().get(0)));

            adapter.getSelectedList().clear();
            adapter.notifyDataSetChanged();
        }
        else
        {
            Toast.makeText(this, "Select member first.", Toast.LENGTH_LONG).show();
        }
    }

    private void cancelLateAction() {
        if (adapter.getSelectedList().size() > 0)
        {
            ((Member)location.get(adapter.getSelectedList().get(0))).isOneSecond = false;
            ((Member)location.get(adapter.getSelectedList().get(0))).isOneThird = false;

            adapter.getSelectedList().clear();
            adapter.notifyDataSetChanged();
        }
        else
        {

        }
    }

    private void littleLateAction() {
        if (adapter.getSelectedList().size() > 0)
        {
            ((Member)location.get(adapter.getSelectedList().get(0))).isOneThird = false;
            ((Member)location.get(adapter.getSelectedList().get(0))).isOneSecond = true;

            adapter.getSelectedList().clear();
            adapter.notifyDataSetChanged();
        }
        else
        {

        }
    }

    private void superLateAction() {
        if (adapter.getSelectedList().size() > 0)
        {
            ((Member)location.get(adapter.getSelectedList().get(0))).isOneSecond = false;
            ((Member)location.get(adapter.getSelectedList().get(0))).isOneThird = true;

            adapter.getSelectedList().clear();
            adapter.notifyDataSetChanged();
        }
        else
        {

        }
    }

    private void deleteButtonAction() {
//        Toast.makeText(this, "DeleteButton. " + "\nadapter length : " + adapter.getSelectedList().size(), Toast.LENGTH_LONG).show();
        if (adapter.getSelectedList().size() > 0)
        {
            // If the deleting member is manager in this location,, check?
            if (location.getManager() != null &&
                    location.getManager().getName().equals(location.get(adapter.getSelectedList().get(0)).getName()))
            {
                location.setManager(null);
            }
            location.remove(adapter.getSelectedList().get(0));
            adapter.getSelectedList().clear();
        }
        adapter.notifyDataSetChanged();
    }

    private void addMemberButtonAction() {
        Intent intent = new Intent(this, Acty_AddMember2Location.class);

        ArrayList<String> existNameList = new ArrayList<String>();

        for (int i = 0; i < adapter.getItemCount(); ++i) {
            existNameList.add(adapter.getItems().get(i).getName());
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("existNameList", existNameList);

        intent.putExtras(bundle);

        startActivityForResult(intent, ADD_MEMBER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK)
        {
            switch (requestCode) {
                case ADD_MEMBER : addMember(intent); break;
//                case REQUEST_KAKAO : ; break;
            }
        }
    }

    // TODO Need to make request interface
    private void addMember(Intent intent) {
        Bundle bundle = intent.getExtras();
        ArrayList<String> nameList = bundle.getStringArrayList("nameList");

        for (int i = 0; i < nameList.size(); ++i)
        {
            for (int j = 0; j < dDay.dayMembers.getLength(); ++j)
            {
                if (nameList.get(i).equals(dDay.dayMembers.get(j).getName()))
                {
                    location.add(dDay.dayMembers.get(j));
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void addMoreLocationButtonAction() {
        if (checkLocationActy())
        {
            addLocation2DDay();

            startActivity(locationIntent);
            finish();
        }
    }

    private void nextButtonAction() {
        if (checkLocationActy())
        {
            addLocation2DDay();

            // 마지막 장소 추가 후 끝내면

            startActivity(resultIntent);
            finish();
        }
    }

    private boolean checkLocationActy() {
        boolean result = false;
        if (location.getLength() >= 1)
        {
            if (!locationNameEditText.getText().toString().equals("") && !locationMoneyEditText.getText().toString().equals(""))
            {
                if (location.getManager() != null)
                {
                    result = true;
                }
                else
                {
                    //이 가게에서 계산한 사람을 선택해주세요 토스트
                    Toast.makeText(this, "이 가게에서 계산한 멤버를 선택해주세요.", Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                //가게이름이랑 금액 토스트
                Toast.makeText(this, "들른 곳 이름과 금액을 입력해주세요.", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            //장소에 해당 멤버들 추가해야한다는 토스트
            Toast.makeText(this, "이 장소에 있었던 멤버들을 전부 추가해주세요.", Toast.LENGTH_LONG).show();
        }
        return result;
    }

    private void addLocation2DDay() {
        location.setName(locationNameEditText.getText().toString());
        location.setMoney(Integer.parseInt(locationMoneyEditText.getText().toString()));

        location.distribution();
        initLate();
        dDay.add(location);
    }

    private void initLate()
    {
        DayMembers dayMembers = dDay.dayMembers;
        for (int i = 0; i < dayMembers.getLength(); ++i)
        {
            ((Member)dayMembers.get(i)).isOneSecond = false;
            ((Member)dayMembers.get(i)).isOneThird = false;
            ((Member)dayMembers.get(i)).setManager(false);
        }
    }

    private void getComponentsId() {
        locationVisitedText = (TextView)findViewById(R.id.locationVisitedText);
        locationStoreName = (TextView)findViewById(R.id.locationStoreName);
        locationTotalMoney = (TextView)findViewById(R.id.locationTotalMoney);
        locationMemberDeleteButton = (Button)findViewById(R.id.locationMemberDeleteButton);
        locationAddMemberButton = (Button)findViewById(R.id.locationAddMemberButton);
        locationDetailOptionButton = (Button)findViewById(R.id.locationDetailOptionButton);
        locationSetManagerButton = (Button)findViewById(R.id.locationSetManagerButton);
        addMoreLocationButton = (Button)findViewById(R.id.addMoreLocationButton);
        locationNextButton = (Button)findViewById(R.id.locationNextButton);
    }

    private void setComponentsNames() {
        locationVisitedText.setText(Config_Language.get().locationVisitedText);
        locationStoreName.setText(Config_Language.get().locationStoreName);
        locationNameEditText.setHint(Config_Language.get().locationNameEditText);
        locationTotalMoney.setText(Config_Language.get().locationTotalMoney);
        locationMoneyEditText.setHint(Config_Language.get().locationMoneyEditText);
        locationMemberDeleteButton.setText(Config_Language.get().locationMemberDeleteButton);
        locationAddMemberButton.setText(Config_Language.get().locationAddMemberButton);
        locationDetailOptionButton.setText(Config_Language.get().locationDetailOptionButton);
        locationSetManagerButton.setText(Config_Language.get().locationSetManagerButton);
        addMoreLocationButton.setText(Config_Language.get().addMoreLocationButton);
        locationNextButton.setText(Config_Language.get().locationNextButton);
    }
}
