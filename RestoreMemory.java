package test;

import java.util.Scanner;

public class RestoreMemory {

	public static int restore(String str)
	{
		int cnt = 0;
		char prev = '0';
		char c;
		
		for (int i = 0; i < str.length(); i++)
		{
			c = str.charAt(i);
			if (c != prev)
				cnt++;
			prev = c;
		}
		
		return cnt;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		String str;
		
		T = sc.nextInt();
		int[] result = new int[T];
		
		sc.nextLine();
		
		for (int i = 0; i < T; i++)
		{
			str = sc.nextLine();
			result[i] = restore(str);
		}

		for (int i = 0; i < T; i++)
			System.out.println("#"+(i+1)+" "+result[i]);
		sc.close();
		return;
	}

}
