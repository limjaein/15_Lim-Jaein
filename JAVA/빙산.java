package algo_200308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ºù»ê {
	static int N, M;
	static int[][] map;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean[][] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(melt());
	}

	private static int melt() {
		int year = 0;
		int side, ny, nx;
		int[][] newMap;

		while (true) {
			year++;
			newMap = new int[N][M];
			if (!isRemained()) {
				return 0;
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] != 0) {
						side = 0;
						for (int k = 0; k < 4; k++) {
							ny = i + dy[k];
							nx = j + dx[k];
							if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] != 0) {
								continue;
							}
							side++;
						}
						newMap[i][j] = map[i][j] - side;
						if (newMap[i][j] < 0) {
							newMap[i][j] = 0;
						}
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = newMap[i][j];
				}
			}
			if (isFinished()) {
				return year;
			}
		}
	}

	private static boolean isFinished() {
		isVisited = new boolean[N][M];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0 && !isVisited[i][j]) {
					if (cnt != 0) {
						return true;
					}
					isVisited[i][j] = true;
					dfs(i, j);
					cnt++;
				}
			}
		}
		return false;
	}

	private static void dfs(int y, int x) {
		int ny, nx;

		for (int i = 0; i < 4; i++) {

			ny = y + dy[i];
			nx = x + dx[i];
			if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == 0 || isVisited[ny][nx]) {
				continue;
			}
			isVisited[ny][nx] = true;
			dfs(ny, nx);
		}
	}

	private static boolean isRemained() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					return true;
				}
			}
		}
		return false;
	}
}
