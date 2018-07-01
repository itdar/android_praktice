package com.gin.praktice.component;

import android.widget.TextView;

import com.gin.praktice.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Location extends Composite {

	private Member manager = null;
	
	public Location() {
		
	}
	public Location(String name, int money) {
		this.name = name;
		this.money = money;
	}
	public Location(Location source) {
		for (Component val : source.list) {
			this.list.add(val.clone());
		}
		this.name = source.name;
		this.money = source.money;
	}

	/**
	 *  DDay의 DayMembers 와 연결된 멤버를 tempList에 새로 만들어두고
	 *  해당 Location에서의 Money를 1/n 해서 temp에 넣어주고 (Location에 남을 거)
	 *  1/n 한 돈을 AllMember와 연결된 것에 더해준다 (DayMembers(DDay안에) 에 각각 전체 돈)
	 */
	public void distribution() {
		List<Component> tempList = new ArrayList<Component>();
		Member member;
		int divider = 0;

		// 늦은사람 있는지 확인해서 없으면 그냥 보통대로 깔끔하게 계산하는 식으로 가야 오차 안생김 **
		for (int i = 0; i < this.getLength(); ++i)
		{
			member = ((Member)this.get(i));
			if (member.isOneSecond)
			{
				divider += 3;
			}
			else if (member.isOneThird)
			{
				divider += 2;
			}
			else
			{
				divider += 6;
			}
			tempList.add(((Member)this.list.get(i).clone()));
		}

		int dividedShare = money / divider;

		// Accumulation -> plusMoney / Each round money -> setMoney
		for (int i = 0; i < this.getLength(); ++i) {
			member = ((Member)this.get(i));
			if (member.isOneSecond)
			{
				((Member)tempList.get(i)).setMoney(dividedShare * 3);
				member.plusMoney(dividedShare * 3);
				member.addManagerCalc(this.manager, dividedShare * 3);
			}
			else if (member.isOneThird)
			{
				((Member)tempList.get(i)).setMoney(dividedShare * 2);
				member.plusMoney(dividedShare * 2);
				member.addManagerCalc(this.manager, dividedShare * 2);
			}
			else
			{
				((Member)tempList.get(i)).setMoney(dividedShare * 6);
				member.plusMoney(dividedShare * 6);
				member.addManagerCalc(this.manager, dividedShare * 6);
			}
		}
		this.setList(tempList);
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
	public Location clone() {
		return new Location(this);
	}

	public Component getManager() { return this.manager; }
	public void setManager(Component member) { this.manager = (Member)member; }

}
