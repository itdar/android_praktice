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

    // activity 정리하고 꾸며야함
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_result);

        dDay = DDay.getInstance();
        resultTextView = (TextView) findViewById(R.id.resultTextView);

        Visitor editTextVisitor = new Visitor_AppendEditText();
        dDay.accept(editTextVisitor, resultTextView);
        dDay.dayMembers.accept(editTextVisitor, resultTextView);

        //새로 TextView 만들어서 각 매니저한테 얼마씩 줘야 하는지 출력 되도록 해야함
        //만들때 visitor 새로 만들어야함
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.doneButton : doneButtonClicked(); break;
            case R.id.copyResultButton : copyResultButtonClicked(); break;
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

    /**
     * 마지막에 DDay->Location 돌면서 Manager따로 빼두고(Member), 각 Location의 멤버들을 빼둔 Manager정보랑 같이 각각 출력
     */

}
