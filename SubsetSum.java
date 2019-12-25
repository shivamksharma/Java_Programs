import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.math.BigInteger;
class Main
{
	static boolean subsetSum(int a[],int n,int sum)
	{
		if(sum==0)
		return true;
		if(n==0&&sum!=0)
		return false;
		if(a[n-1]>sum)
		return subsetSum(a,n-1,sum);
		return subsetSum(a,n-1,sum)||subsetSum(a,n-1,sum-a[n-1]);
	}
	public static void main(String[] args)throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int a[]={1,5,2,4,3};
		System.out.println(subsetSum(a,a.length,154));
	}
}
