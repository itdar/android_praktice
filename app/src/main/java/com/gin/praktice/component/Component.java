package com.gin.praktice.component;


import android.widget.TextView;

import com.gin.praktice.visitor.Visitor;

import java.io.Serializable;

public abstract class Component implements Serializable {
	protected String name = "";
	protected int money = 0;
	
	public abstract void accept(Visitor visitor);
	public abstract void accept(Visitor visitor, TextView textView);
	public abstract Component clone();
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getMoney() {
		return this.money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
}
