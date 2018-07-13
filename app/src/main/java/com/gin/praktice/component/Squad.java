package com.gin.praktice.component;

import android.widget.TextView;

import com.gin.praktice.visitor.Visitor;

public class Squad extends Composite {

    public Squad(String name) {
        this.name = name;
    }
    public Squad(Squad source) {
        this.name = source.name;
        this.money = source.money;
        for (Component val : source.list)
        {
            this.list.add(val.clone());
        }
    }


//    public void setList(List<Component> list) { this.list = list; }
//    public String getName() { return this.name; }



//    public void removeMember(String name) {
//        boolean isDone = false;
//        for (int i = 0; !isDone && i < members.size(); i++) {
//            if (members.get(i).getName().equals(name)) {
//                members.remove(i);
//                isDone = true;
//            }
//        }
//    }

    @Override
    public void accept(Visitor visitor) {
//        visitor.visit(this);
    }

    @Override
    public void accept(Visitor visitor, TextView textView) {
//        visitor.visit(this, textView);
    }

    @Override
    public Component clone() { return new Squad(this); }
}
