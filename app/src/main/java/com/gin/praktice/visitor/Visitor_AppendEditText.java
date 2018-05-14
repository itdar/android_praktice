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
        textView.append("\n	" + dDay.getName() + " " + dDay.getDate());
        for (int i = 0; i < dDay.getList().size(); i++) {
            dDay.get(i).accept(this, textView);
        }
    }

    @Override
    public void visit(Location location, TextView textView) {
        textView.append("\n     " + location.getName() + " " + location.getMoney());
        for (int i = 0; i < location.getList().size(); i++) {
            location.get(i).accept(this, textView);
        }
    }

    @Override
    public void visit(DayMembers dayMembers, TextView textView) {
        textView.append("\n\n	    " + "DayMembers" + " >> " + dayMembers.getLength());
        for (int i = 0; i < dayMembers.getLength(); i++) {
            dayMembers.get(i).accept(this, textView);
        }
    }

    @Override
    public void visit(Member member, TextView textView) {
        textView.append("\n	        " + member.getName() + " " + member.getMoney());
    }
}
