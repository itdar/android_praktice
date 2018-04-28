package com.gin.praktice.component;


import com.gin.praktice.visitor.Visitor;

public abstract class Component {
	protected String name = "";
	protected int money = 0;
	
	public abstract void accept(Visitor visitor);
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
