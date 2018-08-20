package com.gin.praktice.component;

import android.widget.TextView;

import com.gin.praktice.visitor.Visitor;

import java.util.HashMap;
import java.util.Map;

public class Member extends Component {

	private String bank;
	private String accountNumber;
	private String phoneNumber;

	public Map<Member, Integer> managerMap = new HashMap<>();

	public boolean isOneThird = false; // 2
	public boolean isOneSecond = false; // 3 // normal = 6
	private boolean isManager = false;

	public Member(Builder builder) {
		this.name = builder.name;
		this.money = builder.money;
		this.bank = builder.bank;
		this.accountNumber = builder.accountNumber;
		this.phoneNumber = builder.phoneNumber;
	}
	public Member(Member source) {
		this.name = source.name;
		this.money = source.money;
		this.bank = source.bank;
		this.accountNumber = source.accountNumber;
		this.phoneNumber = source.phoneNumber;
		this.isOneSecond = source.isOneSecond;
		this.isOneThird = source.isOneThird;
		this.isManager = source.isManager;
	}

	public static class Builder {
		private String name = "N/A";
		private int money = 0;
		private String bank = "N/A";
		private String accountNumber = "N/A";
		private String phoneNumber = "N/A";

		public Builder name(String name) {
			this.name = name;
			return this;
		}
		public Builder money(int money) {
			this.money = money;
			return this;
		}
		public Builder bank(String bank) {
			this.bank = bank;
			return this;
		}
		public Builder accountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
			return this;
		}
		public Builder phoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}
		public Member build() {
			return new Member(this);
		}
	}

	@Override
	public boolean equals(Object o) {
		Member member = (Member)o;
		boolean result = false;
		if (this.name == member.name
				&& this.money == member.money
				&& this.bank == member.bank
				&& this.accountNumber == member.accountNumber
				&& this.managerMap.equals(member.managerMap)
				&& this.isOneThird == member.isOneThird
				&& this.isOneSecond == member.isOneSecond
				&& this.phoneNumber == member.phoneNumber)
		{
			result = true;
		}
		return result;
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
	public Member clone() {
		return new Member(this);
	}
	
	public void plusMoney(int money) {
		this.money += money;
	}
	public void minusMoney(int money) {
		this.money -= money;
	}

	public String getBank() { return this.bank; }
	public String getAccountNumber() { return this.accountNumber; }
	public String getPhoneNumber() { return this.phoneNumber; }

	public void setManager(boolean isManager) { this.isManager = isManager; }
	public boolean getManager() { return this.isManager; }

	public void setBank(String bank) { this.bank = bank; }
	public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
	public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

	public void addManagerCalc(Member manager, int money2Send) {
		if (!this.name.equals(manager.getName()))
		{
			if (this.managerMap.containsKey(manager))
			{
				int tempMoney = this.managerMap.get(manager);
				this.managerMap.remove(manager);
				this.managerMap.put(manager, tempMoney + money2Send);
			}
			else
			{
				this.managerMap.put(manager, money2Send);
			}
		}
	}
}
