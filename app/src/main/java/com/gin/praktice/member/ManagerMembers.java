package com.gin.praktice.member;

import android.widget.TextView;

import com.gin.praktice.component.Component;
import com.gin.praktice.component.Composite;
import com.gin.praktice.visitor.Visitor;

public class ManagerMembers extends Composite {
    private static ManagerMembers instance;

    private ManagerMembers() {}
    private ManagerMembers(ManagerMembers source) {
        this.name = source.name;
        this.money = source.money;
        for (int i = 0; i < this.list.size(); ++i) {
            this.list.add(source.get(i));
        }
    }

    public static ManagerMembers getInstance() {
        if (instance == null)
        {
            instance = new ManagerMembers();
        }
        return instance;
    }


    @Override
    public void add(Component component) {};
    public void addManager(Component managerMember) {
        if (!list.contains(managerMember))
        {
            list.add(managerMember);
        }
    }

    public void accept(Visitor visitor) {}
    public Component clone() {
        return new ManagerMembers(this);
    }

    public void accept(Visitor visitor, TextView textView) {
        visitor.visit(this, textView);
    }
}
