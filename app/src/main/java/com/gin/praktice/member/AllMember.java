package com.gin.praktice.member;

import com.gin.praktice.component.Member;

import java.util.ArrayList;
import java.util.List;


//Need to check Enum class for singleton
public enum AllMember {
	INSTANCE;
	
	private List<Member> list = new ArrayList<Member>();

	public Member getMember(int index) {
		return this.list.get(index);
	}
	public void putMember(Member member) {
		this.list.add(member);
	}
	
	public void setList(List<Member> list) {
		this.list = list;
	}
	public List<Member> getList() {
		return this.list;
	}

	public int getLength() {
		return this.list.size();
	}
}
