package com.gin.praktice.activities;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gin.praktice.R;
import com.gin.praktice.component.DDay;
import com.gin.praktice.visitor.Visitor;
import com.gin.praktice.visitor.Visitor_AppendEditText;

public class Acty_Result extends Activity {
    private DDay dDay;

    private TextView resultTextView;
    private TextView resultTextView2;

    // activity 정리하고 꾸며야함
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_result);

        dDay = DDay.getInstance();
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        resultTextView2 = (TextView) findViewById(R.id.resultTextView);

        Visitor editTextVisitor = new Visitor_AppendEditText();
        dDay.accept(editTextVisitor, resultTextView);
        dDay.dayMembers.accept(editTextVisitor, resultTextView);

        //만들때 visitor 새로 만들어야함
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.doneButton : doneButtonClicked(); break;
            case R.id.saveButton : saveButtonClicked(); break;
            case R.id.copyResultButton : copyResultButtonClicked(); break;
            case R.id.copyResultButton2 : copyResultButton2Clicked(); break;
            default: break;
        }
    }

    private void copyResultButtonClicked() {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB){
            android.content.ClipboardManager clipboard =  (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("resultCopy", resultTextView.getText());
            clipboard.setPrimaryClip(clip);
        } else{
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
            clipboard.setText(resultTextView.getText());
        }
        Toast.makeText(getApplicationContext(), "Text copied to clipboard", Toast.LENGTH_SHORT).show();
    }

    private void doneButtonClicked() {
        dDay.getList().clear();

        finish();
    }

    private void saveButtonClicked() {

    }

    private void copyResultButton2Clicked() {

    }

    /**
     * 마지막에 DDay->Location 돌면서
     *  각 차마다 Manager따로 빼두고(Member), 나중에 계좌번호 등 필요해서 멤버객체로
     *  각 Location의 멤버들을 빼둔 Manager 에다가 이름하고 각 금액 더해준다
     * 다 돌고 나면 -> ManagerMembers 에서 ManagerMember 객체들에 돈 받아낼 이름, 금액 적인거 갖고 있음
     * 출력한다
     */

}
