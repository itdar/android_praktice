package com.gin.praktice.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gin.praktice.R;
import com.gin.praktice.adapter.ComponentRecyclerAdapter;
import com.gin.praktice.adapter.SquadRecyclerAdapter;
import com.gin.praktice.component.Component;
import com.gin.praktice.component.DDay;
import com.gin.praktice.component.Member;
import com.gin.praktice.component.Squad;
import com.gin.praktice.sqlite.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class Acty_Main extends Activity {
    private Intent dDayIntent;

    private SQLiteHelper sqLiteHelper;

    private RecyclerView memberListView;
    private List<Component> memberList;
    private ComponentRecyclerAdapter memberListAdapter;

    private RecyclerView squadListView;
    private List<Component> squadList;
    private SquadRecyclerAdapter squadListAdapter;

    private static final int ADD_NEW_SQUAD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_main);

        sqLiteHelper = new SQLiteHelper(this.getApplicationContext());

        dDayIntent = new Intent(this, Acty_DDay.class);

        setRecyclerView();

        loadSQLite();
    }

    private void loadSQLite() {
        Cursor squadCursor = sqLiteHelper.getAllSquad();
        if (squadCursor.getCount() > 0)
        {
            squadCursor.moveToFirst();
//            squadList.clear();
            do {
                String name = squadCursor.getString(squadCursor.getColumnIndex("name"));
                squadList.add(new Squad(name));
            } while (squadCursor.moveToNext());
            squadCursor.close();
            squadListAdapter.notifyDataSetChanged();
        }

        Cursor memberCursor = sqLiteHelper.getAllMembers();
        if (memberCursor.getCount() > 0)
        {
            memberCursor.moveToFirst();
            do {
                insertInSquad(memberCursor);
            } while (memberCursor.moveToNext());
            memberCursor.close();
        }
    }

    private void insertInSquad(Cursor memberCursor)
    {
        String squadName = memberCursor.getString(memberCursor.getColumnIndex("squadName"));
        for (int i = 0; i < squadList.size(); ++i)
        {
            if (squadList.get(i).getName().equals(squadName))
            {
                String name = memberCursor.getString(memberCursor.getColumnIndex("name"));
                String bank = memberCursor.getString(memberCursor.getColumnIndex("bank"));
                String accountNumber = memberCursor.getString(memberCursor.getColumnIndex("accountNumber"));
                String phoneNumber = memberCursor.getString(memberCursor.getColumnIndex("phoneNumber"));
                ((Squad)squadList.get(i)).add(
                        new Member.Builder().name(name).bank(bank).accountNumber(accountNumber).phoneNumber(phoneNumber).build());
            }
        }
    }

    private void setRecyclerView()
    {
        memberListView = findViewById(R.id.memberListView);
        memberList = new ArrayList<>();

        squadListView = findViewById(R.id.squadListView);
        squadList = new ArrayList<>();

        memberListView.setHasFixedSize(true);
        squadListView.setHasFixedSize(true);

        memberListAdapter = new ComponentRecyclerAdapter(memberList);
        squadListAdapter = new SquadRecyclerAdapter(squadList, memberList, memberListAdapter);

        memberListView.setAdapter(memberListAdapter);
        squadListView.setAdapter(squadListAdapter);

        memberListView.setLayoutManager(new LinearLayoutManager(this));
        squadListView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.newDDayButton : newDDayButtonAction(); break;
            case R.id.newSquadButton : newSquadButtonAction(); break;
            case R.id.deleteSquadButton : deleteSquadButtonAction(); break;
            case R.id.addMemberButton : addMemberButtonAction(); break;
            case R.id.deleteMemberButton : deleteMemberButtonAction(); break;
            default: break;
        }
    }

    // 버튼 누르면 멤버추가 창 띄워서 돌아오면서 sqlite에 저장해주고 squad랑 member 리스트에 넣어줘야함
    //-> 2018.07.10
    private void addMemberButtonAction() {

    }

    private void deleteMemberButtonAction() {
        if (memberListAdapter.getItemCount() > 0 &&
                memberListAdapter.getSelectedList().size() > 0 && squadListAdapter.getSelectedList().size() > 0)
        {
            String memberName = ((Member)memberListAdapter.getItems().get(memberListAdapter.getSelectedList().get(0).intValue())).getName();
            String squadName = ((Squad)squadList.get(squadListAdapter.getSelectedList().get(0).intValue())).getName();

            sqLiteHelper.deleteMember(memberName, squadName);

            // 여기서 지워주는거 넣어줘야 DB에서는 지워졌는데 squad에 속한 member로 메모리에 남아있는거 (시작시에 띄워준거)
            //까지 지워줄 수 있음 -> 2018.07.10
//            squadListAdapter.getSelectedList()
            memberListAdapter.getItems().remove(memberListAdapter.getSelectedList().get(0).intValue());
            memberListAdapter.notifyDataSetChanged();


        }
    }

    private void newSquadButtonAction() {
//        Toast.makeText(this, "newSquadButtonAction", Toast.LENGTH_LONG).show();

        Intent addNewGroupIntent = new Intent(this, Acty_AddNewSquad.class);

        startActivityForResult(addNewGroupIntent, ADD_NEW_SQUAD);
    }

    private void newDDayButtonAction() {

        if (memberListAdapter != null && memberListAdapter.getItemCount() > 0)
        {
//            Toast.makeText(this, "newDDayButtonAction", Toast.LENGTH_LONG).show();
            DDay dDay = DDay.getInstance();
            dDay.dayMembers.setList(memberListAdapter.getItems());
        }

        startActivity(dDayIntent);
    }

    private void deleteSquadButtonAction() {
        if (squadListAdapter.getSelectedList().size() > 0)
        {
            sqLiteHelper.deleteSquad((Squad)squadList.get(squadListAdapter.getSelectedList().get(0)));
            squadList.remove(squadListAdapter.getSelectedList().get(0).intValue());

            memberListAdapter.clearItems();
            squadListAdapter.getSelectedList().clear();
        }
        memberListAdapter.notifyDataSetChanged();
        squadListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK)
        {
            switch (requestCode) {
                case ADD_NEW_SQUAD : addNewSquad(intent); break;
                case 0 : ; break;
            }
        }
    }

    private void addNewSquad(Intent intent) {
        Bundle bundle = intent.getExtras();

        Squad squad = (Squad)bundle.get("newSquad");
        squadList.add(squad);
        squadListAdapter.notifyDataSetChanged();

        sqLiteHelper.saveSquad(squad);

//        Toast.makeText(this, "squad name >> " + squad.getName(), Toast.LENGTH_LONG).show();
    }

}
