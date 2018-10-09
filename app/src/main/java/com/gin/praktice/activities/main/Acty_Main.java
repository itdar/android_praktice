package com.gin.praktice.activities.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gin.praktice.R;
import com.gin.praktice.activities.splitor.Acty_MainSplitor;
import com.gin.praktice.config.lang.Config_Eng;
import com.gin.praktice.config.lang.Config_Kor;
import com.gin.praktice.config.lang.Config_Language;

public class Acty_Main extends Activity {

    Intent mainSplitor;

    TextView mainText;
    Button mainSplitorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_main);

        mainSplitor = new Intent(this, Acty_MainSplitor.class);

        mainText = (TextView)findViewById(R.id.mainText);
        mainSplitorButton = (Button)findViewById(R.id.mainSplitorButton); // R.id.bt  여기는 xml에 지정한 버튼id로해주세용

        setComponentsNames();
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mainSplitorButton : mainSplitorButtonAction(); break;
            case R.id.languageButton : languageButtonAction(); break;
            default: break;
        }
    }

    private void languageButtonAction() {

        final CharSequence[] items = {
                Config_Language.get().languageKor,
                Config_Language.get().languageEng };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);     // 여기서 this는 Activity의 this

        // 여기서 부터는 알림창의 속성 설정
        builder.setTitle("Language Pack")        // 제목 설정
                .setItems(items, new DialogInterface.OnClickListener() {
                    // 목록 클릭시 설정
                    public void onClick(DialogInterface dialog, int index) {
                        switch (index) {
                            case 0: setLanguage(0); break;
                            case 1: setLanguage(1); break;
//                            case 2: deleteSquadButtonAction();
//                                break;
                            default: break;
                        }
                        // Change UI components name after setting language pack
                        setComponentsNames();
                    }
                });
        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기
    }

    private void setLanguage(int langFlag) {
        switch (langFlag) {
            case 0: Config_Language.get().setLanguagePack(new Config_Kor()); break;
            case 1: Config_Language.get().setLanguagePack(new Config_Eng()); break;

            default: break;
        }
    }

    private void setComponentsNames() {
        mainText.setText(Config_Language.get().mainText);
        mainSplitorButton.setText(Config_Language.get().mainSplitorButton);

    }

    private void mainSplitorButtonAction() {

        startActivity(mainSplitor);
//        finish();

    }


}
