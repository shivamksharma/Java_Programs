import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
class Main
{
  static int allFactors(long n,long array[])		//Stores the all factors in array and returning the index+1(one greater the last)
	{
		int j=0;
		array[j++]=1;
		long i;
		for(i=2;i<Math.sqrt(n);i++)
		{
			if(n%i==0)
			{
				array[j++]=i;
				array[j++]=n/i;
			}
		}
		if(i*i==n)
		array[j++]=i;
		array[j++]=n;
		return j;
	}
	public static void main(String[] args)throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter the number of which factors you have to calculate:");
		long n=Long.parseLong(br.readLine());
		System.out.println("All Divisors/Factors of a given numbers are below:");
		long array[]=new long[200];		//Assuming factors to be less than or equal to 200
		int index=allFactors(n,array);
		Arrays.sort(array,0,index);
		for(int i=0;i<index;i++)		//Factors in increasing order
		System.out.println(array[i]);
		System.out.println("Number of factors are:"+index);
	}
}
