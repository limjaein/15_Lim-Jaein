package algo_200311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 디저트카페 {
	static int N;
	static int[][] map;
	static boolean[] isVisited;
	static int[] dy = { -1, 1, 1, -1 };
	static int[] dx = { 1, 1, -1, -1 };
	static int max_cnt;
	static int sy, sx;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(in.readLine());
			map = new int[N][N];
			isVisited = new boolean[101];
			max_cnt = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 1; i < N; i++) {
				for (int j = 0; j < N; j++) {
					isVisited[map[i][j]] = true;
					sy = i;
					sx = j;
					dfs(i, j, 1, -1, 0);
					isVisited[map[i][j]] = false;
					for(int k=0;k<101;k++) {
						if(isVisited[k]) {
							System.out.println("악");
						}
					}
				}
			}
			sb.append("#").append(t + 1).append(" ").append((max_cnt != 0) ? max_cnt : -1).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int y, int x, int cnt, int dir, int rotate) {
		int ny, nx;
		int n_dir;

		if (dir == -1) { // 시작점
			for (int i = 0; i < 4; i++) {
				ny = y + dy[i];
				nx = x + dx[i];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N || isVisited[map[ny][nx]]) {
					continue;
				}
				isVisited[map[ny][nx]] = true;
				dfs(ny, nx, cnt + 1, i, rotate);
				isVisited[map[ny][nx]] = false;
			}
			return;
		}

		for (int i = 0; i < 2; i++) {
			n_dir = dir + i;
			n_dir %= 4;
			ny = y + dy[n_dir];
			nx = x + dx[n_dir];
			if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
				continue;
			}
			if (ny == sy && nx == sx) { // 시작점이면
				max_cnt = Math.max(max_cnt, cnt);
				return;
			}
			if (isVisited[map[ny][nx]]) { // 이미 먹어 본 디저트이면
				continue;
			}
			if (i != 0 && (rotate + i) == 4) { // 사각형 꼭지점 오류
				return;
			}
			isVisited[map[ny][nx]] = true;
			dfs(ny, nx, cnt + 1, n_dir, rotate + i);
			isVisited[map[ny][nx]] = false;
		}
	}
}