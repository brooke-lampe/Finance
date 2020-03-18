package com.sdb;

public class Node<T> {

	private final T element;
	private Node<T> next;
	
	public Node(T element) {
		this.element = element;
		next = null;
	}

	public T getElement() {
		return this.element;
	}
	
	public Node<T> getNext() {
		return this.next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	public boolean hasNext() {
		return (this.next == null) ? false : true;
	}
	
	public String toString() {
		return "Node containing " + this.element;
	}
}
