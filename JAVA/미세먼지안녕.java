package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미세먼지안녕 {
	static int R, C, T;
	static int[][] room;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int cy, cx;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		boolean flag = false;
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		room = new int[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < C; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if (room[i][j] == -1 && !flag) { // 공청기 첫 위치 저장
					cy = i;
					cx = j;
					flag = true;
				}
			}
		}
		for (int i = 0; i < T; i++) {
			spread();
			clean();
		}
		System.out.println(sum());
	}

	private static void clean() {
		int y, x;
		Queue<Integer> q = new LinkedList<>();
		// UP
		y = cy;
		x = cx;
		q.offer(0);

		while (x++ < C - 1) {
			q.offer(room[y][x]);
			room[y][x] = q.poll();
		}
		x--;
		while (y-- > 0) {
			q.offer(room[y][x]);
			room[y][x] = q.poll();
		}
		y++;
		while (x-- > 0) {
			q.offer(room[y][x]);
			room[y][x] = q.poll();
		}
		x++;
		while (y++ < cy - 1) {
			q.offer(room[y][x]);
			room[y][x] = q.poll();
		}

		q.clear();

		// DOWN
		y = cy + 1;
		x = cx;
		q.offer(0);

		while (x++ < C - 1) {
			q.offer(room[y][x]);
			room[y][x] = q.poll();
		}
		x--;
		while (y++ < R - 1) {
			q.offer(room[y][x]);
			room[y][x] = q.poll();
		}
		y--;
		while (x-- > 0) {
			q.offer(room[y][x]);
			room[y][x] = q.poll();
		}
		x++;
		while (y-- > cy + 2) {
			q.offer(room[y][x]);
			room[y][x] = q.poll();
		}
		q.clear();
	}

	private static void spread() {
		int ny, nx;
		int[][] plus = new int[R][C];
		int amount;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (room[i][j] > 0) {
					amount = room[i][j] / 5;
					for (int k = 0; k < 4; k++) {
						ny = i + dy[k];
						nx = j + dx[k];
						if (ny < 0 || nx < 0 || ny >= R || nx >= C)
							continue;
						if ((ny == cy && nx == cx) || (ny == (cy + 1) && nx == cx))
							continue;
						room[i][j] -= amount;
						plus[ny][nx] += amount;
					}
				}
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (plus[i][j] > 0) {
					room[i][j] += plus[i][j];
				}
			}
		}
	}

	private static int sum() {
		int total = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (room[i][j] != -1)
					total += room[i][j];
			}
		}
		return total;
	}
}
