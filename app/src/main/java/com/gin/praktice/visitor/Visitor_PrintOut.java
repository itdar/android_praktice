package com.gin.praktice.visitor;

import com.gin.praktice.composite.DDay;
import com.gin.praktice.composite.DayMembers;
import com.gin.praktice.composite.Location;
import com.gin.praktice.composite.Member;

public class Visitor_PrintOut implements Visitor {

	@Override
	public void visit(DDay dDay) {
		System.out.println(dDay.getName() + " " + dDay.getDate());
		for (int i = 0; i < dDay.getList().size(); i++) {
			dDay.get(i).accept(this);
		}
	}

	@Override
	public void visit(Location location) {
		System.out.println("	" + location.getName() + " " + location.getMoney());
		for (int i = 0; i < location.getList().size(); i++) {
			location.get(i).accept(this);
		}
	}
	
	@Override
	public void visit(DayMembers dayMembers) {
		System.out.println("	" + "DayMembers" + " >> " + dayMembers.getLength());
		for (int i = 0; i < dayMembers.getLength(); i++) {
			dayMembers.get(i).accept(this);
		}		
	}
	
	@Override
	public void visit(Member member) {
		System.out.println("		" + member.getName() + " " + member.getMoney());
	}


}
