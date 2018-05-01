package com.gin.praktice.member;

import com.gin.praktice.component.Member;

import java.util.ArrayList;
import java.util.List;

public class DayMembers {
	private List<Member> list = new ArrayList<Member>();
	
	public DayMembers() {

	}
	public DayMembers(DayMembers source) {
		for (int i = 0; i < source.list.size(); i++) {
			this.list.add(source.list.get(i).clone());
		}
	}
	
	public void add(Member member) {
		this.list.add(member);
	}

	public void remove(int index) {
		this.list.remove(index);
	}
	
	public Member get(int index) {
		return this.list.get(index);
	}
	
	public DayMembers clone() {
		return new DayMembers(this);
	}
//	public void accept(Visitor visitor) {
//		visitor.visit(this);
//	}

	public void setList(List<Member> list) {
		this.list = list;
	}
	public List<Member> getList() {
		return this.list;
	}
	public int getLength() {
		return this.list.size();
	}
}
