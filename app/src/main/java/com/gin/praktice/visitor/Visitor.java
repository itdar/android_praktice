package com.gin.praktice.visitor;

import com.gin.praktice.composite.DDay;
import com.gin.praktice.member.DayMembers;
import com.gin.praktice.composite.Location;
import com.gin.praktice.composite.Member;

public interface Visitor {
	
	abstract void visit(DDay dDay);
	abstract void visit(Location location);
	abstract void visit(DayMembers dayMembers);
	abstract void visit(Member member);
}
