package algo_200310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 다리만들기1 {
	static int N;
	static int[][] map;
	static boolean[][] isVisited;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int min_dist;
	static Queue<pos> ends;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		isVisited = new boolean[N][N];
		ends = new LinkedList<>();
		min_dist = N * N;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int num = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !isVisited[i][j]) {
					isVisited[i][j] = true;
					bfs(i, j, num);
					num++;
				}
			}
		}
		isVisited = new boolean[N][N];
		bridge_bfs();
		System.out.println(min_dist);
	}

	private static void bfs(int y, int x, int num) {
		int ny, nx;
		pos cur;
		Queue<pos> q = new LinkedList<pos>();
		q.offer(new pos(y, x, 0));

		while (!q.isEmpty()) {
			cur = q.poll();
			map[cur.y][cur.x] = num;
			for (int i = 0; i < 4; i++) {
				ny = cur.y + dy[i];
				nx = cur.x + dx[i];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N || isVisited[ny][nx]) {
					continue;
				}
				if (map[ny][nx] == 0) {
					ends.offer(new pos(ny, nx, num));
					continue;
				}
				isVisited[ny][nx] = true;
				q.offer(new pos(ny, nx, 0));
			}
		}
	}

	private static void bridge_bfs() {
		pos cur;
		int num, ny, nx;

		Queue<pos> q = new LinkedList<>();
		while (!ends.isEmpty()) {
			cur = ends.poll();
			q.offer(cur);
			num = cur.cnt; // 섬의 번호
			cur.cnt = 0;
			for (int i = 0; i < N; i++) {
				Arrays.fill(isVisited[i], false);
			}
			isVisited[cur.y][cur.x] = true;
			while (!q.isEmpty()) {
				cur = q.poll();
				if (cur.cnt >= min_dist) {
					continue;
				}
				for (int i = 0; i < 4; i++) {
					ny = cur.y + dy[i];
					nx = cur.x + dx[i];
					if (ny < 0 || nx < 0 || ny >= N || nx >= N || isVisited[ny][nx]) {
						continue;
					}
					if (map[ny][nx] == 0) {
						isVisited[ny][nx] = true;
						q.offer(new pos(ny, nx, cur.cnt + 1));
					} else if (map[ny][nx] != num) {
						min_dist = Math.min(min_dist, cur.cnt + 1);
					}
				}
			}
		}
	}
}

class pos {
	int y;
	int x;
	int cnt;

	pos(int y, int x, int c) {
		this.y = y;
		this.x = x;
		this.cnt = c;
	}
}
