package com.gin.praktice.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.gin.praktice.R;
import com.gin.praktice.component.Squad;

public class Acty_Main extends Activity {
    private Intent dDayIntent;

    private RecyclerView memberListView;
    private RecyclerView squadListView;

    private static final int ADD_NEW_SQUAD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_main);

        dDayIntent = new Intent(this, Acty_DDay.class);
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

        Intent addNewGroupIntent = new Intent(this, Acty_AddNewGroup.class);

        startActivityForResult(addNewGroupIntent, ADD_NEW_SQUAD);
    }

    private void newDDayButtonAction() {
        Toast.makeText(this, "newDDayButtonAction", Toast.LENGTH_LONG).show();

        startActivity(dDayIntent);
    }

    private void deleteSquadButtonAction() {

    }

    private void modifySquadButtonAction() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ADD_NEW_SQUAD : addNewSquad(intent); break;
                case 0 : ; break;
            }
        }
    }

    private void addNewSquad(Intent intent) {
        Bundle bundle = intent.getExtras();

        Squad squad = (Squad)bundle.get("newSquad");

        Toast.makeText(this, squad.getName(), Toast.LENGTH_LONG).show();
    }

}
