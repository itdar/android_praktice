package com.gin.praktice.member;

import com.gin.praktice.component.Component;
import com.gin.praktice.component.Member;
import com.gin.praktice.visitor.Visitor;

import java.util.ArrayList;

public class DayMembers {
	private ArrayList<Component> list = new ArrayList<Component>();
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
	
	public Component get(int index) {
		return this.list.get(index);
	}
	
	public DayMembers clone() {
		return new DayMembers(this);
	}
	public void accept(Visitor visitor) {
		visitor.visit(this);		
	}

	public ArrayList<Component> getList() {
		return this.list;
	}
	public int getLength() {
		return this.length;
	}
}
