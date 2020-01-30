package test;

import java.util.ArrayList;
import java.util.Scanner;

public class LongestSubsequences {
	public static int[] list;
	public static int[] result;
	
	public static int lower_bound(ArrayList<Integer> slist, int num)
	{
		int s,e,m;
		s = 0;
		e = slist.size();
		while(s<e)
		{
			m = (s+e)/2;
			if(slist.get(m) < num)
				s = m+1;
			else
				e = m;
		}
		return s;
	}
	
	public static void solution(int tc)
	{
		ArrayList<Integer> sorted_list = new ArrayList<>();
		int ind;
		int num;
		
		for(int i=0;i<list.length;i++)
		{
			num = list[i];
			ind = lower_bound(sorted_list, num);

			if(sorted_list.size() == 0 || ind > sorted_list.size() - 1)
				sorted_list.add(num);
			else
				sorted_list.set(ind, num);
		}

		for(int i=0;i<sorted_list.size();i++)
		{
			System.out.println(sorted_list.get(i)+" ");
		}
		result[tc] = sorted_list.size();
		return;
	}
	
	public static void main(String[] args) {
		int T, N, tmp;
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		result = new int[T];

		for (int i = 0; i < T; i++)
		{
			N = sc.nextInt();
			sc.nextLine();
			list = new int[N]; // 초기화
			for (int j = 0; j < N; j++)
			{
				tmp = sc.nextInt();
				list[j] = tmp;
			}
			solution(i);
		}
		sc.close();
		for (int i = 0; i < T; i++)
			System.out.println("#"+(i+1)+" "+result[i]);
	}

}
