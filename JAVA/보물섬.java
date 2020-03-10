package algo_200308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class º¸¹°¼¶ {
	static int N, M;
	static boolean[][] map;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		String line;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		result = 0;

		for (int i = 0; i < N; i++) {
			line = in.readLine();
			for (int j = 0; j < M; j++) {
				if (line.charAt(j) == 'L') {
					map[i][j] = true;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]) {
					bfs(i, j);
				}
			}
		}
		System.out.println(result);
	}

	private static void bfs(int y, int x) {
		Queue<Ground> q = new LinkedList<>();
		boolean[][] isVisited = new boolean[N][M];
		Ground cur;
		int ny, nx;
		q.offer(new Ground(y, x, 0));
		isVisited[y][x] = true;
		while (!q.isEmpty()) {
			cur = q.poll();
			for (int i = 0; i < 4; i++) {
				ny = cur.y + dy[i];
				nx = cur.x + dx[i];
				if (ny < 0 || nx < 0 || ny >= N || nx >= M || !map[ny][nx] || isVisited[ny][nx]) {
					continue;
				}
				q.offer(new Ground(ny, nx, cur.cnt + 1));
				isVisited[ny][nx] = true;
				result = Math.max(result, cur.cnt + 1);
			}
		}
	}
}

class Ground {
	int y;
	int x;
	int cnt;

	Ground(int y, int x, int c) {
		this.y = y;
		this.x = x;
		this.cnt = c;
	}
}