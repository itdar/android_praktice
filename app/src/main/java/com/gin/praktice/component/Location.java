package com.gin.praktice.component;

import com.gin.praktice.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Location extends Composite {
	
	
	public Location() {
		
	}
	public Location(String name, int money) {
		this.name = name;
		this.money = money;
	}
	public Location(Location source) {
		for (int i = 0; i < source.list.size(); i++) {
			this.list.add(source.list.get(i).clone());
		}
		this.name = source.name;
		this.money = source.money;
	}
	
	public void distribution() {
		List<Component> tempList = new ArrayList<Component>();
		for (int i = 0; i < this.getLength(); i++) {
			tempList.add(((Member)this.list.get(i).clone()));
		}
		int share = money / this.getLength();
		for (int i = 0; i < this.getLength(); i++) {
			// Accumulation -> plusMoney / Each round money -> setMoney
			((Member)tempList.get(i)).setMoney(share);
			((Member) this.get(i)).plusMoney(share);
		}
		this.setList(tempList);
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	@Override
	public Location clone() {
		return new Location(this);
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	public int getMoney() {
		return this.money;
	}
	public int getLength() {
		return this.list.size();
	}
}
