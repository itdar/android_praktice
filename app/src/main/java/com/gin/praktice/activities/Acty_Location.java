package com.gin.praktice.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gin.praktice.R;
import com.gin.praktice.adapter.LocationRecyclerAdapter;
import com.gin.praktice.component.DDay;
import com.gin.praktice.component.Location;
import com.gin.praktice.component.Member;
import com.gin.praktice.member.DayMembers;

import java.util.ArrayList;

public class Acty_Location extends Activity {
    private static final int ADD_MEMBER = 1;

    private Intent resultIntent;
    private Intent locationIntent;

    private DDay dDay;
    private Location location;

    private EditText storeNameEditText;
    private EditText moneyEditText;

    private RecyclerView locationMemberView;
    private LocationRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_location);

        dDay = DDay.getInstance();
        location = new Location();
//        locationMemberList = location.getList();

        resultIntent = new Intent(this, Acty_Result.class);
        locationIntent = new Intent(this, Acty_Location.class);

        storeNameEditText = findViewById(R.id.storeNameEditText);
        moneyEditText = findViewById(R.id.moneyEditText);

        locationMemberView = (RecyclerView) findViewById(R.id.locationMemberView);
        setRecyclerView();

        for (int i = 0; i < dDay.dayMembers.getLength(); ++i)
        {
            location.add(dDay.dayMembers.get(i));
        }
        adapter.notifyDataSetChanged();

        storeNameEditText.setOnFocusChangeListener(new TextView.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager imm = (InputMethodManager) storeNameEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(storeNameEditText.getWindowToken(), 0);
                }
            }
        });
        moneyEditText.setOnFocusChangeListener(new TextView.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager imm = (InputMethodManager) moneyEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(moneyEditText.getWindowToken(), 0);
                }
            }
        });
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
            case R.id.nextButton : nextButtonAction(); break;
            case R.id.deleteButton : deleteButtonAction(); break;
            case R.id.addMemberButton : addMemberButtonAction(); break;
            case R.id.cancelLateButton : cancelLateButtonAction(); break;
            case R.id.littleLateButton : littleLateButtonAction(); break;
            case R.id.superLateButton : superLateButtonAction(); break;
            case R.id.setManagerButton : setManagerButtonAction(); break;
            case R.id.addMoreLocationButton : addMoreLocationButtonAction(); break;
            default: break;
        }
    }

    private void setManagerButtonAction() {
        if (adapter.getSelectedList().size() > 0)
        {
            location.setManager(location.get(adapter.getSelectedList().get(0)));

            adapter.getSelectedList().clear();
            adapter.notifyDataSetChanged();
        }
    }

    private void cancelLateButtonAction() {
        if (adapter.getSelectedList().size() > 0)
        {
            ((Member)location.get(adapter.getSelectedList().get(0))).isOneSecond = false;
            ((Member)location.get(adapter.getSelectedList().get(0))).isOneThird = false;

            adapter.getSelectedList().clear();
            adapter.notifyDataSetChanged();
        } else {

        }
    }

    private void littleLateButtonAction() {
        if (adapter.getSelectedList().size() > 0)
        {
            ((Member)location.get(adapter.getSelectedList().get(0))).isOneThird = false;
            ((Member)location.get(adapter.getSelectedList().get(0))).isOneSecond = true;

            adapter.getSelectedList().clear();
            adapter.notifyDataSetChanged();
        } else {

        }
    }

    private void superLateButtonAction() {
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
            location.remove(adapter.getSelectedList().get(0));
            adapter.getSelectedList().clear();
        }
        adapter.notifyDataSetChanged();
    }

    // 중복추가 안되게 해야함
    //추가하는 intent 열때 아예 리스트에 있는 이름들은 같이 보내주고, 가서 중복되면 아예 안뜨도록..
    //일단 지금은 창 열려서 선택해서 확인하면 선택된 것들 중에 없는 것들만 추가됨 (addMember 함수)
    //나중에 바꾸면 될 듯
    private void addMemberButtonAction() {
        Intent intent = new Intent(this, Acty_AddMember2Location.class);

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
        // 중복추가 안되게 막아야함 -> 확인해야함
        Bundle bundle = intent.getExtras();
        ArrayList<String> nameList = bundle.getStringArrayList("nameList");

        for (int i = 0; i < nameList.size(); ++i)
        {
//            adapter.add(nameList.get(i));
            for (int j = 0; j < dDay.dayMembers.getLength(); ++j)
            {
                if (nameList.get(i).equals(dDay.dayMembers.get(j).getName())
                        && !location.contains(nameList.get(i)))
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
            if (!storeNameEditText.getText().toString().equals("") && !moneyEditText.getText().toString().equals(""))
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
        location.setName(storeNameEditText.getText().toString());
        location.setMoney(Integer.parseInt(moneyEditText.getText().toString()));

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
}
