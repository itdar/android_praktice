package com.gin.praktice.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

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

        memberListView = (RecyclerView) findViewById(R.id.memberListView);
        memberList = new ArrayList<>();

        squadListView = (RecyclerView) findViewById(R.id.squadListView);
        squadList = new ArrayList<>();

        setRecyclerView();

        loadSQLite();


    }

    private void loadSQLite() {
        Cursor squadCursor = sqLiteHelper.getAllSquad();
        if (squadCursor.getCount() > 0)
        {
            squadCursor.moveToFirst();
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
                String name = memberCursor.getString(memberCursor.getColumnIndex("name"));;
                String bank = memberCursor.getString(memberCursor.getColumnIndex("bank"));;
                String accountNumber = memberCursor.getString(memberCursor.getColumnIndex("accountNumber"));;
                String phoneNumber = memberCursor.getString(memberCursor.getColumnIndex("phoneNumber"));;
                ((Squad)squadList.get(i)).add(new Member(name, bank, accountNumber, phoneNumber));
            }
        }
    }

    private void setRecyclerView() {
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
            case R.id.modifySquadButton : modifySquadButtonAction(); break;
            default: break;
        }
    }

    private void newSquadButtonAction() {
        Toast.makeText(this, "newSquadButtonAction", Toast.LENGTH_LONG).show();

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

    private void modifySquadButtonAction() {
//        memberList.add(new Member("newMember"));
//        memberListAdapter.notifyDataSetChanged();
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
