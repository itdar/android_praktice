package com.gin.praktice.composite;

import java.util.ArrayList;
import java.util.List;

abstract class Composite extends Component {
	protected List<Component> list = new ArrayList<Component>();

	public void add(Component component) {
		this.list.add(component);
	}
	
	public void remove(Component component) {
		this.list.remove(component);
	}
	public void remove(int index) {
		this.list.remove(index);
	}
	
	public Component get(int index) {
		return this.list.get(index);
	}
//	public Component get(String name) {
//		for (Component component : this.list) {
//			if (component.getName().equals(name)) {
//				return component;
//			}
//		}
//		return null;
//	}
	
	public abstract Component clone();
	
	
	public void setList(List<Component> list) {
		this.list = list;
	}
	public List<Component> getList() {
		return this.list;
	}	
}
