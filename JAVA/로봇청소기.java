package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 로봇청소기 {

	static final int UP = 0;
	static final int RIGHT = 1;
	static final int DOWN = 2;
	static final int LEFT = 3;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static boolean[][] isCleaned;
	static int[][] arr;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int y, x, dir;
		int cnt = 0;

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		isCleaned = new boolean[N][M];

		st = new StringTokenizer(in.readLine(), " ");
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		dir = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		cleanRoom(y, x, dir);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (isCleaned[i][j])
					cnt++;
			}
		}

		System.out.println(cnt);
	}

	private static void cleanRoom(int y, int x, int dir) {
		int n_y, n_x;

		if (!isCleaned[y][x]) // 1
			isCleaned[y][x] = true;
		else
			return;

		for (int r = 0; r < 5; r++) {
			//왼쪽방향
			if (dir == UP)
				dir = LEFT;
			else
				dir -= 1;

			n_y = y + dy[dir];
			n_x = x + dx[dir];

			if (arr[n_y][n_x] == 0 && !isCleaned[n_y][n_x]) { // 2-a
				cleanRoom(n_y, n_x, dir);
				return;
			} else {
				if (r == 4) {
					n_y = y - dy[dir];
					n_x = x - dx[dir];
					if (arr[n_y][n_x] == 0) { // 2-c
						r = 0;
						y = n_y;
						x = n_x;
						continue;
					} else // 2-d
						return;
				} else // 2-b
					continue;
			}
		}
	}
}
