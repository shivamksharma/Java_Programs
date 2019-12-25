import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
class Node
{
	int data;
	Node next;
	public Node(int data)
	{
		this.data=data;
	}
}
class Main
{
	static Node head=null;
	static Node ptr=null;
	static private int size=0;
	static void add(int item)
	{
		if(ptr==null)
		{
			Node node=new Node(item);
			ptr=node;
			head=ptr;
			ptr.next=null;
		}
		else
		{
			Node node=new Node(item);
			if(ptr.next==null)
			{
				ptr.next=node;
				ptr=node;
				ptr.next=null;
			}
		}
		size++;
	}
	static void printList(Node head)
	{
		Node temp=head;
		while(temp!=null)
		{
			System.out.print(temp.data+"->");
			temp=temp.next;
		}
	}
	static int remove(int pos)
	{
		if(pos>size)
		System.out.println("Position Invalid");
		Node temp=head;
		for(int i=1;i<pos-1;i++)
		{
			temp=temp.next;
		}
		Node n=temp.next.next;
		temp.next=n;
		return temp.next.data;
	}
	static void reverse()
	{
		Node temp=head,n=null;
		Node curr=null,prev=null;
		while(temp!=null)
		{
			n=temp.next;
			curr=temp;
			if(temp==head)
			{
				temp.next=null;
				prev=temp;
			}
			else
			{
				temp.next=prev;
				prev=temp;
			}
			temp=n;
		}
		head=curr;
	}
	public static void main(String[] args)throws IOException
	{
		add(1);
		add(2);
		add(3);
		add(11);
		add(21);
		printList(head);
		System.out.println();
		reverse();
		printList(head);
	}
}