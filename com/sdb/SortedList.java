package com.sdb;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import com.sdb.Node;

public class SortedList<T> implements Iterable<T> {
	
	private Node<T> head = null;
	private Comparator<T> comparator = null;

	public SortedList(Node<T> head, Comparator<T> comparator) {
		this.head = head;
		this.comparator = comparator;
	}
	
	//Insert methods
	//This method determines where to insert the new element to maintain sorted list, then uses an insert function at that position
	public void insert(T element) {
		int index = 0;
		
		if(head == null) {
			//Nothing in list; element is inserted at start
			insertElementAtStart(element);
			return;
		} else {
			Node<T> current = head;
			
			//The element is less than the head element; element is inserted at start
			if(comparator.compare(current.getElement(), element) > 0) {
				insertElementAtStart(element);
				return;
			//The element is equal to the head element; element is inserted following head element
			} else if(comparator.compare(current.getElement(), element) == 0) {
				index = 1;
				insertElementAtIndex(element, index);
				return;
			} else {
				//Go through the linked list.  If the element is greater than the current element, nothing happens
				//If the element is equal to the current element, it is inserted following the current element
				//If the element is less than the current element, it is inserted in the current element's position
				for(int i = 1; i < getSize(); i++) {
					current = current.getNext();
					//The element is equal to the current element, so it is inserted following the current element
					if(comparator.compare(current.getElement(), element) == 0) {
						index = i + 1;
						insertElementAtIndex(element, index);
						return;
					//The element is less than the current element, so it is inserted in the current element's position
					} else if(comparator.compare(current.getElement(), element) > 0) {
						index = i;
						insertElementAtIndex(element, index);
						return;
					}
				}
				//At this point, the element is greater than all elements of the list, and is inserted at the end
				insertElementAtEnd(element);
				return;
			}
		}
	}
	
	//This method inserts all of the elements in an ArrayList
	public void insert(ArrayList<T> elementList) {
		
		for(int i = 0; i < elementList.size(); i++) {
			insert(elementList.get(i));
		}
	}
	
	//Insert element at start of list
	private void insertElementAtStart(T element) {
		Node<T> newNode = new Node<T>(element);
		newNode.setNext(this.head);
		this.head = newNode;
	}
	
	//Insert element at given index of list
	private void insertElementAtIndex(T element, int index) {
		if(index < 0 || index > this.getSize()) {
			throw new IndexOutOfBoundsException("Index value provided is out of bounds.");
		}
		
		if(index == this.getSize()) {
			this.insertElementAtEnd(element);
			return;
		} else if(index == 0) {
			this.insertElementAtStart(element);
			return;
		} else {
			Node<T> node = this.getNode(index - 1);
			Node<T> newNode = new Node<T>(element);
			newNode.setNext(node.getNext());
			node.setNext(newNode);
			return;
		}
	}
	
	//Insert element at end of list
	private void insertElementAtEnd(T element) {
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
	private Node<T> getNode(int index) {
		if(index < 0 || index > this.getSize()) {
			throw new IndexOutOfBoundsException("Index value provided is out of bounds.");
		}
		
		Node<T> current = head;
		for(int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current;
	}
	
	//Retrieve element at given index
	public T get(int index) {
		if(index < 0 || index > (this.getSize() - 1)) {
			throw new IndexOutOfBoundsException("Index value provided is out of bounds.");
		}
		
		Node<T> theNode = this.getNode(index);
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

	//Iterator method
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

	//ToString method
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
	
	//Print method
	public void print() {
    	if(this.head == null) {
			System.out.print("[none]\n");
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			Node<T> current = head;
			while(current.getNext() != null) {
				sb.append(current.getElement());
				sb.append(", ");
				current = current.getNext();
			}
			sb.append(current.getElement());
			sb.append("]\n");
			System.out.print(sb.toString());
		}
    }

}
