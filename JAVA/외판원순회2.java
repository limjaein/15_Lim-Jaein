package practice2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 외판원순회2 {
	static int N;
	static int[][] weight;
	static int min_result = 987654321;
	static boolean[] isVisited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(in.readLine());
		weight = new int[N][N];
		isVisited = new boolean[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				weight[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			isVisited[i] = true;
			move(i, 0, 0, i);
			isVisited[i] = false;
		}
		System.out.println(min_result);
	}

	private static void move(int idx, int cnt, int sum, int ret) {
		if (cnt == N - 1 && weight[idx][ret] != 0) {
			min_result = Math.min(min_result, sum + weight[idx][ret]);
			return;
		}

		if (idx == N - 1) {
			return;
		}

		for (int i = 0; i < N; i++) {
			if (isVisited[i])
				continue;
			if (weight[idx][i] != 0) {
				isVisited[i] = true;
				move(i, cnt + 1, sum + weight[idx][i], ret);
				isVisited[i] = false;
			}
		}
	}

}
