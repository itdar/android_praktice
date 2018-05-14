package com.gin.praktice.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.doneButton : doneButtonClicked(); break;
            default: break;
        }
    }

    // 여기 레이아웃 잡고 기능 추가부터 시작


    private void doneButtonClicked() {
        dDay.getList().clear();

        finish();
    }

}
