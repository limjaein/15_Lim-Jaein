package practice2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 종교 {
	static int N, M;
	static int[] religion;
	static boolean[] check;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int a, b;

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		religion = new int[N + 1];
		check = new boolean[N + 1];

		for (int i = 1; i < N + 1; i++) {
			religion[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			union(a, b);
		}

		System.out.println(count());
	}

	private static int count() {
		int cnt = 0;
		for (int i = 1; i < N + 1; i++) {
			if (religion[i] == i) {
				cnt++;
			}
		}
		return cnt;
	}

	private static int getReligion(int a) {
		if (religion[a] == a) {
			return a;
		} else {
			int r = getReligion(religion[a]);
			religion[a] = r;
			return r;
		}
	}

	private static void union(int a, int b) {
		int aRoot = getReligion(a);
		int bRoot = getReligion(b);
		if (aRoot < bRoot) {
			religion[bRoot] = aRoot;
		}else {
			religion[aRoot] = bRoot;
		}
	}

}
