package com.gin.praktice.member;

import android.widget.TextView;

import com.gin.praktice.component.Member;
import com.gin.praktice.visitor.Visitor;

import java.util.LinkedList;
import java.util.List;

public enum ManagerMembers {
    INSTANCE;

    private List<Member> managerList = new LinkedList<>();

    public void addManager(Member managerMember) {
        if (!managerList.contains(managerMember))
        {
            managerList.add(managerMember);
        }
    }

    public void removeManager() { }

    public void accept(Visitor visitor, TextView textView) {
        visitor.visit(this, textView);
    }

    public List<Member> getManagerList() {
        return this.managerList;
    }
}
