package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정사각형방 {
	static int N;
	static int[][] room;
	static int[][] dp;
	static int num;
	static int max_cnt;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		String token = " ";

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(in.readLine());
			room = new int[N][N];
			dp = new int[N][N];
			num = N * N;
			max_cnt = -1;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), token);
				for (int j = 0; j < N; j++) {
					room[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (dp[i][j] == 0) {
						dp[i][j] = dfs(i, j, 1);
						if (max_cnt < dp[i][j]) {
							max_cnt = dp[i][j];
						}
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (max_cnt == dp[i][j]) {
						num = Math.min(num, room[i][j]);
					}
				}
			}
			sb.append("#").append(t + 1).append(" ").append(num).append(" ").append(max_cnt).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int dfs(int y, int x, int cnt) {
		int ny, nx;
		int result = cnt;

		if (dp[y][x] != 0)
			return dp[y][x];

		for (int i = 0; i < 4; i++) {
			ny = y + dy[i];
			nx = x + dx[i];
			if (ny < 0 || nx < 0 || ny >= N || nx >= N || room[y][x] != room[ny][nx] - 1) {
				result = Math.max(result, cnt);
				continue;
			}
			result = Math.max(result, cnt + dfs(ny, nx, 1));
		}
		return result;
	}

}