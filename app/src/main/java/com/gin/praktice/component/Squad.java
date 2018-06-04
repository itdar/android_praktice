package com.gin.praktice.component;

import android.widget.TextView;

import com.gin.praktice.visitor.Visitor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Squad extends Composite implements Serializable {
    private String name;
    private List<Component> members;

    // TODO 0604
    // Squad 를 Composite 상속받는걸로 바꿔서
    // MainActy 에서 새로운 Squad 추가한 후에 받아온 객체를
    // RecyclerView 에다가 올려줘야함
    // 코드정리하면서~

    public Squad() {
        this.name = null;
        this.members = new ArrayList<>();
    }
    public Squad(String name) {
        this.name = name;
        this.members = new ArrayList<Component>();
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

    public void setMembers(List<Component> memberList) {
        this.members = memberList;
    }
    public List<Component> getMembers() {
        return this.members;
    }

    @Override
    public void accept(Visitor visitor) {

    }

    @Override
    public void accept(Visitor visitor, TextView textView) {

    }

    @Override
    public Component clone() {
        return null;
    }

    public String getName() { return this.name; }
}
