package algo_200303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 내리막길 {
	static int N, M;
	static int cnt;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] input;
	static int[][] isPossible; // 0은 안간곳, -1은 간곳.안됨, 1은 간곳.됨

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		cnt = 0;
		st = new StringTokenizer(in.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		input = new int[M][N];
		isPossible = new int[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		isPossible[M - 1][N - 1] = 1;
		dfs(0, 0);
		if(isPossible[0][0] == -1) {
			isPossible[0][0] = 0;
		}
		System.out.println(isPossible[0][0]);
	}

	private static int dfs(int y, int x) {
		int count = 0;
		int ret = 0;

		if (isPossible[y][x] > 0) {
			return isPossible[y][x];
		}
		int ny, nx;
		for (int i = 0; i < 4; i++) {
			ny = y + dy[i];
			nx = x + dx[i];
			if (ny < 0 || nx < 0 || ny >= M || nx >= N || input[ny][nx] >= input[y][x] || isPossible[y][x] == -1) {
				continue;
			}
			ret = dfs(ny, nx);
			if (ret != -1) {
				count += ret;
			}
		}

		if (count == 0) {
			isPossible[y][x] = -1;
			return -1;
		} else {
			isPossible[y][x] = count;
			return count;
		}
	}
}
