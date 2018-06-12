package com.gin.praktice.visitor;

import android.widget.TextView;

import com.gin.praktice.component.DDay;
import com.gin.praktice.component.Location;
import com.gin.praktice.component.Member;
import com.gin.praktice.member.DayMembers;

public class Visitor_AppendEditText implements Visitor {

    @Override
    public void visit(DDay dDay) {}
    @Override
    public void visit(Location location) {}
    @Override
    public void visit(DayMembers dayMembers) {}
    @Override
    public void visit(Member member){}

    @Override
    public void visit(DDay dDay, TextView textView) {
        textView.append("\n\n" + " 날 이름 >> " + dDay.getName() + "\n" + " 날  짜    >> " + dDay.getDate() + "\n");
        for (int i = 0; i < dDay.getList().size(); i++) {
            dDay.get(i).accept(this, textView);
        }
    }

    @Override
    public void visit(Location location, TextView textView) {
        textView.append("\n\n     " + "가게 이름 >> " + location.getName() + "\n     " + "금액 >> " + location.getMoney());
        for (int i = 0; i < location.getList().size(); i++) {
            location.get(i).accept(this, textView);
        }
    }

    @Override
    public void visit(DayMembers dayMembers, TextView textView) {
        textView.append("\n\n\n	     " + "총 결산 인원" + " >> " + dayMembers.getLength());
        for (int i = 0; i < dayMembers.getLength(); i++) {
            dayMembers.get(i).accept(this, textView);
        }
    }

    @Override
    public void visit(Member member, TextView textView) {
        textView.append("\n	        " + member.getName() + " " + member.getMoney());
    }
}
