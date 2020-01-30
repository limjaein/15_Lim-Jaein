package HW;

import java.util.Scanner;

public class 주사위던지기1 {
	final static int MAX = 6;
	static int N;
	static int[] list;
	static boolean[] isContained;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M;

		N = sc.nextInt();
		M = sc.nextInt();

		list = new int[N];
		isContained = new boolean[MAX];
		findComb(0, M);
	}

	private static void findComb(int idx, int type) {

		if (idx == N) {
			for (int i = 0; i < N; i++)
				System.out.print(list[i] + " ");
			System.out.println();
			return;
		}

		for (int i = 1; i < MAX + 1; i++) {
			if (!isContained[i - 1]) {
				if (type == 2 && idx > 0 && i < list[idx - 1])
					continue;
				
				list[idx] = i;

				if (type == 3)
					isContained[i - 1] = true;

				findComb(idx + 1, type);

				if (type == 3)
					isContained[i - 1] = false;
			}
		}
	}
}
