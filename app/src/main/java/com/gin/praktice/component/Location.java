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
	 *  DDay의 DayMembers 와 연결된 Member들 tempList에 새로 만들어두고
	 *  해당 Location에서의 Money를 1/n 해서 tempList member들에 //넣어주고 (Location에 남을 거)
	 *  1/n 한 돈을 AllMember와 연결된 것에 //더해준다 (DayMembers(DDay안에) 에 각각 전체 돈)
	 */
	public void distribution() {
		List<Component> tempList = new ArrayList<>();
		Member member;
		int divider = 0;

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
//			tempList.add(((Member) this.list.get(i).clone()));
			tempList.add(member.clone());
		}

		int dividedShare = money / divider;

		// Accumulation -> plusMoney / Each round money -> setMoney
		for (int i = 0; i < this.getLength(); ++i) {
			member = ((Member)this.get(i));
			if (member.isOneSecond)
			{
			    int oneSecond = (dividedShare * 3 + 5) / 10 * 10;
				((Member)tempList.get(i)).setMoney(oneSecond);
				member.plusMoney(oneSecond);
//				member.addManagerCalc(this.manager, oneSecond);
			}
			else if (member.isOneThird)
			{
			    int oneThird = (dividedShare * 2 + 5) / 10 * 10;
				((Member)tempList.get(i)).setMoney(oneThird);
				member.plusMoney(oneThird);
//				member.addManagerCalc(this.manager, oneThird);
			}
			else
			{
			    int oneFirst = (dividedShare * 6 + 5) / 10 * 10;
				((Member)tempList.get(i)).setMoney(oneFirst);
				member.plusMoney(oneFirst);
//				member.addManagerCalc(this.manager, oneFirst);
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
