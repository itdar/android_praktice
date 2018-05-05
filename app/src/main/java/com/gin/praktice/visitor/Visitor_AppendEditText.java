package com.gin.praktice.visitor;

import android.widget.EditText;

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
    public void visit(DDay dDay, EditText editText) {
        editText.append("\n	" + dDay.getName() + " " + dDay.getDate());
        for (int i = 0; i < dDay.getList().size(); i++) {
            dDay.get(i).accept(this, editText);
        }
    }

    @Override
    public void visit(Location location, EditText editText) {
        editText.append("\n     " + location.getName() + " " + location.getMoney());
        for (int i = 0; i < location.getList().size(); i++) {
            location.get(i).accept(this, editText);
        }
    }

    @Override
    public void visit(DayMembers dayMembers, EditText editText) {
        editText.append("\n\n	    " + "DayMembers" + " >> " + dayMembers.getLength());
        for (int i = 0; i < dayMembers.getLength(); i++) {
            dayMembers.get(i).accept(this, editText);
        }
    }

    @Override
    public void visit(Member member, EditText editText) {
        editText.append("\n	        " + member.getName() + " " + member.getMoney());
    }
}
