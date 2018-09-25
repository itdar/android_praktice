package com.gin.praktice.activities.splitor;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gin.praktice.R;
import com.gin.praktice.component.DDay;
import com.gin.praktice.component.Member;
import com.gin.praktice.config.lang.Config_Language;
import com.gin.praktice.dynamic.result.MoneyMatrix;
import com.gin.praktice.visitor.Visitor;
import com.gin.praktice.visitor.Visitor_AppendEditText;

public class Acty_Result extends Activity {
    private DDay dDay;

    private TextView resultOfCalc;
    private TextView resultTextView1;
    private TextView resultTextView2;
    private Button copyResultButton1;
    private Button copyResultButton2;
    private Button resultSaveButton;
    private Button resultDoneButton;


    // activity 정리하고 꾸며야함
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_result);

        dDay = DDay.getInstance();
        resultTextView1 = (TextView) findViewById(R.id.resultTextView1);
        resultTextView2 = (TextView) findViewById(R.id.resultTextView2);

        Visitor editTextVisitor = new Visitor_AppendEditText();
        dDay.accept(editTextVisitor, resultTextView1);
        dDay.dayMembers.accept(editTextVisitor, resultTextView1);

        //만들때 visitor? 새로 만들어야함
        tempManagerFunction();

        getComponentsId();
        setComponentsNames();

    }

    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.resultDoneButton : doneButtonClicked(); break;
            case R.id.resultSaveButton : saveButtonClicked(); break;
            case R.id.copyResultButton1 : copyResultButtonClicked(); break;
            case R.id.copyResultButton2 : copyResultButton2Clicked(); break;
            default: break;
        }
    }

    private void copyResultButtonClicked() {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB){
            android.content.ClipboardManager clipboard =  (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("resultCopy1", resultTextView1.getText());
            clipboard.setPrimaryClip(clip);
        } else{
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
            clipboard.setText(resultTextView1.getText());
        }
        Toast.makeText(getApplicationContext(), "정산 세부내역을 복사", Toast.LENGTH_SHORT).show();
    }

    private void doneButtonClicked() {
        // 메인화면으로 돌아간다는 메시지창 확인/취소

        dDay.getList().clear();
        dDay.dayMembers.getList().clear();

        finish();
    }

    private void saveButtonClicked() {
        //준비중

    }

    private void copyResultButton2Clicked() {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB){
            android.content.ClipboardManager clipboard =  (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("resultCopy2", resultTextView2.getText());
            clipboard.setPrimaryClip(clip);
        } else{
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
            clipboard.setText(resultTextView2.getText());
        }
        Toast.makeText(getApplicationContext(), "멤버별 부칠 내역을 복사", Toast.LENGTH_SHORT).show();
    }

    /**
     * 2018.08.20
     * naming, function fix
     *
     * 1. 전체 멤버 시작부터 끝까지 반복한다.
     *  1.1. 멤버의 매니저맵의 시작부터 끝까지 반복한다.
     *   1.1.1. 전체 멤버 시작부터 끝까지 반복한다.
     *    1.1.1.1. 매니저를 전체 멤버에서 찾는다.
     *     1.1.1.1.1. 찾은 멤버의 매니저 맵에 처음 (i) 멤버가 있는지 확인해서
     *      1.1.1.1.1.1. 있으면 -
     *      1.1.1.1.1.2. 없으면 -
     * 2. 맞게 출력한다.
     * 3. 끝낸다.
     */
    /**
     * Dynamic matrix
     */
    private void tempManagerFunction() {
        Member managerMember;
        String memberName;

        resultTextView2.append("\n");

        int dayMembersLength = dDay.dayMembers.getLength();
        int[][] resultMoneyMat = MoneyMatrix.INSTANCE.resultMoneyMat;

        for (int i = 0; i < dayMembersLength; ++i)
        {
            memberName = dDay.dayMembers.get(i).getName();
            resultTextView2.append("\n" + memberName + Config_Language.get().resultOfThisMember);
            for (int j = 0; j < dayMembersLength; ++j)
            {
                if (i != j && resultMoneyMat[i][j] > 0)
                {
                    resultTextView2.append("\n " + dDay.dayMembers.get(j).getName() + Config_Language.get().resultTo
                            + resultMoneyMat[i][j] + Config_Language.get().resultToBeSent);
                }
            }
            resultTextView2.append("\n");
        }


    }

    //old ver.
//    private void tempManagerFunction() {
//        Member managerMember;
//        String memberName;
//
//        resultTextView2.append("\n");
//
//        //Dynamic programming
//        // Need to adjust this in every money distribution
//
//        int dayMembersLength = dDay.dayMembers.getLength();
//        int[][] resultMoneyMat = MoneyMatrix.INSTANCE.resultMoneyMat;
//
////        resultTextView2.append("\n");
////        for (int i = 0; i < dayMembersLength; ++i)
////        {
////            if (!((Member)dDay.dayMembers.get(i)).managerMap.isEmpty())
////            {
////                memberName = dDay.dayMembers.get(i).getName();
////                resultTextView2.append("\n" + memberName + "님의 정산결과는 \n");
////                for (Map.Entry<Member, Integer> entry : ((Member)dDay.dayMembers.get(i)).managerMap.entrySet())
////                {
////
////                    managerMember = entry.getKey();
////                    for (int j = 0; j < dayMembersLength; ++j)
////                    {
////                        if (managerMember.getName().equals(dDay.dayMembers.get(j).getName()))
////                        {
////                            resultMoneyMat[i][j] = managerMember.getMoney();
////                            int money2Send = resultMoneyMat[i][j];
////
////                            if (resultMoneyMat[j][i] > 0)
////                            {
////                                int money2Receive = resultMoneyMat[j][i];
////
////                                if (money2Send > money2Receive)
////                                {
////                                    resultMoneyMat[i][j] = money2Send - money2Receive;
////                                }
////                                else if (money2Send == money2Receive)
////                                {
////                                    resultMoneyMat[i][j] = 0;
////                                    resultMoneyMat[j][i] = 0;
////                                }
////                                else if (money2Send < money2Receive)
////                                {
////                                    resultMoneyMat[j][i] = money2Receive - money2Send;
////                                }
////                            }
////                        }
////                    }
////                    resultTextView2.append("\n " + entry.getKey().getName() + " 에게 " + entry.getValue() + "원을 부쳐야합니다");
////                }
////                resultTextView2.append("\n\n");
////            }
////        }
//
////        resultTextView2.append("\n");
////        for (int i = 0; i < dayMembersLength; ++i)
////        {
////            Member member = ((Member)dDay.dayMembers.get(i));
////            if (!member.managerMap.isEmpty())
////            {
////                memberName = member.getName();
////                resultTextView2.append("\n" + memberName + "님의 정산결과는 \n");
////                for (Map.Entry<Member, Integer> entry : ((Member)dDay.dayMembers.get(i)).managerMap.entrySet())
////                {
////                    resultTextView2.append("\n " + entry.getKey().getName() + " 에게 " + entry.getValue() + "원을 부쳐야합니다");
////                }
////                resultTextView2.append("\n\n");
////            }
////        }
//
//        // 고쳐야함
//        //
//        String tempBuffer = "";
//        for (int i = 0; i < dayMembersLength; ++i)
//        {
//            for (int j = 0; j < dayMembersLength; ++j)
//            {
//                tempBuffer += resultMoneyMat[i][j];
//                tempBuffer += "       ";
//            }
//            tempBuffer += "\n";
//        }
//        resultTextView2.append(tempBuffer);
//
//    }
    /**
     * 마지막에 DDay->Location 돌면서
     *  각 차마다 Manager따로 빼두고(Member), 나중에 계좌번호 등 필요해서 멤버객체로
     *  각 Location의 멤버들을 빼둔 Manager 에다가 이름하고 각 금액 더해준다
     * 다 돌고 나면 -> ManagerCalculator 에서 ManagerCalculator 객체들에 돈 받아낼 이름, 금액 적인거 갖고 있음
     * 출력한다
     */

    private void getComponentsId() {
        resultOfCalc = (TextView)findViewById(R.id.resultOfCalc);
        copyResultButton1 = (Button)findViewById(R.id.copyResultButton1);
        copyResultButton2 = (Button)findViewById(R.id.copyResultButton2);
        resultSaveButton = (Button)findViewById(R.id.resultSaveButton);
        resultDoneButton = (Button)findViewById(R.id.resultDoneButton);
    }

    private void setComponentsNames() {
        resultOfCalc.setText(Config_Language.get().resultOfCalc);
        copyResultButton1.setText(Config_Language.get().copyResultButton1);
        copyResultButton2.setText(Config_Language.get().copyResultButton2);
        resultSaveButton.setText(Config_Language.get().resultSaveButton);
        resultDoneButton.setText(Config_Language.get().resultDoneButton);
    }
}
