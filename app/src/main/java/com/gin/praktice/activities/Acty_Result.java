package com.gin.praktice.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.gin.praktice.R;
import com.gin.praktice.component.DDay;
import com.gin.praktice.visitor.Visitor;
import com.gin.praktice.visitor.Visitor_AppendEditText;

public class Acty_Result extends Activity {
    private DDay dDay;

    private EditText multiText1;

    // activity 정리하고 꾸며야함
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_result);

        dDay = DDay.getInstance();
        multiText1 = (EditText) findViewById(R.id.multiText1);

        Visitor editTextVisitor = new Visitor_AppendEditText();
        dDay.accept(editTextVisitor, multiText1);
        dDay.dayMembers.accept(editTextVisitor, multiText1);
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
