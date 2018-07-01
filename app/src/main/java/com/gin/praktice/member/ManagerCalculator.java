package com.gin.praktice.member;

import android.widget.TextView;

import com.gin.praktice.component.Member;
import com.gin.praktice.visitor.Visitor;

public class ManagerCalculator {
    private Member managerMember;
    private int money2Send;

    public ManagerCalculator() {
        this.managerMember = null;
        this.money2Send = 0;
    }
    public ManagerCalculator(ManagerCalculator source) {
        this.managerMember = source.managerMember;
        this.money2Send = source.money2Send;
    }

    public void accept(Visitor visitor) {}
    public ManagerCalculator clone() { return new ManagerCalculator(this); }

    public void accept(Visitor visitor, TextView textView) {
        visitor.visit(this, textView);
    }
}
