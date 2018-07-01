package com.gin.praktice.visitor;

import android.widget.TextView;

import com.gin.praktice.component.DDay;
import com.gin.praktice.component.Location;
import com.gin.praktice.component.Member;
import com.gin.praktice.member.DayMembers;
import com.gin.praktice.member.ManagerCalculator;

public interface Visitor {
	
	abstract void visit(DDay dDay);
	abstract void visit(Location location);
	abstract void visit(DayMembers dayMembers);
	abstract void visit(Member member);

	abstract void visit(DDay dDay, TextView textView);
	abstract void visit(Location location, TextView textView);
	abstract void visit(DayMembers dayMembers, TextView textView);
	abstract void visit(Member member, TextView textView);
	abstract void visit(ManagerCalculator managerCalculator, TextView textView);
}
