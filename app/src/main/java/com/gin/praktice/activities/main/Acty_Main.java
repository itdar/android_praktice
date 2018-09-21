package com.gin.praktice.activities.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gin.praktice.R;
import com.gin.praktice.activities.splitor.Acty_MainSplitor;
import com.gin.praktice.config.Config_Kor;

public class Acty_Main extends Activity {

    Intent mainSplitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_main);

        mainSplitor = new Intent(this, Acty_MainSplitor.class);

    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mainSplitorButton : mainSplitorButtonAction(); break;
            case R.id.languageButton : languageButtonAction(); break;
            default: break;
        }
    }

    private void languageButtonAction() {

        final CharSequence[] items = { Config_Kor.languageKor,
                                    Config_Kor.languageEng,
                                    Config_Kor.languageFrn,
                                    Config_Kor.languageGer,
                                    Config_Kor.languageChn,
                                    Config_Kor.languageJpn};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);     // 여기서 this는 Activity의 this

        // 여기서 부터는 알림창의 속성 설정
        builder.setTitle("Modification")        // 제목 설정
                .setItems(items, new DialogInterface.OnClickListener() {
                    // 목록 클릭시 설정
                    public void onClick(DialogInterface dialog, int index) {
                        switch (index) {
                            case 0:
//                                addMemberButtonAction();
                                break;
                            case 1:
//                                deleteMemberButtonAction();
                                break;
                            case 2:
//                                deleteSquadButtonAction();
                                break;
                            default:
                                break;
                        }
                    }
                });
        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기
    }

    private void mainSplitorButtonAction() {

        startActivity(mainSplitor);
        finish();
    }


}
