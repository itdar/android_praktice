package com.gin.praktice.member;

import android.widget.EditText;

import com.gin.praktice.component.Component;
import com.gin.praktice.component.Member;
import com.gin.praktice.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class DayMembers {
	private List<Component> list = new ArrayList<Component>();
	
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
	
	public Component get(int index) {
		return this.list.get(index);
	}
	
	public DayMembers clone() {
		return new DayMembers(this);
	}

	public void accept(Visitor visitor, EditText editText) {
		visitor.visit(this, editText);
	}

	public void setList(List<Component> list) {
		this.list = list;
	}
	public List<Component> getList() {
		return this.list;
	}
	public int getLength() {
		return this.list.size();
	}
}
