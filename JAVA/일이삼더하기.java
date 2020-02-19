package practice2;

import java.util.Scanner;

public class 일이삼더하기 {
	static int N;
	static int[] num = { 1, 2, 3 };
	static int cnt;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		StringBuilder sb = new StringBuilder();
		T = sc.nextInt();
		for (int i = 0; i < T; i++) {
			cnt = 0;
			N = sc.nextInt();
			count(0);
			sb.append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void count(int sum) {
		if (sum > N)
			return;
		else if (sum == N) {
			cnt++;
			return;
		}

		for (int i = 0; i < 3; i++) {
			count(sum + num[i]);
		}
	}

}
