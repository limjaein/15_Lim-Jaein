package HW;

import java.util.Arrays;
import java.util.Scanner;

public class 주사위던지기2 {
	final static int MAX = 6;
	static int N;
	static int M;
	static int[] list;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		list = new int[N];
		findComb(0);
	}

	private static void findComb(int idx) {
		int sum = 0;

		if (idx == N) {
			for (int i = 0; i < N; i++)
				sum += list[i];
			if (sum == M) {
				for (int i = 0; i < N; i++)
					System.out.print(list[i]+" ");
				System.out.println();
			}
			return;
		}

		for (int i = 1; i < MAX + 1; i++) {
			list[idx] = i;
			findComb(idx + 1);
		}
	}

}
