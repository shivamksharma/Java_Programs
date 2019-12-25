import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
/**Public the class and change the file name*/
class Main
{
	public static final int Max=1000000000;
	/**Calculate the minimum value b/w "x" and "y".*/
	public static long minVal(long x,long y)
	{
		return (x<y)?x:y;
	}
	/**Calculate the mid value of "s" and "e"*/
	public static int getMid(int s ,int e)
	{
		return s+(e-s)/2;
	}
	/**Range Minimum Query Utilization is method that recursively call itself
	to find the minimum value b/w query start and ending*/
	public static long RMQUtil(long[] st,int ss,int se,int qs,int qe,int index)
	{
		if(qs<=ss && qe>=se)
		return st[index];
		if(qs>se || qe<ss)
		return Max;
		int mid=getMid(ss,se);
		return minVal(RMQUtil(st, ss, mid, qs, qe, 2*index+1),RMQUtil(st, mid+1, se, qs, qe, 2*index+2));
	}
	/**Call the RMQUtil method with one exception*/
	public static long RMQ(long[] st,int n,int qs,int qe)
	{
		if(qs>qe||qs<0||qe>n-1)
		return -1;
		return RMQUtil(st,0,n-1,qs,qe,0);
	}
	/**Construct the Segment Tree by recursively calling of itself*/
	public static long constructSTUtil(long[] arr,int ss,int se,long[] st,int si)
	{
		if (ss == se)
		{
			st[si] = arr[ss];
			return arr[ss];
		}
		int mid = getMid(ss, se);
		st[si] =  minVal(constructSTUtil(arr, ss, mid, st, si*2+1),constructSTUtil(arr, mid+1, se, st, si*2+2));
		return st[si];
	}
	/**Call the ConstructUtil method by dynamically allocating the array size 
	for an array*/
	static void updateValueUtil(long[] st, int ss, int se, int i, long diff, int index)
	{
		if (i < ss || i > se)
		{
			return;
		}
		st[index] = st[index] + diff;
		if (se != ss)
		{
			int mid = getMid(ss, se);
			updateValueUtil(st, ss, mid, i, diff, 2*index + 1);
			updateValueUtil(st, mid+1, se, i, diff, 2*index + 2);
		}
	}
	static void updateValue(long arr[], long[] st, int n, int i, long new_val)
	{
		if (i < 0 || i > n-1)
		{
			return;
		}
		long diff = new_val - arr[i];
		arr[i] = new_val;
		updateValueUtil(st, 0, n-1, i, diff, 0);
	}
	public static long[] constructST(long arr[],int n)
	{
		int x = (int)(Math.log(n)/Math.log(2))+1;
		int max_size = 2*(int)Math.pow(2, x) - 1;
		long st[]=new long[max_size];
		constructSTUtil(arr, 0, n-1, st, 0);
		return st;
	}
	/**Main method which directly understand by JVM*/
	public static void main(String args[])throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter the size of Array:");
		int n=Integer.parseInt(br.readLine());
		String s=br.readLine();
		long array[]=new long[n];
		int i=0;
		for(String str:s.split(" "))
		array[i++]=Integer.parseInt(str);
		long st[]=constructST(array,n);
		while(true)
		{
			System.out.print("Enter 1 for \'Update\' and 2 for finding \'Minimum\':");
			int flag=Integer.parseInt(br.readLine());
			if(flag==1)
			{
				System.out.print("Enter the query \'in the form\' of two spaced number:");
				String str[]=br.readLine().split(" ");
				int qs=Integer.parseInt(str[0]);
				int qe=Integer.parseInt(str[1]);
				System.out.println("Minimum value in range["+qs+","+qe+"] is ="+RMQ(st,n,qs,qe));
			}
			else if(flag==0)
			{
				System.out.print("Enter the index of element you want to update(1-indexed array) and value in between with space:");
				String str[]=br.readLine().split(" ");
				int index=Integer.parseInt(str[0]);
				int value=Integer.parseInt(str[1]);
				updateValue(array,st,n,index,value);
			}
		}
	}
}
