package practice2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 서로소집합 {
	static int N, M;
	static int[] parents;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		int a, b, o;

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			sb.append("#").append(t).append(" ");
			parents = new int[N + 1];
			Arrays.fill(parents, -1);

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				o = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());

				if (o == 0) {
					union(a, b);
				} else {
					if (findSet(a) == findSet(b)) {
						sb.append(1);
					} else {
						sb.append(0);
					}
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int findSet(int a) {
		if(parents[a] < 0)
			return a;
		else
			return parents[a] = findSet(parents[a]);
	}

	private static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot != bRoot) {
			parents[bRoot] = aRoot;
		}
	}
}
