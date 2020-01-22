package test;

import java.util.ArrayList;
import java.util.Scanner;

public class HamburgerDiet {
	public static int maxCal;
	public static ArrayList<Ingred> list;
	
	public static int findBestBurger(int ind, int sumCal, int sumFlavor)
	{
		int result = 0;
		
		if (sumCal > maxCal)
			return sumFlavor - list.get(ind - 1).flavor;
		if (ind == list.size() || sumCal == maxCal)
			return sumFlavor;

		result = Math.max(findBestBurger(ind + 1, sumCal, sumFlavor), findBestBurger(ind + 1, sumCal + list.get(ind).cal, sumFlavor + list.get(ind).flavor));

		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int T, N;
		int f, c;
		int[] result;
		
		list = new ArrayList<>();

		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		sc.nextLine();
		result = new int[T];
		
		for (int i = 0; i < T; i++)
		{
			N = sc.nextInt();
			maxCal = sc.nextInt();
			sc.nextLine();
			for (int j = 0; j < N; j++)
			{
				f = sc.nextInt();
				c = sc.nextInt();
				list.add(new Ingred(f, c));
			}
			result[i] = findBestBurger(0, 0, 0);

			list.clear();
		}
		
		sc.close();
		
		for (int i = 0; i < T; i++)
			System.out.println("#"+(i+1)+" "+result[i]);
	}
}
class Ingred{
	int flavor;
	int cal;
	Ingred(int f, int c){flavor = f; cal = c;}
}