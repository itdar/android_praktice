package com.gin.praktice.component;

import android.widget.TextView;

import com.gin.praktice.visitor.Visitor;

public class Member extends Component {
	// 계좌명 / 은행명 / 전화번호  // 옵션 - Builder pattern
	private String phoneNumber;

	private Member() {
		
	}
	public Member(String name) {
		this.name = name;
		this.phoneNumber = "N/A";
		this.money = 0;
	}
	public Member(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.money = 0;
	}
	public Member(String name, int money) {
		this.name = name;
		this.phoneNumber = "N/A";
		this.money = money;
	}
	public Member(Member source) {
		this.name = source.name;
		this.phoneNumber = source.phoneNumber;
		this.money = source.money;
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
	

	public String getPhoneNumber() { return this.phoneNumber; }
	public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
