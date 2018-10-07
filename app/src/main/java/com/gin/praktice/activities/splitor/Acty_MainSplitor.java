package com.gin.praktice.activities.splitor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gin.praktice.R;
import com.gin.praktice.adapter.ComponentRecyclerAdapter;
import com.gin.praktice.adapter.SquadRecyclerAdapter;
import com.gin.praktice.component.Component;
import com.gin.praktice.component.DDay;
import com.gin.praktice.component.Member;
import com.gin.praktice.component.Squad;
import com.gin.praktice.config.lang.Config_Language;
import com.gin.praktice.sqlite.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class Acty_MainSplitor extends Activity {
    private Intent dDayIntent;

    private SQLiteHelper sqLiteHelper;

    private RecyclerView memberListView;
    private List<Component> memberList;
    private ComponentRecyclerAdapter memberListAdapter;

    private RecyclerView squadListView;
    private List<Component> squadList;
    private SquadRecyclerAdapter squadListAdapter;

    private static final int ADD_NEW_SQUAD = 1;
    private static final int ADD_NEW_MEMBER= 2;

    private TextView splitorMainText;
    private TextView selectedMemberListText;
    private TextView wholeGroupListText;
    private Button splitorMainModifyButton;
    private Button splitorMainNewSquadButton;
    private Button splitorMainNewDDayButton;

    private String titleModification;
    private String titleDeleteConfirm;
    private String msgDeleteConfirm;
    private String toastSelectSquadFirst;
    private String toastSelectMemberFirst;
    private String toastAlreadyExistName;

    private String deleteSelectedSquad = Config_Language.get().deleteSelectedSquad;
    private String deleteSelectedMemberInSelectedSquad = Config_Language.get().deleteSelectedMemberInSelectedSquad;
    private String addMemberToSelectedSquad = Config_Language.get().addMemberToSelectedSquad;
//    private String modifySelectedMemberName = "선택멤버 이름 수정";   // 준비중
//    private String modifySelectedSquadName = "선택모임 이름 수정";    // 준비중


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_main_splitor);

        sqLiteHelper = new SQLiteHelper(this.getApplicationContext());

        dDayIntent = new Intent(this, Acty_DDay.class);

        setRecyclerView();

        loadSQLite();

        getComponentsId();
        setComponentsLang();
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

    private void insertInSquad(Cursor memberCursor) {
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

    private void setRecyclerView() {
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
            case R.id.splitorMainNewDDayButton : newDDayButtonAction(); break;
            case R.id.splitorMainNewSquadButton : newSquadButtonAction(); break;
            case R.id.splitorMainModifyButton : modifyButtonAction(); break;
            default: break;
        }
    }

    private void newDDayButtonAction() {

        if (memberListAdapter != null && memberListAdapter.getItemCount() > 0)
        {
            DDay dDay = DDay.getInstance();
            dDay.clear(); // clear all location
            dDay.dayMembers.setList(memberListAdapter.getItems()); // clear all ddayMember
        }
        startActivity(dDayIntent);
    }

    private void newSquadButtonAction() {

        Intent addNewGroupIntent = new Intent(this, Acty_AddNewSquad.class);

        startActivityForResult(addNewGroupIntent, ADD_NEW_SQUAD);
    }

    private void modifyButtonAction() {

        final CharSequence[] items = {
                Config_Language.get().addMemberToSelectedSquad,
                Config_Language.get().deleteSelectedMemberInSelectedSquad,
                Config_Language.get().deleteSelectedSquad};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);     // 여기서 this는 Activity의 this

        // 여기서 부터는 알림창의 속성 설정
        builder.setTitle(this.titleModification)        // 제목 설정
                .setItems(items, new DialogInterface.OnClickListener() {
                    // 목록 클릭시 설정
                    public void onClick(DialogInterface dialog, int index) {
                        switch (index)
                        {
                            case 0:addMemberButtonAction();break;
                            case 1:deleteMemberButtonAction();break;
                            case 2:deleteSquadButtonAction();break;
                            default:break;
                        }
                    }
                });
        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기
    }

    private void addMemberButtonAction() {
        if (squadListAdapter.getSelectedList().size() > 0)
        {
            Intent addNewMemberIntent = new Intent(this, Acty_AddNewMember.class);

            startActivityForResult(addNewMemberIntent, ADD_NEW_MEMBER);
        }
        else
        {
            Toast.makeText(this, this.toastSelectSquadFirst, Toast.LENGTH_LONG).show();
        }
    }

    private void deleteMemberButtonAction() {
        if (memberListAdapter.getItemCount() > 0 &&
                memberListAdapter.getSelectedList().size() > 0 && squadListAdapter.getSelectedList().size() > 0)
        {
            String memberName = ((Member)memberListAdapter.getItems().get(memberListAdapter.getSelectedList().get(0).intValue())).getName();
            String squadName = ((Squad)squadList.get(squadListAdapter.getSelectedList().get(0).intValue())).getName();

            sqLiteHelper.deleteMember(memberName, squadName);
//            squadListAdapter.getSelectedList()

            int memberIndex = memberListAdapter.getSelectedList().get(0).intValue();
            memberListAdapter.getItems().remove(memberIndex);
            memberListAdapter.notifyDataSetChanged();

            ((Squad)squadList.get(squadListAdapter.getSelectedList().get(0).intValue())).remove(memberIndex);
        }
        else
        {
            Toast.makeText(this, this.toastSelectMemberFirst, Toast.LENGTH_LONG).show();
        }
    }

    private void deleteSquadButtonAction() {
        if (squadListAdapter.getSelectedList().size() > 0) {
            new AlertDialog.Builder(this)
                    .setTitle(this.titleDeleteConfirm)
                    .setMessage(this.msgDeleteConfirm)
                    .setIcon(android.R.drawable.ic_delete)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            sqLiteHelper.deleteSquad((Squad)squadList.get(squadListAdapter.getSelectedList().get(0)));
                            squadList.remove(squadListAdapter.getSelectedList().get(0).intValue());

                            memberListAdapter.clearItems();
                            squadListAdapter.getSelectedList().clear();


                            memberListAdapter.notifyDataSetChanged();
                            squadListAdapter.notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    })
                    .show();
        }
        else
        {
            Toast.makeText(this, this.toastSelectSquadFirst, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK)
        {
            switch (requestCode) {
                case ADD_NEW_SQUAD : addNewSquad(intent); break;
                case ADD_NEW_MEMBER : addNewMember(intent); break;
            }
        }
    }

    private void addNewSquad(Intent intent) {
        Bundle bundle = intent.getExtras();

        Squad squad = (Squad)bundle.get("newSquad");
        squadList.add(squad);
        squadListAdapter.notifyDataSetChanged();

        sqLiteHelper.saveSquad(squad);

    }

    //멤버정보 추가/삭제 하고 아예 다시 조회해서 넣어주는걸로 해도 되는데 확인해야함(성능?) -> 현재는 해당 멤버나 스쿼드만 따로 넣어줌
    private void addData(String name, String phoneNumber) {

        Member member = new Member.Builder().name(name).phoneNumber(phoneNumber).build();
        sqLiteHelper.insertMember(((Squad)squadList.get(squadListAdapter.getSelectedList().get(0))).getName(), member);

        ((Squad)squadList.get(squadListAdapter.getSelectedList().get(0).intValue())).add(member);
        memberListAdapter.setItems(((Squad)squadList.get(squadListAdapter.getSelectedList().get(0).intValue())).getList());
        memberListAdapter.notifyDataSetChanged();
    }

    private void addNewMember(Intent intent) {
        Bundle bundle = intent.getExtras();

        String receiveName = bundle.getString("name");
        String receiveBank = bundle.getString("bank");
        String receiveAccount = bundle.getString("account");

        if (!isExist(receiveName))
        {
            addData(receiveName, "");
        }
        else
        {
            Toast.makeText(this, this.toastAlreadyExistName, Toast.LENGTH_LONG).show();
        }
    }

    //멤버추가창에서 가져온 멤버 이름이 select 되어있는 sqlite | squadList memberList 에서 있는 이름인지 확인해야함
    private boolean isExist(String receiveName) {
        List<Component> tempList = ((Squad)squadList.get(squadListAdapter.getSelectedList().get(0))).getList();
        for (int i = 0; i < tempList.size(); ++i)
        {
            if (receiveName.equals(tempList.get(i).getName()))
            {
                return true;
            }
        }
        return false;
    }

    private void getComponentsId() {
        splitorMainText = (TextView)findViewById(R.id.splitorMainText);
        wholeGroupListText = (TextView)findViewById(R.id.wholeGroupListText);
        selectedMemberListText = (TextView)findViewById(R.id.selectedMemberListText);
        splitorMainModifyButton = (Button)findViewById(R.id.splitorMainModifyButton);
        splitorMainNewSquadButton = (Button)findViewById(R.id.splitorMainNewSquadButton);
        splitorMainNewDDayButton = (Button)findViewById(R.id.splitorMainNewDDayButton);
    }

    private void setComponentsLang() {
        splitorMainText.setText(Config_Language.get().splitorMainText);
        wholeGroupListText.setText(Config_Language.get().wholeGroupListText);
        selectedMemberListText.setText(Config_Language.get().selectedMemberListText);
        splitorMainModifyButton.setText(Config_Language.get().splitorMainModifyButton);
        splitorMainNewSquadButton.setText(Config_Language.get().splitorMainNewSquadButton);
        splitorMainNewDDayButton.setText(Config_Language.get().splitorMainNewDDayButton);
        titleModification = Config_Language.get().titleMotification;
        titleDeleteConfirm = Config_Language.get().titleDeleteConfirm;
        msgDeleteConfirm = Config_Language.get().msgDeleteConfirm;
        toastSelectSquadFirst = Config_Language.get().toastSelectSquadFirst;
        toastSelectMemberFirst = Config_Language.get().toastSelectMemberFirst;
        toastAlreadyExistName = Config_Language.get().toastAlreadyExistName;
    }

}
