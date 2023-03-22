package listClasses;

import java.util.*;


public class BasicLinkedList<T> implements Iterable<T> {
	
	/* Node definition */
	protected class Node {
		protected T data;
		protected Node next;

		protected Node(T data) {
			this.data = data;
			next = null;
		}
	}

	/* We have both head and tail */
	protected Node head, tail;
	
	/* size */
	protected int listSize;
	
	public BasicLinkedList() {
		head = null;
		tail = null;
		
	}
	
	public int getSize() {
		return listSize;
	
	}
	
	public BasicLinkedList<T> addToEnd(T data) {
		Node curr = head;
		
		if(curr == null) {
			head = new Node(data);
			tail = head;
		} else {
			tail.next = new Node(data);
			tail = tail.next;
		}
		listSize++;
		return this;
	}
	
	public BasicLinkedList<T> addToFront(T data) {
		if(head == null) {
			head = new Node(data);
			tail = head;
		} else {
			Node newNode = new Node(data);
			newNode.next = head;
			head = newNode;
		}
		listSize++;
		
		return this;
	}
	
	public T getFirst() {
		if(head == null) {
			return null;
		} else {
			return head.data;
		}
	}
	
	public T getLast() {
		if(head == null) {
			return null;
		} else {
			return tail.data;
		}
	}
	
	public T retrieveFirstElement() {
		if(head == null) {
			return null;
		}
		Node newNode = new Node(head.data);
		head = head.next;
		listSize--;
		
		return newNode.data;
	}
	
	public T retrieveLastElement() {
		if(head == null) {
			return null;
		}
		Node prev1 = null, prev2 = null, curr = head;
		
		while(curr != null) {
			prev1 = prev2;
			prev2 = curr;
			curr = curr.next;
		}
		Node newNode = new Node(prev2.data);
		tail = prev1;
		tail.next = null;
		listSize--;
		
		return newNode.data;
		
	}
	
	public BasicLinkedList<T> remove(T targetData, Comparator<T> comparator) {
		Node prev = null, curr = head;
		while(curr != null) {
			if(comparator.compare(curr.data, targetData) == 0) {
				if(prev == null) {
					head = head.next;
				} else {
					prev.next = curr.next;
				}
				listSize--;
			} else {
				prev = curr;
			}
			curr = curr.next;
		}
		return this;
	}
	@Override
	public Iterator<T> iterator() {
		Iterator<T> myIterator = new Iterator<T>() {
			Node curr = head;

			public boolean hasNext() {
				return curr != null;
			}

			public T next() {
				T toReturn = curr.data;

				curr = curr.next;
				return toReturn;
			}
		};
		
		return myIterator;
	}
	
	public ArrayList<T> getReverseArrayList() {
		BasicLinkedList<T> newList = getReverseList();
		ArrayList<T> reversedList = new ArrayList<>();
		
		reverseArrayAuxiliary(newList.head, reversedList);
		return reversedList;
	}
	
	private void reverseArrayAuxiliary(Node headAux, ArrayList<T> answer) {
		if(headAux != null) {
			answer.add(headAux.data);
			reverseArrayAuxiliary(headAux.next, answer);
		}
	}
	
	public BasicLinkedList<T> getReverseList() {
		BasicLinkedList<T> newList = new BasicLinkedList<T>();
		return reverseListAuxiliary(head, newList);
	}
	
	private BasicLinkedList<T> reverseListAuxiliary(Node headAux, BasicLinkedList<T> list) {
		if(headAux == null) {
			return list;
		} else {
			list.addToFront(headAux.data);
			return reverseListAuxiliary(headAux.next, list); 
		}
	}
}