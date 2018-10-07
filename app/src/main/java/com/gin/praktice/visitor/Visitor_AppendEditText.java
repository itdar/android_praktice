package com.gin.praktice.visitor;

import android.widget.TextView;

import com.gin.praktice.component.DDay;
import com.gin.praktice.component.Location;
import com.gin.praktice.component.Member;
import com.gin.praktice.config.lang.Config_Language;
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
        textView.append("\n\n" + Config_Language.get().resultDDayName + dDay.getName() +
                "\n" + Config_Language.get().resultDate + dDay.getDate() + "\n");
        for (int i = 0; i < dDay.getList().size(); i++) {
            dDay.get(i).accept(this, textView);
        }
    }

    @Override
    public void visit(Location location, TextView textView) {
        textView.append("\n\n     " + Config_Language.get().resultLocationName + location.getName()
                        + "\n     " + Config_Language.get().resultLocationMoney + location.getMoney());
        if (location.getManager() != null)
        {
            textView.append("\n     " + Config_Language.get().resultHost + location.getManager().getName());
        }

        for (int i = 0; i < location.getList().size(); i++) {
            location.get(i).accept(this, textView);
        }
    }

    @Override
    public void visit(DayMembers dayMembers, TextView textView) {
        textView.append("\n\n\n	     " + Config_Language.get().resultTotalMember + " >> " + dayMembers.getLength());
        for (int i = 0; i < dayMembers.getLength(); i++) {
            dayMembers.get(i).accept(this, textView);
        }
    }

    @Override
    public void visit(Member member, TextView textView) {
        textView.append("\n	        " + member.getName() + " " + member.getMoney());
    }

}
