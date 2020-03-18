package com.sdb;

import java.util.Iterator;
import com.sdb.Node;

public class UnsortedList<T> implements Iterable<T> {
	
	private Node<T> head = null;
	
	//Insert methods
	//Insert element at start of list
	public void insertElementAtStart(T element) {
		Node<T> newNode = new Node<T>(element);
		newNode.setNext(this.head);
		this.head = newNode;
	}
	
	//Insert element at given index of list
	public void insertElementAtIndex(T element, int index) {
		if(index < 0 || index > this.getSize()) {
			throw new IndexOutOfBoundsException("Index value provided is out of bounds.");
		}
		
		if(index == this.getSize()) {
			this.insertElementAtEnd(element);
		} else if(index == 0) {
			this.insertElementAtStart(element);
		}
		
		Node<T> node = this.retrieveNode(index-1);
		Node<T> newNode = new Node<T>(element);
		newNode.setNext(node.getNext());
		node.setNext(newNode);
	}
	
	//Insert element at end of list
	public void insertElementAtEnd(T element) {
		Node<T> newNode = new Node<T>(element);
    	
    	if(head == null) {
			head = newNode;
		} else {
	    	Node<T> current = head;
			
			while(current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(newNode);
		}
	}
	
	//Remove methods
	//Remove element at given index of list
	public void removeElementAtIndex(int index) {
    	if(index < 0 || index > (this.getSize() - 1)) {
			throw new IndexOutOfBoundsException("Index value provided is out of bounds.");
		} else if(index == 0 && head != null) {
			head = head.getNext();
		} else {
			Node<T> current = head;
			Node<T> previous = null;
			
			for(int i = 0; i < index; i++) {
				previous = current;
				current = current.getNext();
			}
			previous.setNext(current.getNext());
		}
    }
	
	//Remove element at first instance of element
	public void removeFirstInstance(T element) {
		if(head == null) {
			//Do nothing
		} else if(head.getElement().equals(element)) {
			head = head.getNext();
		} else {
			Node<T> current = head;
			Node<T> previous = null;
			while(current.hasNext() && !current.getElement().equals(element)) {
				previous = current;
				current = current.getNext();
			}
			if(current.getElement().equals(element))
				previous.setNext(current.getNext());
		}
	}
	
	//Remove all elements of the list
	public void removeAllElements() {
    	if(head == null) {
			//Do nothing
		} else {
			head = null;
		}
    }
	
	//Retrieve methods
	//Retrieve node at given index
	private Node<T> retrieveNode(int index) {
		if(index < 0 || index > this.getSize()) {
			throw new IndexOutOfBoundsException("Index value provided is out of bounds.");
		}
		
		Node<T> current = head;
		for(int i=0; i<index; i++) {
			current = current.getNext();
		}
		return current;
	}
	
	//Retrieve element at given index
	public T retrieveElement(int index) {
		if(index < 0 || index > (this.getSize() - 1)) {
			throw new IndexOutOfBoundsException("Index value provided is out of bounds.");
		}
		
		Node<T> theNode = this.retrieveNode(index);
		return theNode.getElement();
	}
	
	//Supporting methods
	//Determine the size (number of elements) of the list
	public int getSize() {
		Node<T> current = head;
		int counter = 0;
		while(current != null) {
			current = current.getNext();
			counter++;
		}
		return counter;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Node<T> current = head;
			
			@Override
			public boolean hasNext() {
				if(current == null)
					return false;
				else
					return true;
			}
			
			@Override
			public T next() {
				T element = current.getElement();
				current = current.getNext();
				return element;
			}
		};
	}

	@Override
	public String toString() {
		if(this.head == null) {
			return "[none]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		Node<T> current = head;
		while(current.hasNext()) {
			sb.append(current.getElement());
			sb.append(", ");
			current = current.getNext();
		}
		sb.append(current.getElement());
		sb.append("]");
		return sb.toString();
	}

}
