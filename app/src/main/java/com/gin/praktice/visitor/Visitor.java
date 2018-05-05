package com.gin.praktice.visitor;

import android.widget.EditText;

import com.gin.praktice.component.DDay;
import com.gin.praktice.member.DayMembers;
import com.gin.praktice.component.Location;
import com.gin.praktice.component.Member;

public interface Visitor {
	
	abstract void visit(DDay dDay);
	abstract void visit(Location location);
	abstract void visit(DayMembers dayMembers);
	abstract void visit(Member member);

	abstract void visit(DDay dDay, EditText editText);
	abstract void visit(Location location, EditText editText);
	abstract void visit(DayMembers dayMembers, EditText editText);
	abstract void visit(Member member, EditText editText);
}
