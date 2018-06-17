package com.gin.praktice.component;

import android.widget.TextView;

import com.gin.praktice.member.DayMembers;
import com.gin.praktice.visitor.Visitor;

public class DDay extends Composite {
	private static DDay instance;

	public DayMembers dayMembers;
	private String date;
	
	public DDay() {
		this.dayMembers = new DayMembers();
		this.date = null;
	}
	
	public DDay(DDay source) {
		for (int i = 0; i < source.list.size(); i++) {
			this.list.add(source.list.get(i).clone());
		}
		this.name = source.name;
		this.date = source.date;
	}

	public static DDay getInstance() {
		if (instance == null) {
			instance = new DDay();
		}
		return instance;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	@Override
	public void accept(Visitor visitor, TextView textView) {
		visitor.visit(this, textView);
	}

	@Override
	public Component clone() {
		return new DDay(this);
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	public String getDate() {
		return this.date;
	}
}
