package algo_200314;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 파핑파핑지뢰찾기 {
	static int N, total_cnt;
	static int[][] map;
	static boolean[][] isPossible;
	static boolean[][] isVisited;
	static int min_result;
	static int[] dy = { -1, -1, 1, 1, 0, 0, 1, -1 };
	static int[] dx = { 1, -1, 1, -1, 1, -1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String input;
		int T = Integer.parseInt(in.readLine());
		int click_cnt;

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(in.readLine());
			map = new int[N][N];
			isVisited = new boolean[N][N];
			isPossible = new boolean[N][N];
			total_cnt = 0;
			click_cnt = 0;
			min_result = N * N;

			for (int i = 0; i < N; i++) {
				input = in.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = input.charAt(j);
					if (map[i][j] == '.') {
						total_cnt++;
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					isPossible[i][j] = isNotRounded(i, j);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '.' && !isVisited[i][j] && isPossible[i][j]) {
						isVisited[i][j] = true;
						total_cnt -= click(i, j);
						click_cnt++;
					}
				}
			}
			sb.append("#").append(t + 1).append(" ").append(total_cnt + click_cnt).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static boolean isNotRounded(int y, int x) {
		int ny, nx;
		for (int i = 0; i < 8; i++) {
			ny = y + dy[i];
			nx = x + dx[i];
			if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
				continue;
			}
			if (map[ny][nx] == '*')
				return false;
		}
		return true;
	}

	private static int click(int y, int x) {
		Queue<pair> q = new LinkedList<>();
		int cnt = 1;
		int ny, nx;
		pair cur;
		q.offer(new pair(y, x));

		while (!q.isEmpty()) {
			cur = q.poll();
			for (int i = 0; i < 8; i++) {
				ny = cur.y + dy[i];
				nx = cur.x + dx[i];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N || isVisited[ny][nx] || !isPossible[cur.y][cur.x] || map[ny][nx] == '*') {
					continue;
				}
				isVisited[ny][nx] = true;
				q.offer(new pair(ny, nx));
				cnt++;
			}
		}
		return cnt; // 클릭 함으로써 오픈할 수 있는 칸의 수 리턴
	}

}

class pair {
	int y;
	int x;

	pair(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
