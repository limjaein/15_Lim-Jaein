package algo_200310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 성곽 {
	static int n, m;
	static int[][] map;
	static boolean[][] isVisited;
	static int[] dy = { 0, -1, 0, 1 };
	static int[] dx = { -1, 0, 1, 0 };
	static int room_cnt, max_cnt, extend_cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		isVisited = new boolean[n][m];
		room_cnt = 0;
		max_cnt = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!isVisited[i][j]) {
					isVisited[i][j] = true;
					max_cnt = Math.max(max_cnt, dfs(i, j));
					room_cnt++;
				}
			}
		}
		extend_cnt = max_cnt;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] != 0) {
					isVisited = new boolean[n][m];
					isVisited[i][j] = true;
					for (int k = 0; k < 4; k++) {
						if ((map[i][j] & (1 << k)) != 0) { // 벽일 때
							map[i][j] &= ~(1 << k); // 벽 제거
							extend_cnt = Math.max(extend_cnt, dfs(i, j));
							map[i][j] |= (1 << k); // 다시 생성
						}
					}
				}
			}
		}
		System.out.println(room_cnt);
		System.out.println(max_cnt);
		System.out.println(extend_cnt);
	}

	private static int dfs(int y, int x) {
		int ny, nx;
		int result = 0;

		for (int i = 0; i < 4; i++) {
			ny = y + dy[i];
			nx = x + dx[i];
			if (ny < 0 || nx < 0 || ny >= n || nx >= m || isVisited[ny][nx]) {
				continue;
			}
			if ((map[y][x] & (1 << i)) == 0) { // 벽 없을 때
				isVisited[ny][nx] = true;
				result += dfs(ny, nx);
			}
		}
		return 1 + result;
	}
}
