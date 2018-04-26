package com.gin.praktice.member;

import com.gin.praktice.composite.Member;
import com.gin.praktice.visitor.Visitor;

import java.util.ArrayList;

public class DayMembers {
	private ArrayList<Member> list = new ArrayList<Member>();
	private int length;
	
	public DayMembers() {
		this.length = 0;
	}
	public DayMembers(DayMembers source) {
		for (int i = 0; i < source.list.size(); i++) {
			this.list.add(source.list.get(i).clone());
		}
		this.length = source.length;
	}
	
	public void add(Member member) {
		this.list.add(member.clone());
		this.length++;
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
	public void accept(Visitor visitor) {
		visitor.visit(this);		
	}
	
	public int getLength() {
		return this.length;
	}
}
