package listClasses;

import java.util.*;


/**
 * Implements a generic sorted list using a provided Comparator. It extends
 * BasicLinkedList class.
 * 
 *  @author Dept of Computer Science, UMCP
 *  
 */

public class SortedLinkedList<T> extends BasicLinkedList<T> {
	private Comparator<T> comparator;
	
	public SortedLinkedList(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	public SortedLinkedList<T> add(T element) {
		if (head == null) {
			head = new Node(element);
			tail = head;
		} else if (comparator.compare(element, head.data) < 0) {
			Node newNode = new Node(element);
			newNode.next = head;
			head = newNode;
		} else {
			Node curr = head;
			Node newNode = new Node(element);
			
			while(curr.next != null && comparator.compare(element, curr.next.data) > 0) {
				curr = curr.next;
			}
			newNode.next = curr.next;
			curr.next = newNode;
			if(newNode.next == null) {
				tail = newNode;
			}
		}
		listSize++;
		return this;
	}
	
	public SortedLinkedList<T> remove(T targetData) {
		super.remove(targetData, comparator);
		return this;
	}
	
	public BasicLinkedList<T> addToEnd(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	public BasicLinkedList<T> addToFront(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}

}	