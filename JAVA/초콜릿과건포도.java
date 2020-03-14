package algo_200313;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 초콜릿과건포도 {
	static int n, m;
	static int[][] choco;
	static int[][][][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			dp = new int[n][m][n + 1][m + 1];
			choco = new int[n][m];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < m; j++) {
					choco[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append("#").append(t + 1).append(" ").append(dfs(0, 0, n, m)).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int dfs(int y, int x, int h, int w) {
		if (h == 1 && w == 1) {
			return 0;
		}
		if (dp[y][x][h][w] != 0) {
			return dp[y][x][h][w];
		}

		int result = Integer.MAX_VALUE;
		int sum = 0;
		for (int i = y; i < y + h; i++) {
			for (int j = x; j < x + w; j++) {
				sum += choco[i][j];
			}
		}

		for (int i = 1; i < h; i++) {
			result = Math.min(result, sum + dfs(y, x, i, w) + dfs(y + i, x, h - i, w));
		}
		for (int i = 1; i < w; i++) {
			result = Math.min(result, sum + dfs(y, x, h, i) + dfs(y, x + i, h, w - i));
		}

		dp[y][x][h][w] = result;
		return result;
	}
}
