package com.gin.praktice.squad;

import com.gin.praktice.component.Member;

import java.util.ArrayList;
import java.util.List;

public class Squad {
    private String name;
    private List<Member> members;

    public Squad(String name) {
        this.name = name;
        this.members = new ArrayList<Member>();
    }

    public void addMember(Member member) {
        this.members.add(member);
    }
    public void removeMember(String name) {
        boolean isDone = false;
        for (int i = 0; !isDone && i < members.size(); i++) {
            if (members.get(i).getName().equals(name)) {
                members.remove(i);
                isDone = true;
            }
        }
    }
    public void clearMembers() {
        this.members.clear();
    }

    public void setMembers(List<Member> memberList) {
        this.members = memberList;
    }
    public List<Member> getMembers() {
        return this.members;
    }
}
