package com.gin.praktice.member;

import com.gin.praktice.component.Member;

import java.util.ArrayList;


//Need to check Enum class for singleton
public class AllMember {
	private static AllMember instance;
	
	private ArrayList<Member> list = new ArrayList<Member>();
	
	public AllMember() {
		
	}
	public AllMember(ArrayList<Member> list) {
		this.list = list;
	}
	
	public static AllMember loadAllMember() {
		if (instance == null) {
			instance = new AllMember();
		}
		return instance;
	}
	
	public Member getMember(int index) {
		return this.list.get(index);
	}
	public void putMember(Member member) {
		this.list.add(member);
	}
	// overload putMember(String name)
	
	public void setList(ArrayList<Member> list) {
		this.list = list;
	}
	
	public ArrayList<Member> getList() {
		return this.list;
	}
	public int getLength() {
		return this.list.size();
	}
}
