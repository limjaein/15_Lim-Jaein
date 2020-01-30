package HW;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class 가능한시험점수 {
	final static int MAX = 10001;
	static int N;
	static int[] score;
	static boolean[] count;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		int result;
		ArrayList<Integer> idxes = new ArrayList<>();
		count = new boolean[MAX];

		T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			N = sc.nextInt();
			score = new int[N];
			result = 0;
			Arrays.fill(count, false);
			count[0] = true;

			for (int i = 0; i < N; i++)
				score[i] = sc.nextInt();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < MAX; j++) {
					if (count[j] && (j + score[i] < MAX - 1))
						idxes.add(j + score[i]);
				}
				for (int j = 0; j < idxes.size(); j++)
					count[idxes.get(j)] = true;
				idxes.clear();
			}

			for (int i = 0; i < MAX; i++) {
				if (count[i])
					result++;
			}
			System.out.println("#" + (t + 1) + " " + result);
		}
	}

}
